;;; Sierra Script 1.0 - (do not remove this comment)
(script# 784)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	police 0
)

(local
	soundCued
)
(instance police of Room
	(properties
		picture 128
	)
	
	(method (init)
		(super init:)
		(HandsOff)
		(Load FONT 41)
		(Load SOUND 25 54)
		(LoadMany PICTURE 1 85)
		(DrawPic 128 WIPEDOWN TRUE 0)
		(prop1 setCycle: EndLoop self init:)
		(prop2 init: hide:)
		(prop3 setPri: 10 init: hide:)
		(prop4 setPri: 10 init: hide:)
		(prop5 init: hide:)
		(prop6 setPri: 11 init: hide:)
	)
	
	(method (doit)
		(if (== (prop1 view?) 228)
			(cond 
				((< (prop1 cel?) 2)
					(prop1 cycleSpeed: 1)
				)
				((< (prop1 cel?) 5)
					(prop1 cycleSpeed: 2)
				)
				(else
					(prop1 cycleSpeed: 0)
					(if (and (!= (cSound number?) 25) (not soundCued))
						(= soundCued TRUE)
						(cSound number: 25 loop: 1 play:)
					)
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (cue)
		(police curPic: 1)
		(DrawPic 1 WIPEDOWN TRUE 1)
		(addToPics add: boat policeman doit:)
		(prop1
			view: 111
			loop: 4
			posn: 105 118
			setPri: 12
			setCycle: Forward
			setScript: PoliceEscort
		)
		(prop5 stopUpd: show:)
		(Display 784 0
			p_at 116 0
			p_width 240
			p_color vWHITE
			p_back -1
			p_font SYSFONT
		)
	)
)

(instance CEyeActions of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(CEye show:)
				(= cycles 1)
			)
			(1
				(CEye hide:)
				(= state -1)
				(= seconds (Random 3 6))
			)
		)
	)
)

(instance LEyeActions of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(LEye cel: 1 show:)
				(= cycles 1)
			)
			(1
				(LEye hide:)
				(= state -1)
				(= seconds (Random 3 6))
			)
		)
	)
)

(instance COEyeActions of Script

	(method (doit)
		(switch (prop3 loop?)
			(4 (COEye posn: 145 94 cel: 0))
			(6 (COEye posn: 150 95 cel: 1))
			(7 (COEye posn: 147 97 cel: 2))
			(else  (COEye hide:))
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(COEye show:)
				(= cycles 1)
			)
			(1
				(COEye hide:)
				(= state -1)
				(= seconds (Random 3 6))
			)
		)
	)
)

(instance PoliceEscort of Script

	(method (doit)
		(if (== (self state?) 4)
			(prop1
				posn: (- (actor1 x?) 4) (- (actor1 y?) 35)
				show:
			)
		)
		(super doit:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(actor1
					setCycle: Walk
					setMotion: MoveTo 150 86 self
					init:
				)
				(actor2
					setLoop: 7
					setCycle: Walk
					setMotion: MoveTo 135 84
					init:
				)
				(cSound number: 54 loop: 1 play:)
			)
			(1
				(actor1 setMotion: MoveTo 176 127 self)
				(actor2 setMotion: MoveTo 167 125)
			)
			(2
				(cast eachElementDo: #hide)
				(cls)
				(addToPics dispose:)
				(police curPic: 85)
				(DrawPic 85 IRISIN TRUE 0)
				(prop2 show:)
				(prop3 loop: 4 setCycle: Forward show:)
				(prop4 show:)
				(prop6 show:)
				(Print 784 1 #at 62 135 #dispose)
				(= seconds 4)
			)
			(3
				(prop3 cel: 0 setCycle: 0)
				(= cycles 2)
			)
			(4
				(cast eachElementDo: #hide)
				(cls)
				(police curPic: 1)
				(DrawPic 1 IRISOUT TRUE 1)
				(prop5 setCycle: EndLoop show:)
				(prop1 loop: 5)
				(actor1
					view: 111
					setLoop: 1
					setCel: 0
					posn: 25 151
					show:
					setMotion: MoveTo -64 151 self
				)
				(actor2
					view: 111
					setLoop: 6
					posn: 88 147
					show:
					setMotion: MoveTo -1 147
				)
			)
			(5
				(cast eachElementDo: #hide)
				(police curPic: 85)
				(DrawPic 85 IRISIN TRUE 0)
				(prop2 show:)
				(prop3
					loop: 5
					cel: 0
					cycleSpeed: 1
					setCycle: EndLoop self
					show:
				)
				(prop4 show:)
				(prop6 show:)
				(CEye setPri: 14 init: setScript: CEyeActions)
				(LEye setPri: 14 init: setScript: LEyeActions)
				(COEye setPri: 14 init: setScript: COEyeActions)
			)
			(6
				(prop3 loop: 6 cycleSpeed: 0 setCycle: Forward)
				(Print 784 2 #at 62 135 #dispose)
				(= seconds 8)
			)
			(7
				(Print 784 3 #at 20 135 #width 280 #dispose)
				(= seconds 8)
			)
			(8
				(prop3 setCel: 0)
				(Print 784 4 #at 62 135 #dispose)
				(prop4 setCycle: Forward)
				(= seconds 8)
			)
			(9
				(prop3 loop: 5)
				(prop3
					cel: (- (NumCels prop3) 1)
					cycleSpeed: 1
					setCycle: BegLoop self
				)
				(prop4 setCel: 0)
			)
			(10
				(if (ego has: 22)
					(LEye show: loop: 3 cel: 0 setScript: 0)
				)
				(prop3 loop: 0 setCycle: EndLoop self)
			)
			(11
				(prop3 loop: 4 cel: 0 cycleSpeed: 0 setCycle: Forward)
				(if (ego has: 22)
					(Print 784 5 #at 62 135 #dispose)
					(= seconds 8)
				else
					(= state 13)
					(= cycles 1)
				)
			)
			(12
				(Print 784 6 #at 62 135 #dispose)
				(= seconds 8)
			)
			(13
				(prop3 setCel: 0)
				(LMouth cycleSpeed: 0 setPri: 14 setCycle: Forward init:)
				(Print 784 7 #at 62 135 #dispose)
				(= seconds 6)
			)
			(14
				(if (ego has: 22)
					(LEye setScript: LEyeActions)
					(LMouth hide:)
				)
				(prop3 loop: 7 cel: 0 cycleSpeed: 0 setCycle: Forward)
				(prop5
					view: 185
					loop: 9
					cel: 0
					posn: 109 138
					setPri: 10
					cycleSpeed: 1
					setCycle: Forward
					show:
				)
				(prop6 cycleSpeed: 1 setCycle: EndLoop)
				(Print 784 8 #at 62 20 #dispose)
				(= seconds 8)
			)
			(15
				(prop6 stopUpd:)
				(Print 784 9 #at 20 20 #width 280 #dispose)
				(= seconds 8)
			)
			(16
				(Print 784 10 #at 62 20 #dispose)
				(= seconds 6)
			)
			(17
				(cls)
				(prop3 setCel: 0)
				(Display 784 11
					p_at 157 43
					p_width 50
					p_color vBROWN
					p_back -1
					p_font 41
				)
				(Display 784 11
					p_at 156 41
					p_width 50
					p_color vYELLOW
					p_back -1
					p_font 41
				)
				(= seconds 4)
				(cSound fade:)
			)
			(18
				(curRoom newRoom: 786)
			)
		)
	)
)

(instance boat of PicView
	(properties
		y 153
		x 101
		view 111
	)
)

(instance policeman of PicView
	(properties
		y 130
		x 147
		view 111
		cel 1
		priority 12
	)
)

(instance prop1 of Prop
	(properties
		y 47
		x 164
		view 228
	)
)

(instance prop2 of Prop
	(properties
		y 62
		x 121
		view 185
		loop 1
	)
)

(instance prop3 of Prop
	(properties
		y 112
		x 153
		view 185
	)
)

(instance prop4 of Prop
	(properties
		y 65
		x 227
		view 285
		loop 2
	)
)

(instance prop5 of Prop
	(properties
		y 74
		x 129
		view 54
		cycleSpeed 1
	)
)

(instance prop6 of Prop
	(properties
		y 136
		x 84
		view 285
		loop 3
	)
)

(instance actor1 of Actor
	(properties
		y 86
		x 150
		view 394
	)
)

(instance actor2 of Actor
	(properties
		y 84
		x 135
		view 111
	)
)

(instance CEye of Prop
	(properties
		y 50
		x 217
		view 285
	)
)

(instance LEye of Prop
	(properties
		y 43
		x 114
		view 185
		loop 3
	)
)

(instance LMouth of Prop
	(properties
		y 53
		x 115
		view 185
		loop 8
	)
)

(instance COEye of Prop
	(properties
		y 50
		x 217
		view 185
		loop 2
	)
)
