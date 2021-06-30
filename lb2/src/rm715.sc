;;; Sierra Script 1.0 - (do not remove this comment)
(script# 715)
(include game.sh)
(use Main)
(use LBRoom)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	rm715 0
)

(local
	local0
	[local1 20]
)
(instance rm715 of LBRoom
	(properties
		picture 715
		style SCROLLLEFT
		east 720
		west 710
	)
	
	(method (init)
		(ego
			wearingGown: TRUE
			view: 715
			loop: 3
			cel: 0
			posn: 121 142
			init:
		)
		(LoadMany RES_SOUND 710 712 713)
		(LoadMany RES_VIEW 711 712 713 715 710 714 716)
		(super init:)
		(WrapMusic init: 1 1710 1712 1713)
		(theIconBar disable: 7)
		(tut addToPic:)
		(rameses init:)
		(sunnie1 init: setCycle: Forward)
		(sunnie2 init: setCycle: Forward)
		(sunnie3 init: setCycle: Forward)
		(sunnie4 init: setCycle: Forward)
		(sunnie5 init: setCycle: Forward)
		(sunnie6 init: setCycle: Forward)
		(sunnie7 init: setCycle: Forward)
		(sunnie8 init: setCycle: Forward)
		(sunnie9 init: setCycle: Forward)
		(sunnie10 init: setCycle: Forward)
		(curRoom setScript: sQuestion)
	)
	
	(method (dispose)
		(theMusic2 fade:)
		(super dispose: &rest)
	)
)

(instance sQuestion of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 2)
			)
			(1 (= ticks 180))
			(2
				(sunnie1 setCycle: EndLoop self)
				(sunnie2 setCycle: EndLoop)
				(sunnie3 setCycle: EndLoop self)
				(sunnie4 setCycle: EndLoop)
				(sunnie5 setCycle: EndLoop self)
				(sunnie6 setCycle: EndLoop)
				(sunnie7 setCycle: EndLoop)
				(sunnie8 setCycle: EndLoop)
				(sunnie9 setCycle: EndLoop)
				(sunnie10 setCycle: EndLoop)
			)
			(3)
			(4)
			(5
				(sunnie1 addToPic:)
				(sunnie2 addToPic:)
				(sunnie3 addToPic:)
				(sunnie4 addToPic:)
				(sunnie5 addToPic:)
				(sunnie6 addToPic:)
				(sunnie7 addToPic:)
				(sunnie8 addToPic:)
				(sunnie9 addToPic:)
				(sunnie10 addToPic:)
				(WrapMusic dispose:)
				(= cycles 1)
			)
			(6
				(messager say: 1)
				(= cycles 1)
			)
			(7
				(curRoom setInset: (ScriptID 20 0))
				(Message MsgGet 715 3 0 5 1 @local1)
				(= local0 (not (StrCmp @global136 @local1)))
				(= cycles 1)
			)
			(8
				(theIconBar disable: 7)
				(if local0
					(theGame points: 1 156)
					(= global136 0)
					(messager say: 1 0 2)
					(= cycles 1)
				else
					(curRoom setScript: sDeath)
				)
			)
			(9
				(curRoom setInset: (ScriptID 20 0))
				(Message MsgGet 715 3 0 4 1 @local1)
				(= local0 (not (StrCmp @global136 @local1)))
				(= cycles 1)
			)
			(10
				(theIconBar disable: 7)
				(if local0
					(theGame points: 1 157)
					(messager say: 1 0 3)
					(= cycles 1)
				else
					(curRoom setScript: sDeath)
				)
			)
			(11
				(rameses setCycle: EndLoop self)
			)
			(12
				(rameses stopUpd:)
				(= cycles 1)
			)
			(13
				(ego
					normalize: 831
					loop: 1
					scaleSignal: 1
					scaleX: 110
					scaleY: 110
					setHeading: 0 self
				)
			)
			(14
				(ego setLoop: 3 setMotion: MoveTo 123 159 self)
			)
			(15
				(ego setPri: 9 setMotion: MoveTo 130 159 self)
			)
			(16
				(ego setLoop: 0 setMotion: MoveTo 152 186 self)
			)
			(17
				(ego edgeHit: 2)
				(curRoom newRoom: 720)
				(self dispose:)
			)
		)
	)
)

(instance sDeath of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (rameses setCycle: EndLoop self))
			(1
				(messager say: 1 0 1 1)
				(= cycles 1)
			)
			(2
				(rameses setLoop: 1 setCel: 0 setCycle: CycleTo 5 1 self)
			)
			(3
				(wrap init:)
				(rameses setPri: 15 setCycle: CycleTo 0 1 self)
			)
			(4
				(rameses setPri: -1 setCycle: CycleTo 5 1 self)
			)
			(5
				(wrap setCel: (+ (wrap cel?) 1))
				(rameses setPri: 15 setCycle: CycleTo 0 1 self)
			)
			(6
				(rameses setPri: -1 setCycle: CycleTo 5 1 self)
			)
			(7
				(wrap setCel: (+ (wrap cel?) 1))
				(rameses setPri: 15 setCycle: CycleTo 0 1 self)
			)
			(8
				(rameses setPri: -1 setCycle: CycleTo 5 1 self)
			)
			(9
				(wrap setCel: (+ (wrap cel?) 1))
				(rameses setPri: 15 setCycle: CycleTo 0 1 self)
			)
			(10
				(rameses setPri: -1 setCycle: CycleTo 5 1 self)
			)
			(11
				(lauraWrap init:)
				(= ticks 120)
			)
			(12
				(narrator x: 59 y: 30)
				(messager say: 1 0 1 2)
				(= cycles 1)
			)
			(13 (= ticks 180))
			(14
				(= deathReason 7)
				(curRoom newRoom: 99)
				(self dispose:)
			)
		)
	)
)

(instance tut of Actor
	(properties
		x 141
		y 142
		view 716
		loop 1
		signal $4001
	)
)

(instance rameses of Actor
	(properties
		x 108
		y 142
		view 715
		signal $4000
	)
)

(instance sunnie1 of Prop
	(properties
		x 27
		y 181
		view 710
		loop 1
		signal $4000
	)
)

(instance sunnie2 of Prop
	(properties
		x 157
		y 189
		view 710
		loop 2
		signal $4000
	)
)

(instance sunnie3 of Prop
	(properties
		x 65
		y 181
		view 711
		loop 2
		signal $4000
	)
)

(instance sunnie4 of Prop
	(properties
		x 203
		y 190
		view 711
		loop 1
		signal $4000
	)
)

(instance sunnie5 of Prop
	(properties
		x 47
		y 188
		view 712
		loop 1
		signal $4000
	)
)

(instance sunnie6 of Prop
	(properties
		x 94
		y 179
		view 712
		loop 2
		signal $4000
	)
)

(instance sunnie7 of Prop
	(properties
		x 183
		y 188
		view 713
		loop 2
		signal $4000
	)
)

(instance sunnie8 of Prop
	(properties
		x 123
		y 187
		view 713
		loop 1
		signal $4000
	)
)

(instance sunnie9 of Prop
	(properties
		x 96
		y 189
		view 714
		loop 1
		signal $4000
	)
)

(instance sunnie10 of Prop
	(properties
		x 137
		y 183
		view 714
		loop 2
		signal $4000
	)
)

(instance wrap of View
	(properties
		x 119
		y 101
		view 715
		loop 4
		priority 11
		signal $0011
	)
)

(instance lauraWrap of View
	(properties
		x 70
		y 59
		view 715
		loop 2
		priority 15
		signal $4011
	)
)
