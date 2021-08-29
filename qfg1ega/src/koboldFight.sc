;;; Sierra Script 1.0 - (do not remove this comment)
(script# 111)
(include game.sh)
(use Main)
(use KoboldCave)
(use Motion)

(public
	swBlow 0
	swDodge 1
	swParry 2
	knStab 3
	knDodge 4
)

(local
	stabLoop
)

(instance swBlow of KScript	
	(method (dispose)
		(super dispose:)
		(DisposeScript 111)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= stabLoop egoKoboldBattleLoop)
				(if (Random 0 1)
					(ego setLoop: (+ egoKoboldBattleLoop 4))
					(= register 5)
				else
					(ego setLoop: (+ egoKoboldBattleLoop 2))
					(= register 4)
				)
				(ego setCel: 0)
				(= cycles register)
			)
			(1
				(ego setCel: 1)
				(if
					(and
						(== egoKoboldBattleLoop stabLoop)
						(>= koboldEvade (Rand100))
					)
					(theKobold getHurt: damageToKobold)
				)
				(= cycles 2)
			)
			(2
				(ego setCel: (if (== register 5) 2 else 0))
				(= cycles register)
			)
			(3
				(ego setLoop: egoKoboldBattleLoop setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance swDodge of KScript
	(method (dispose)
		(super dispose:)
		(DisposeScript 111)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: egoKoboldBattleLoop setCel: register)
				(= cycles 10)
			)
			(1
				(ego setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance swParry of KScript
	(method (dispose)
		(super dispose:)
		(DisposeScript 111)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego setLoop: (+ egoKoboldBattleLoop 8) setCel: 0)
				(= cycles 8)
			)
			(1
				(ego setLoop: egoKoboldBattleLoop)
				(self dispose:)
			)
		)
	)
)

(instance knStab of KScript
	(method (dispose)
		(super dispose:)
		(DisposeScript 111)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= stabLoop egoKoboldBattleLoop)
				(ego setLoop: (if egoKoboldBattleLoop 5 else 0))
				(ego setCel: -1 cel: 0 setCycle: CycleTo 4 1 self)
			)
			(1
				(if
					(and
						(== egoKoboldBattleLoop stabLoop)
						(>= koboldEvade (Rand100))
					)
					(theKobold getHurt: damageToKobold)
				)
				(= cycles 4)
			)
			(2
				(ego setCycle: EndLoop self)
				(= cycles register)
			)
			(3
				(ego setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance knDodge of KScript
	(method (dispose)
		(super dispose:)
		(DisposeScript 111)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego
					setLoop: (if egoKoboldBattleLoop (+ register 5) else register)
					setCel: 0
				)
				(= cycles 10)
			)
			(1
				(ego setLoop: (if egoKoboldBattleLoop 5 else 0))
				(self dispose:)
			)
		)
	)
)
