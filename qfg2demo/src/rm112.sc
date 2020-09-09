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

(define ShemaX 200)
(define ShemaY 107)

(local
	musicianInCast
	musicianPausedSignal
	danceSteps =	[
		DanceEndLoop 0 0 0
		DanceEndLoop 1 0 15
		DanceEndLoop 2 0 17
		DanceEndLoop 3 0 20
		DanceMove 4 2 dirE
		DanceMove 4 4 dirW
		DanceMove 4 2 dirE
		DanceRelPosn -19 0 0
		DanceEndLoop 5 0 0
		DanceMove 6 2 dirS
		DanceMove 6 2 dirN
		DanceEndLoop 7 0 0
		DanceMove 8 2 dirE
		DanceMove 8 4 dirW
		DanceMove 8 2 dirE
		DanceBegLoop 7 0 0
		DanceMove 6 1 dirS
		DanceMove 6 1 dirN
		DanceBegLoop 5 0 0
		DanceRelPosn 16 0 0
		DanceMove 4 1 dirW
		DanceMove 4 2 dirE
		DanceMove 4 1 dirW
		DanceView vDance2 0 0
		DanceEndLoop 0 0 0
		DanceEndLoop 2 4 0
		DanceRelPosn -27 0 0
		DanceEndLoop 1 0 0
		DanceEndLoop 3 4 0
		DanceView rShemaDance 0 0
		DanceEndLoop 5 0 0
		DanceView vDance2 0 0
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DanceMove 4 0 dirE
		DanceEndLoop 4 0 3
		DancePosn ShemaX ShemaY 0
		DanceEndLoop 5 0 0
		DanceCel 6 0 10
		DanceEndLoop 7 0 0
		DanceView rShemaDance 0 0
		DanceBegLoop 0 0 20
		DanceEnd
	]
)
(instance rm112 of Room
	(properties
		picture rKattaClose
		style IRISIN
	)
	
	(method (init)
		(LoadMany VIEW rShemaDance vDance2 vDanceMusician rKattaClose)
		(Load SOUND rShemaDance)
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
		view rShemaDance
		cycleSpeed 1
		xStep 1
		moveSpeed 1
	)
)

(instance musician of Prop
	(properties
		x 110
		y 88
		view vDanceMusician
		loop 1
		signal fixPriOn
		cycleSpeed 6
	)
)

(instance farLamp of PicView
	(properties
		x 88
		y 115
		view rKattaClose
		loop 1
	)
)

(instance nearLamp of PicView
	(properties
		x 226
		y 156
		view rKattaClose
		loop 1
		cel 1
	)
)

(instance egoSilhouette of PicView
	(properties
		x 249
		y 167
		view rKattaClose
		loop 2
	)
)

(instance farFlame of PicView
	(properties
		x 88
		y 97
		view rKattaClose
		loop 3
	)
)

(instance nearFlame of PicView
	(properties
		x 226
		y 136
		view rKattaClose
		loop 3
	)
)

(instance bellySound of QueuedSound
	(properties
		number rShemaDance
		priority 10
	)
)

(instance dance of DanceQueuedSound
	
	(method (dispose)
		(curRoom newRoom: ALLEY)
		(super dispose:)
	)
	
	(method (cue &tmp aSignal)
		(super cue: &rest)
		(if musicianInCast
			(cond 
				(
					(>
						(= aSignal (theMusic prevSignal?))
						musicianPausedSignal
					)
					(musician setCycle: Forward)
					(= musicianPausedSignal INFINITY)
				)
				((OneOf (theMusic prevSignal?) 140 147 156 456)
					(musician setCel: 2 setCycle: 0)
					(= musicianPausedSignal aSignal)
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
			(1
				(= seconds 2)
			)
			(2
				(shema setScript: dance bellySound)
				(self dispose:)
			)
		)
	)
)
