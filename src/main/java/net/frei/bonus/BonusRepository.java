package net.frei.bonus;

import org.springframework.data.jpa.repository.JpaRepository;

import net.frei.bonus.Bonus.BonusID;

public interface BonusRepository extends JpaRepository<Bonus, BonusID> {

}
