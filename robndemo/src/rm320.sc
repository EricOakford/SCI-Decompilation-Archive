;;; Sierra Script 1.0 - (do not remove this comment)
(script# 320)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm320 0
)

(procedure (localproc_000e)
	(if numDACs
		(bowSound vol: 120 changeState:)
	)
)

(procedure (localproc_0021)
	(if numDACs
		(bowSound vol: 92 changeState:)
	)
)

(instance rm320 of Room
	(properties
		picture 330
	)
	
	(method (init)
		(super init:)
		(InitAddToPics targetLeft targetRight banner)
		(hand init: stopUpd:)
		(bow init: stopUpd:)
		(target init: stopUpd:)
		(arrow init: stopUpd:)
		(self setScript: flyin)
	)
	
	(method (doit)
		(if (== (bowSound prevSignal?) -1)
			(bowSound dispose:)
			(curRoom newRoom: 95)
		)
		(if (and numDACs (voice prevSignal?))
			(switch (voice number?)
				(506
					(voice number: 507 play:)
				)
				(507
					(voice dispose:)
					(localproc_000e)
				)
				(508
					(voice dispose:)
					(localproc_000e)
				)
			)
		)
		(super doit:)
	)
)

(instance voice of Sound
	(properties
		number 506
		priority 15
	)
)

(instance hand of Actor
	(properties
		x 115
		y 209
		yStep 1
		view 331
		loop 1
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		xStep 1
	)
)

(instance bow of Actor
	(properties
		x 171
		y 97
		yStep 1
		view 331
		cel 1
		priority 1
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		xStep 1
	)
)

(instance arrow of Actor
	(properties
		x 139
		y 87
		yStep 1
		view 331
		loop 2
		priority 2
		signal (| ignrAct fixedLoop fixedCel fixPriOn)
		illegalBits $0000
		xStep 1
	)
)

(instance banner of PicView
	(properties
		x 38
		y 23
		view 326
		loop 1
		priority 3
		signal (| fixedLoop fixedCel fixPriOn)
	)
)

(instance targetLeft of PicView
	(properties
		x 83
		y 51
		view 331
		loop 3
		cel 3
		signal ignrAct
	)
)

(instance targetRight of PicView
	(properties
		x 227
		y 51
		view 331
		loop 3
		cel 4
		signal ignrAct
	)
)

(instance target of View
	(properties
		x 150
		y 51
		view 331
		loop 3
		signal ignrAct
	)
)

(instance targPortrait of PicView
	(properties
		x 68
		y 89
		view 326
		signal (| ignrAct fixedLoop fixedCel)
	)
	
	(method (doVerb)
		(super doVerb:)
	)
)

(instance targFrame of PicView
	(properties
		x 64
		y 92
		view 124
		loop 4
		cel 1
		signal ignrAct
	)
	
	(method (doVerb)
		(super doVerb:)
	)
)

(instance thwip of Sound
	(properties
		number 100
		priority 14
	)
)

(instance thunk of Sound
	(properties
		number 101
		priority 14
	)
)

(instance flyin of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(bowSound vol: 120 init: play:)
				(= cycles 10)
			)
			(1
				(localproc_0021)
				(= cycles 3)
			)
			(2
				(if numDACs
					(voice init: play:)
				)
				(Display 320 0
					p_at 6 3
					p_time 10
					p_font 0
					p_color 0
				)
				(Display 320 0
					p_at 5 2
					p_time 10
					p_font 0
					p_color 30
				)
				(Display 320 1
					p_at 19 13
					p_time 10
					p_font 0
					p_color 0
				)
				(Display 320 2
					p_at 18 12
					p_time 10
					p_font 0
					p_color 30
				)
				(Display 320 3
					p_at 81 23
					p_time 10
					p_font 0
					p_color 0
				)
				(Display 320 3
					p_at 80 22
					p_time 10
					p_font 0
					p_color 30
				)
				(= cycles 65)
			)
			(3
				(= cycles 10)
			)
			(4
				(= talkerOnScreen TRUE)
				(curRoom drawPic: 330)
				(InitAddToPics targetLeft targetRight banner)
				(hand init:)
				(bow init:)
				(target init:)
				(arrow init:)
				(= cycles 4)
			)
			(5
				(= talkerOnScreen FALSE)
				(= cycles 13)
			)
			(6
				(hand
					setMotion: MoveTo (+ (hand x?) 10) (+ (hand y?) 3)
				)
				(bow setMotion: MoveTo (+ (bow x?) 10) (+ (bow y?) 3))
				(arrow
					setMotion: MoveTo (+ (arrow x?) 10) (+ (arrow y?) 3) self
				)
			)
			(7
				(thwip init: play:)
				(arrow setCel: 2 posn: (arrow x?) (- (arrow y?) 39))
				(= cycles 2)
			)
			(8
				(arrow setCel: 3 posn: (target x?) (- (target y?) 10))
				(= cycles 1)
			)
			(9
				(thunk init: play:)
				(arrow hide:)
				(target setCel: 1)
				(= cycles 2)
			)
			(10
				(InitAddToPics targPortrait targFrame)
				(= cycles 15)
			)
			(11
				(hand stopUpd:)
				(bow stopUpd:)
				(localproc_0021)
				(= cycles 6)
			)
			(12
				(if numDACs
					(voice number: 508 init: play:)
				)
				(Display 320 4
					p_at 56 6
					p_time 4
					p_font 0
					p_color 0
				)
				(Display 320 4
					p_at 55 5
					p_time 4
					p_font 0
					p_color 30
				)
				(= cycles 40)
			)
			(13
				(thwip dispose:)
				(thunk dispose:)
				(self dispose:)
			)
		)
	)
)
