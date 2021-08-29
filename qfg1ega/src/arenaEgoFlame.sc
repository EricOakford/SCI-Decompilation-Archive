;;; Sierra Script 1.0 - (do not remove this comment)
(script# ARENA_FLAME) ;147
(include game.sh)
(use Main)
(use Motion)
(use System)

(public
	egoFlame 0
)

(local
	theSpell
	theWarrior
	theX
	theY
)
(instance egoFlame of Script
	(method (init)
		(= theWarrior (ScriptID WARRIOR 0))
		(= theSpell (ScriptID CLOSECOMBAT 1))
		(super init: &rest)
	)
	
	(method (dispose)
		(HandsOn)
		(super dispose:)
		(DisposeScript ARENA_FLAME)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(theWarrior
					canFight: FALSE
					action: ActCast
				)
				(self cue:)
			)
			(1
				(theSpell
					setLoop: 6
					setCel: 0
					cycleSpeed: 1
					ignoreActors:
					posn: (- (theWarrior x?) 77) (- (theWarrior y?) 87)
					init:
					setCycle: CycleTo 2 1 self
				)
			)
			(2
				(= theX
					(/ (- ((theWarrior opponent?) flameX?) (theSpell x?)) 3)
				)
				(= theY
					(/ (- (theSpell y?) ((theWarrior opponent?) flameY?)) 3)
				)
				(theSpell
					setPri: 15
					setCel: 3
					posn: (+ (theSpell x?) theX) (- (theSpell y?) theY)
				)
				(= cycles 2)
			)
			(3
				(theSpell
					setCel: 4
					posn: (+ (theSpell x?) theX) (- (theSpell y?) theY)
				)
				(= cycles 2)
			)
			(4
				(theSpell
					setCel: 5
					posn: (+ (theSpell x?) theX) (+ (theSpell y?) theY)
				)
				(= cycles 2)
			)
			(5
				(theSpell setCel: -1 setCycle: EndLoop self)
			)
			(6
				(theSpell dispose:)
				((theWarrior opponent?) getHurt: (+ 5 (/ [egoStats FLAMEDART] 3)))
				(theWarrior canFight: TRUE show:)
				(self dispose:)
			)
		)
	)
)
