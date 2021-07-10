;;; Sierra Script 1.0 - (do not remove this comment)
(script# 342)
(include game.sh)
(use Main)
(use Intrface)
(use n396)
(use Submarine_806)
(use EgoDead)
(use System)

(public
	coontz 0
)

(local
	local0
	local1 =  1
	local2
)
(instance coontz of Script

	(method (doit)
		(if (and (not script) (OneOf state 3 2) (not (-- local0)))
			(self setScript: pingedWrong)
		)
		(if local2 (-- local2))
		(if (and (& (Submarine flags?) $0080) local1)
			(= local2 20)
			(= local1 0)
		)
		(if
			(and
				(OneOf state 3 2)
				local2
				(not (& (Submarine flags?) $0080))
			)
			(self cue:)
		)
		(if
			(and
				(not script)
				(== local1 0)
				(not local2)
				(& (Submarine flags?) $0080)
			)
			(self setScript: pingedWrong)
		)
		(super doit:)
	)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 342)
		(DisposeScript 396)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				((ScriptID 27 4) posn: 83 75)
				(Submarine
					longitude: 12
					latitude: 36
					depth: 500
					absHeading: 45
					hSpeed: 20
				)
				(= seconds 10)
			)
			(1
				(Print 342 3)
				(SubPrint 4 342 4)
				(= seconds 5)
			)
			(2
				(SubPrint 4 342 5)
				(= local0 300)
				(= seconds 5)
			)
			(4 (= seconds 3))
			(5
				(SubPrint 3 342 6)
				(= seconds 4)
			)
			(6
				(SubPrint 3 342 7)
				(= seconds 4)
			)
			(7
				(SubPrint 3 342 8)
				(= seconds 4)
			)
			(8
				(SubPrint 4 342 9)
				(= seconds 5)
			)
			(9
				(SubPrint 4 342 10)
				(= seconds 5)
			)
			(10
				(theGame changeScore: 5)
				(self dispose:)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((and (== (event type?) 1024) (< 0 state))
				(switch (event message?)
					(0
						(SubPrint 5 342 0)
						(self setScript: firedOnCoontz)
					)
					(1
						(SubPrint 5 342 1)
					)
					(2
						(SubPrint 5 342 2)
						(self setScript: firedOnCoontz)
					)
				)
			)
		)
	)
)

(instance pingedWrong of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(SubPrint 4 342 11)
				(= seconds 5)
			)
			(1
				(SubPrint 5 342 12)
				(= seconds 6)
			)
			(2 (EgoDead 7 0 0 342 13))
		)
	)
)

(instance firedOnCoontz of Script

	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1 (EgoDead 926 1 0 342 14))
		)
	)
)
