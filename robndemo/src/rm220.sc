;;; Sierra Script 1.0 - (do not remove this comment)
(script# 220)
(include game.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm220 0
)

(procedure (localproc_000e)
	(if cDAudio (introSound vol: 117 changeState:))
)

(procedure (localproc_0021)
	(if cDAudio (introSound vol: 90 changeState:))
)

(instance rm220 of Room
	(properties
		picture 119
	)
	
	(method (init)
		(super init:)
		(InitAddToPics sher)
		(john init: stopUpd:)
		(tuck init: stopUpd:)
		(will init: stopUpd:)
		(sHead
			posn: (sher x?) (- (sher y?) 61)
			setPri: 14
			init:
			stopUpd:
		)
		(ego
			view: 124
			posn: 100 169
			loop: 7
			cel: 0
			init:
			setScript: moveOn
		)
	)
	
	(method (doit)
		(if (and cDAudio (voice prevSignal?))
			(switch (voice number?)
				(502
					(voice dispose:)
					(localproc_000e)
					(robMouth dispose:)
				)
				(503 (voice number: 504 play:))
				(504
					(voice dispose:)
					(localproc_000e)
					(sherMouth dispose:)
				)
			)
		)
		(super doit:)
	)
)

(instance voice of Sound
	(properties
		number 502
		priority 15
	)
)

(instance sher of PicView
	(properties
		x 187
		y 153
		view 124
		loop 6
		priority 13
	)
)

(instance sHead of Prop
	(properties
		view 124
		loop 5
		priority 14
	)
)

(instance will of Prop
	(properties
		x 156
		y 184
		view 124
		loop 10
	)
)

(instance john of Prop
	(properties
		x 216
		y 134
		view 124
		loop 11
	)
)

(instance tuck of Prop
	(properties
		x 123
		y 137
		view 124
		loop 9
	)
)

(instance robinPortrait of PicView
	(properties
		x 41
		y 59
		view 124
		priority 1
		signal ignrAct
	)
)

(instance sheriffPortrait of PicView
	(properties
		x 264
		y 64
		view 124
		loop 2
		priority 1
		signal ignrAct
	)
)

(instance frame1 of PicView
	(properties
		x 40
		y 81
		view 124
		loop 4
		priority 14
		signal ignrAct
	)
)

(instance frame2 of PicView
	(properties
		x 275
		y 81
		view 124
		loop 4
		priority 14
		signal ignrAct
	)
)

(instance robMouth of Prop
	(properties
		x 43
		y 77
		view 124
		loop 1
		priority 12
		signal ignrAct
	)
)

(instance sherMouth of Prop
	(properties
		x 260
		y 72
		view 124
		loop 3
		priority 12
		signal ignrAct
	)
)

(instance moveOn of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0 (= cycles 4))
			(1 (ego setCycle: EndLoop self))
			(2 (= cycles 4))
			(3 (will setCycle: EndLoop self))
			(4
				(will signal: -32735)
				(ego setLoop: 8 cel: 0 cycleSpeed: 1 setCycle: EndLoop self)
			)
			(5
				(ego stopUpd:)
				(sHead cycleSpeed: 1 setCycle: EndLoop self)
			)
			(6
				(sHead cycleSpeed: 1 setCycle: BegLoop self)
			)
			(7
				(localproc_0021)
				(InitAddToPics robinPortrait frame1)
				(= cycles 4)
			)
			(8
				(robMouth init: setCycle: Forward)
				(if cDAudio
					(voice init: play:)
				)
				(Display 220 0
					p_at 103 40
					p_time 7
					p_font SYSFONT
					p_color 0
					p_dispose
				)
				(Display 220 0
					p_at 102 39
					p_time 7
					p_font SYSFONT
					p_color 30
					p_dispose
				)
				(Display 220 1
					p_at 111 50
					p_time 7
					p_font SYSFONT
					p_color 0
					p_dispose
				)
				(Display 220 1
					p_at 110 49
					p_time 7
					p_font SYSFONT
					p_color 30
					p_dispose
				)
				(Display 220 2
					p_at 95 60
					p_time 7
					p_font SYSFONT
					p_color 0
					p_dispose
				)
				(Display 220 2
					p_at 94 59
					p_time 7
					p_font SYSFONT
					p_color 30
					p_dispose
				)
				(Display 220 3
					p_at 130 70
					p_time 7
					p_font SYSFONT
					p_color 0
					p_dispose
				)
				(Display 220 3
					p_at 129 69
					p_time 7
					p_font SYSFONT
					p_color 30
					p_dispose
				)
				(= cycles 65)
			)
			(9
				(if (== cDAudio 0)
					(robMouth dispose:)
				)
				(= cycles 14)
			)
			(10
				(= global126 1)
				(curRoom drawPic: 119)
				(InitAddToPics sher)
				(john init:)
				(tuck init:)
				(will init: stopUpd:)
				(sHead init: stopUpd:)
				(ego init:)
				(= cycles 5)
			)
			(11
				(= global126 0)
				(tuck setCycle: EndLoop self)
			)
			(12 (john setCycle: EndLoop self))
			(13
				(sHead cycleSpeed: 1 setCycle: EndLoop self)
			)
			(14
				(ego stopUpd:)
				(InitAddToPics sher sheriffPortrait frame2)
				(sherMouth init: setCycle: Forward)
				(localproc_0021)
				(sHead cycleSpeed: 1 setCycle: BegLoop self)
			)
			(15
				(if cDAudio (voice number: 503 init: play:))
				(Display 220 4
					p_at 48 40
					p_time 9
					p_font SYSFONT
					p_color 0
				)
				(Display 220 4
					p_at 47 41
					p_time 9
					p_font SYSFONT
					p_color 15
				)
				(Display 220 5
					p_at 49 50
					p_time 9
					p_font SYSFONT
					p_color 0
				)
				(Display 220 5
					p_at 48 51
					p_time 9
					p_font SYSFONT
					p_color 15
				)
				(Display 220 6
					p_at 44 60
					p_time 9
					p_font SYSFONT
					p_color 0
				)
				(Display 220 6
					p_at 43 61
					p_time 9
					p_font SYSFONT
					p_color 15
				)
				(Display 220 7
					p_at 76 70
					p_time 9
					p_font SYSFONT
					p_color 0
				)
				(Display 220 7
					p_at 75 71
					p_time 9
					p_font SYSFONT
					p_color 15
				)
				(= cycles 65)
			)
			(16
				(if (not cDAudio) (sherMouth dispose:))
				(john setCycle: BegLoop)
				(= cycles 40)
			)
			(17
				(introSound fade: 0 10 8 1)
				(= cycles 10)
			)
			(18
				(curRoom newRoom: 210)
				(self dispose:)
			)
		)
	)
)
