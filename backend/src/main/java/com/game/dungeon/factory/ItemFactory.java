package com.game.dungeon.factory;

import com.game.dungeon.model.Item;
import com.game.dungeon.model.Question;
import com.game.dungeon.repository.QuestionRepository;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class ItemFactory {
    private final Random random = new Random();
    private final QuestionRepository questionRepository;

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