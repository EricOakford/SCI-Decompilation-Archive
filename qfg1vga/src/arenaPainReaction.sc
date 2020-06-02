;;; Sierra Script 1.0 - (do not remove this comment)
(script# PAINREACTION)
(include game.sh)
(use Main)
(use Procs)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	painReaction 0
)

(local
	theWarrior
	[local1 3]
)
(instance painReaction of Script
	(properties)
	
	(method (init)
		(sEgoHurt init:)
		(thatsIt init:)
		(= theWarrior (ScriptID WARRIOR 0))
		(egoBlood
			init:
			ignoreActors:
			setPri: (- (theWarrior priority?) 1)
		)
		(super init: &rest)
	)
	
	(method (dispose)
		(sEgoHurt dispose:)
		(thatsIt dispose:)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not ((theWarrior opponent?) script?))
					((theWarrior opponent?) setCycle: 0)
					((theWarrior opponent?) setScript: (ScriptID monsterNum 2))
				)
				(= egoCanFight FALSE)
				(= ticks 1)
			)
			(1
				(sEgoHurt play:)
				(if (ego has: iSword)
					(theWarrior view: 113 forceUpd:)
				else
					(theWarrior view: 106 forceUpd:)
				)
				(theWarrior setPri: -1 setLoop: 0 setCel: 0)
				(egoBlood setCel: 0)
				(= ticks 8)
			)
			(2
				(theWarrior setCel: 1)
				(cond 
					(((theWarrior opponent?) lowBlow?)
						(egoBlood
							posn: (theWarrior x?) (- (theWarrior y?) 27)
							setCycle: EndLoop
						)
					)
					((ego has: iSword)
						(egoBlood
							posn: (+ (theWarrior x?) 1) (- (theWarrior y?) 48)
							setCycle: EndLoop
						)
					)
					(else
						(egoBlood
							posn: (+ (theWarrior x?) 4) (- (theWarrior y?) 48)
							setCycle: EndLoop
						)
					)
				)
				(= ticks 5)
			)
			(3
				(theWarrior cycleSpeed: 10 setCycle: EndLoop self)
			)
			(4
				(theWarrior cycleSpeed: 3 setLoop: 0 setCel: 0)
				(= ticks 30)
			)
			(5
				(= egoCanFight TRUE)
				(theWarrior canFight: TRUE)
				(theWarrior setEgoHP: (theWarrior health?))
				(if (or (Btst fDiedInBattle) (<= (theWarrior health?) 0))
					(thatsIt play:)
					(Animate (cast elements?) FALSE)
					(theGame setCursor: normalCursor TRUE)
					(EgoDead C_DIE_ARENA C_DIE_ARENA_TITLE)
				)
				(egoBlood dispose:)
				(if (ego has: iSword)
					(theWarrior view: 109)
				else
					(theWarrior view: 102)
				)
				(self dispose:)
			)
		)
	)
)

(instance egoBlood of Prop
	(properties
		x 500
		y 500
		view 535
		cycleSpeed 0
	)
)

(instance sEgoHurt of Sound
	(properties
		flags $ffff
		number 104
		priority 1
	)
)

(instance thatsIt of Sound
	(properties
		flags $ffff
		number 105
		priority 1
	)
)
