;;; Sierra Script 1.0 - (do not remove this comment)
(script# 4630)
(include sci.sh)
(use Main)
(use Procs)
(use n960)
(use Motion)
(use Actor)
(use System)

(public
	rm4v630 0
)

(local
	local0
)
(instance rm4v630 of ShiversRoom
	(properties
		picture 4240
	)
	
	(method (init)
		(efExitLeft init: 2)
		(efExitRight init: 1)
		(= local0 0)
		(spPuzzle init:)
		(super init: &rest)
	)
	
	(method (newRoom)
		(if local0
			(sounds play: 10409 0 30 0)
			(spPuzzle cel: 0 init:)
			(UpdateScreenItem spPuzzle)
			(FrameOut)
			(proc960_1)
		)
		(DisposeScript 960)
		(super newRoom: &rest)
	)
)

(instance efExitLeft of ExitFeature
	(properties
		nextRoom 4620
	)
)

(instance efExitRight of ExitFeature
	(properties
		nextRoom 4600
	)
)

(instance spPuzzle of ShiversProp
	(properties
		x 49
		y 16
		priority 255
		fixPriority 1
		view 4240
	)
	
	(method (doVerb)
		(curRoom setScript: sPuzzle)
	)
)

(instance sPuzzle of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(efExitLeft dispose:)
				(efExitRight dispose:)
				(= temp0 0)
				(if (proc951_5 6) (= temp0 1))
				(proc960_0 self)
				(sounds play: 10408 0 30 0)
				(spPuzzle setCycle: End self)
				(if (== 1 temp0) (sounds play: 10405 0 50 0))
			)
			(1
				(= local0 1)
				(theGame handsOn:)
				(spPuzzle dispose:)
			)
			(2
				(= local0 0)
				(theGame handsOff:)
				(spPuzzle
					init:
					cel: (spPuzzle lastCel:)
					setCycle: Beg self
				)
				(sounds play: 10409 0 30 self)
			)
			(3)
			(4
				(proc960_1)
				(if (proc951_5 6)
					(= cycles 1)
				else
					(theGame handsOn:)
					(efExitLeft init: 2)
					(efExitRight init: 1)
					(self dispose:)
				)
			)
			(5
				(spPuzzle dispose:)
				((Prop new:) view: 4190 setPri: 25 1 init:)
				(curRoom drawPic: 4190)
				(= cycles 1)
			)
			(6
				((Prop new:)
					view: 4191
					setPri: 25 1
					init:
					setCycle: End self
				)
				(sounds play: 10404 0 90 0)
			)
			(7
				(theGame handsOn:)
				(curRoom newRoom: 4620)
			)
		)
	)
)
