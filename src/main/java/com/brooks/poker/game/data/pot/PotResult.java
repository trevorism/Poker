package com.brooks.poker.game.data.pot;

import java.util.*;

import com.brooks.poker.player.Player;

/**
 * @author Trevor
 * 
 */
public class PotResult {
	private final List<PotWinner> potWinners;

	public PotResult() {
		potWinners = new LinkedList<>();
	}

	public void resolvePot(Pot pot) {
		List<Player> winners = collectWinners(pot);
		addChipsToWinners(winners, pot.getPotAmount());
	}

	private List<Player> collectWinners(Pot pot) {
		List<Player> winners = new LinkedList<>();

		for (Player player : pot.getEligiblePlayers()) {
			if (winners.isEmpty())
				winners.add(player);
			else {
				upateWinnersWithBestHand(winners, player);
			}
		}

		return winners;
	}

	private void upateWinnersWithBestHand(List<Player> winners, Player player) {
		int compareResult = compareAgainstExistingPlayer(winners, player);
		if (compareResult == 0) {
			winners.add(player);
		} else if (compareResult < 0) {
			winners.clear();
			winners.add(player);
		}
	}

	private int compareAgainstExistingPlayer(List<Player> winners, Player player) {
		Player existingPlayer = winners.get(0);
		return existingPlayer.getHand().compareTo(player.getHand());
	}

	private void addChipsToWinners(List<Player> winners, int potAmount) {
		if (potAmount == 0)
			return;
		// Integer division, so always a whole number.
		int awardAmount = potAmount / winners.size();

		for (Player player : winners) {
			player.addChips(awardAmount);
			addPotWinner(player, awardAmount);
		}
		//Give the remainder to a random winner, so as to not lose chips from the game.
		if(potAmount % winners.size() != 0){
			Random random = new Random();
			int index = random.nextInt(winners.size());
			winners.get(index).addChips(potAmount % winners.size());
		}
	}

	private void addPotWinner(Player player, int amount) {
		PotWinner potWinner = new PotWinner(player, amount);
		potWinners.add(potWinner);
	}

	public Set<Player> getWinningPlayers() {
		Set<Player> players = new HashSet<>();
		for (PotWinner potWinner : potWinners) {
			players.add(potWinner.player);
		}
		return players;
	}

	public List<PotWinner> getPotWinners() {
		return potWinners;
	}
}
