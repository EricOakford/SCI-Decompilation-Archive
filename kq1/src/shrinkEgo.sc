;;; Sierra Script 1.0 - (do not remove this comment)
(script# 779)
(include game.sh)
(use Main)
(use Motion)
(use User)
(use Menu)
(use System)

(public
	shrinkEgo 0
)

(instance shrinkEgo of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(if
			(and
				(== state 3)
				(== curRoomNum 78)
				(== (ego onControl: origin) cLGREY)
			)
			(= seconds 0)
			(self cue:)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (and (== curRoomNum 78) (== (ego onControl: origin) cLGREY))
					(ego setMotion: MoveTo 304 145 self)
				else
					(self cue:)
				)
			)
			(1
				(HandsOff)
				(ego
					view: 24
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
					setMotion: 0
				)
				(SetItemOwner iMushroom 0)
			)
			(2
				((ScriptID 0 21) number: 20 init: play:)
				(ego view: 30 setCycle: EndLoop self)
			)
			(3
				(ego
					view: 31
					setStep: 2 1
					cycleSpeed: 0
					illegalBits: cWHITE
					setCycle: Walk
				)
				(if (== curRoomNum 78)
					(SolvePuzzle fAteMushroom 2)
					(User canControl: TRUE)
					(TheMenuBar state: TRUE)
					(= seconds 10)
				else
					(theGame changeScore: -1)
					(= seconds 4)
				)
			)
			(4
				(User canControl: FALSE)
				(if (and (== curRoomNum 78) (== (ego onControl: origin) cBLUE))
					;for some reason, this never happens - Graham can be in that area indefinitely without re-enlarging.
					(EgoDead
						{The mushroom wore off and you enlarged.__But you can't squeeze a five-foot Knight into a 6-inch hole!}
					)
				else
					((ScriptID 0 21) number: 21 play:)
					(ego
						view: 30
						cycleSpeed: 1
						cel: 6
						setMotion: 0
						illegalBits: cWHITE
						setCycle: BegLoop self
					)
				)
			)
			(5
				(NormalEgo)
				(ego loop: 2 illegalBits: cWHITE)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)
