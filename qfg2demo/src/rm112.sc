;;; Sierra Script 1.0 - (do not remove this comment)
(script# 3)
(include game.sh)
(use Main)
(use Dance)
(use LoadMany)
(use QSound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm112 0
)

(local
	local0
	theTheMusicPrevSignal
	[danceSteps 217] = [2 0 0 0 2 1 0 15 2 2 0 17 2 3 0 20 4 4 2 3 4 4 4 7 4 4 2 3 5
	-19 0 0 2 5 0 0 4 6 2 5 4 6 2 1 2 7 0 0 4 8 2 3 4 8 4 7 4 8 2 3 3 7 0 0 4 6 1 5
	4 6 1 1 3 5 0 0 5 16 0 0 4 4 1 7 4 4 2 3 4 4 1 7 0 113 0 0 2 0 0 0 2 2 4 0 5 -27
	0 0 2 1 0 0 2 3 4 0 0 112 0 0 2 5 0 0 0 113 0 0 4 4 0 3 2 4 0 3 4 4 0 3 2 4 0 3 4
	4 0 3 2 4 0 3 4 4 0 3 2 4 0 3 4 4 0 3 2 4 0 3 4 4 0 3 2 4 0 3 4 4 0 3 2 4 0 3 4 4
	0 3 2 4 0 3 6 200 107 0 2 5 0 0 1 6 0 10 2 7 0 0 0 112 0 0 3 0 0 20 8]
)
(instance rm112 of Room
	(properties
		picture 110
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW 112 113 111 110)
		(Load SOUND 112)
		(globalSound stop:)
		(LoadMany SCRIPT REVERSE)
		(super init:)
		(ego posn: 1000 1000 init:)
		(shema init:)
		(egoSilhouette init:)
		(nearLamp init:)
		(farLamp init:)
		(nearFlame init:)
		(farFlame init:)
		(musician init:)
		(if (< howFast fast)
			(musician addToPic:)
		)
		(addToPics doit:)
		(self setScript: rmScript)
	)
)

(instance shema of Actor
	(properties
		x 200
		y 107
		yStep 1
		view 112
		cycleSpeed 1
		xStep 1
		moveSpeed 1
	)
)

(instance musician of Prop
	(properties
		x 110
		y 88
		view 111
		loop 1
		signal fixPriOn
		cycleSpeed 6
	)
)

(instance farLamp of PicView
	(properties
		x 88
		y 115
		view 110
		loop 1
	)
)

(instance nearLamp of PicView
	(properties
		x 226
		y 156
		view 110
		loop 1
		cel 1
	)
)

(instance egoSilhouette of PicView
	(properties
		x 249
		y 167
		view 110
		loop 2
	)
)

(instance farFlame of PicView
	(properties
		x 88
		y 97
		view 110
		loop 3
	)
)

(instance nearFlame of PicView
	(properties
		x 226
		y 136
		view 110
		loop 3
	)
)

(instance bellySound of QueuedSound
	(properties
		number 112
		priority 10
	)
)

(instance dance of DanceQueuedSound
	(properties)
	
	(method (dispose)
		(curRoom newRoom: 4)
		(super dispose:)
	)
	
	(method (cue &tmp theMusicPrevSignal)
		(super cue: &rest)
		(if local0
			(cond 
				(
					(>
						(= theMusicPrevSignal (theMusic prevSignal?))
						theTheMusicPrevSignal
					)
					(musician setCycle: Forward)
					(= theTheMusicPrevSignal 32767)
				)
				((OneOf (theMusic prevSignal?) 140 147 156 456)
					(musician setCel: 2 setCycle: 0)
					(= theTheMusicPrevSignal theMusicPrevSignal)
				)
			)
		)
	)
	
	(method (at n)
		(return [danceSteps n])
	)
)

(instance rmScript of Script
	(properties
		cycles 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds 2))
			(2
				(shema setScript: dance bellySound)
				(self dispose:)
			)
		)
	)
)
