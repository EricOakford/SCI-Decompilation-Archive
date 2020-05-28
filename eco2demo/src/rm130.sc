;;; Sierra Script 1.0 - (do not remove this comment)
(script# 130)
(include game.sh)
(use Main)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm130 0
)

(local
	[str 200]
	saveBits
	saveBits2
)
(instance rm130 of Room
	(properties
		picture 888
	)
	
	(method (init)
		(super init: &rest)
		(self setScript: demoRm130Scr)
		(theMusic number: 632 loop: -1 play:)
	)
	
	(method (dispose)
		(theMusic fade:)
		(super dispose:)
	)
)

(instance demoRm130Scr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1 (= ticks 220))
			(2
				(cFace init:)
				(cMouth init:)
				(cEyes init:)
				(= ticks 60)
			)
			(3
				(Message MsgGet 105 0 0 5 1 @str)
				(= saveBits
					(Display
						@str
						p_at teJustLeft
						p_at 26 140
						p_font userFont
						p_color 68
						p_width 128
						p_save
					)
				)
				(cMouth setCycle: Forward)
				(= ticks 20)
			)
			(4 (cEyes setCycle: EndLoop self))
			(5
				(cEyes dispose:)
				(= ticks 180)
			)
			(6
				(cMouth dispose:)
				(= cycles 2)
			)
			(7
				(aFace init:)
				(aEyes init:)
				(aMouth init:)
				(= ticks 30)
			)
			(8
				(Message MsgGet 105 0 0 6 1 @str)
				(= saveBits2
					(Display @str
						p_mode teJustLeft
						p_at 170 140
						p_font userFont
						p_color 68
						p_width 110
						p_save
					)
				)
				(aMouth setCycle: Forward)
				(= ticks 40)
			)
			(9 (aEyes setCycle: EndLoop self))
			(10 (aEyes setCycle: BegLoop self))
			(11
				(aEyes dispose:)
				(= ticks 160)
			)
			(12
				(aMouth dispose:)
				(= cycles 2)
			)
			(13
				(theMusic fade:)
				(= ticks 120)
			)
			(14
				(aFace dispose:)
				(cFace dispose:)
				(= cycles 2)
			)
			(15
				(Display {} p_restore saveBits)
				(Display {} p_restore saveBits2)
				(= cycles 2)
			)
			(16 (curRoom newRoom: 140))
		)
	)
)

(instance cFace of View
	(properties
		x 51
		y 47
		view 1670
		loop 1
	)
)

(instance cEyes of Prop
	(properties
		x 87
		y 79
		view 1670
		loop 2
	)
)

(instance cMouth of Actor
	(properties
		x 80
		y 48
		view 1670
	)
)

(instance aFace of View
	(properties
		x 191
		y 47
		view 1671
		loop 1
	)
)

(instance aMouth of Prop
	(properties
		x 209
		y 63
		view 1671
	)
)

(instance aEyes of Prop
	(properties
		x 212
		y 85
		view 1671
		loop 2
	)
)
