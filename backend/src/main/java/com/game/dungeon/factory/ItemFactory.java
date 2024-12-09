package com.game.dungeon.factory;

import com.game.dungeon.model.Item;
import com.game.dungeon.model.Question;
import com.game.dungeon.repository.QuestionRepository;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

/**
 * ItemFactory is responsible for creating items for the game.
 * It can create either a teacher (with a question) or a friend (with a hint)
 * based on the provided parameters.
 */
@Component
@RequiredArgsConstructor
public class ItemFactory {
    private final Random random = new Random();
    private final QuestionRepository questionRepository;

    /**
     * @param difficulty      The difficulty level for selecting questions
     * @param usedQuestionIds List of question IDs that have already been used
     * @param isFriend        Whether to create a friend item (true) or teacher item
     *                        (false)
     * @return A new Item instance with appropriate type and properties
     */
    public Item createItem(String difficulty, List<String> usedQuestionIds, boolean isFriend) {
        Item item = new Item();

        item.setType(isFriend ? Item.ItemType.FRIEND : Item.ItemType.EMPTY);

        if (isFriend) {
            Question question = getRandomUnusedQuestion(usedQuestionIds);
            if (question != null) {
                item.setType(Item.ItemType.FRIEND);
                item.setHint(question.getExplanation());
            }
            return item;
        }

        Question question = getRandomUnusedQuestion(usedQuestionIds);
        if (question != null) {
            item.setType(Item.ItemType.TEACHER);
            item.setQuestionId(question.getId());
        }
        return item;
    }

    /**
     * Gets a random question that hasn't been used in the current game.
     *
     * @param usedQuestionIds List of question IDs that have already been used
     * @return A random unused Question, or null if no unused questions are
     *         available
     */
    private Question getRandomUnusedQuestion(List<String> usedQuestionIds) {
        List<Question> availableQuestions = questionRepository.findAll()
                .stream()
                .filter(q -> !usedQuestionIds.contains(q.getId()))
                .toList();

        if (availableQuestions.isEmpty()) {
            return null;
        }

        return availableQuestions.get(random.nextInt(availableQuestions.size()));
    }
}