;;; Sierra Script 1.0 - (do not remove this comment)
(script# 408)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Actor)
(use System)

(public
	ShdwyFig 0
)

(instance ShdwyFig of Script
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 408)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(DrawPic 11 IRISOUT)
				(addToPics dispose:)
				(features dispose:)
				(cast eachElementDo: #hide)
				(= cycles 2)
			)
			(1
				(Print 408 0 #at 50 10 #dispose)
				(figure
					x: 330
					ignoreActors: TRUE
					setCycle: Walk
					setMotion: MoveTo -10 140 self
					init:
				)
			)
			(2
				(cls)
				(figure dispose: delete:)
				(DrawPic curRoomNum IRISIN)
				(Bset fSawShadowyFigure)
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
)

(instance figure of Actor
	(properties
		y 140
		view 507
		illegalBits $0000
		xStep 5
	)
)
