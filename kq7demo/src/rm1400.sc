;;; Sierra Script 1.0 - (do not remove this comment)
(script# 1400)
(include game.sh)
(use Main)
(use Procs)
(use KQRoom)
(use ExitFeature)
(use Print)
(use Polygon)
(use Feature)
(use System)

(public
	rm1400 0
)

(local
	local0
	local1
)
(instance rm1400 of KQRoom
	(properties
		picture 1400
		style SHOW_FADE_IN
		exitStyle SHOW_FADE_OUT
	)
	
	(method (init)
		(if (Btst 37) (self picture: 1405))
		(super init:)
		(Bset 21)
		(= scrollingIsOn TRUE)
		(bowl init:)
		(myNorthExit init:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PContainedAccess
					init:
						208 95
						310 30
						316 18
						191 86
						141 80
						79 92
						20 100
						0 111
						0 143
						320 142
						320 102
						239 90
					yourself:
				)
				((Polygon new:)
					type: PBarredAccess
					init:
						71 103
						117 105
						106 123
						56 120
					yourself:
				)
		)
		(if demoScripts
			(curRoom setScript: scrollIt)
		else
			(switch prevRoomNum
				(1250
					(if (not (Btst 37))
						(self setScript: scrollIt)
					else
						(ego init: posn: 60 125)
						(self setScript: fromStairs)
					)
				)
				(1410 (ego init: posn: 145 120))
				(else  (ego init: posn: 60 125))
			)
		)
	)
)

(instance sayOk of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Prints
					{The statues head bobs up and down and you hear a grinding noise.}
				)
				(= cycles 1)
			)
			(1
				(theGame handsOn:)
				(curRoom newRoom: 1410)
				(self dispose:)
			)
		)
	)
)

(instance scrollIt of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(thePlane setRect: 0 -270 319 406 priority: 1)
				(UpdatePlane thePlane)
				(= seconds 3)
			)
			(1
				(= local0 -270)
				(= local1 136)
				(while (!= local1 406)
					(thePlane setRect: 0 local0 319 local1 priority: 1)
					(+= local0 3)
					(+= local1 3)
					(UpdatePlane thePlane)
					(FrameOut)
				)
				(= seconds 4)
			)
			(2
				(curRoom newRoom: 1250)
			)
		)
	)
)

(instance fromStairs of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego posn: 174 103)
				(self cue:)
			)
			(1
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance bowl of Feature
	(properties
		nsLeft 110
		nsTop 87
		nsRight 142
		nsBottom 98
		approachX 145
		approachY 120
	)
	
	(method (init)
		(super init: &rest)
		(self approachVerbs: 8 10 setHotspot: 8 10)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(8 (curRoom newRoom: 1410))
		)
	)
)

(instance myNorthExit of ExitFeature
	(properties
		nsLeft 288
		nsRight 320
		nsBottom 56
		exitDir NORTH
	)
	
	(method (init)
		(super init: &rest)
		(self setHotspot: 10 10)
	)
	
	(method (handleEvent event)
		(return
			(cond 
				((event claimed?) (return TRUE))
				((and (& (event type?) walkEvent) (self onMe: event))
					(curRoom newRoom: 1250)
				)
				((and scratch (not (event type?)) (self onMe: event))
					(= theExitFeature self)
					((self scratch?) doit:)
					(return (event claimed: TRUE))
				)
			)
		)
	)
)
