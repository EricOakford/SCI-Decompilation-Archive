;;; Sierra Script 1.0 - (do not remove this comment)
(script# 401)
(include game.sh) (include "400.shm")
(use Main)
(use GloryTalker)
(use Motion)
(use Actor)
(use System)

(public
	ComeOnDown 0
	laurelTalker 1
	hardyTalker 2
)

(local
	oldCur
)
(instance ComeOnDown of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0
				(HandsOff)
				(theIconBar disable:)
				(= oldCur (theGame setCursor: INVIS_CURSOR TRUE))
				(SetCursor 0)
				(hardy
					init:
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 7
					setMotion: MoveTo 143 119 self
				)
				(hardyTalker init:)
				(laurelTalker init:)
				(laurel
					init:
					setLoop: 0
					setCycle: Walk
					cycleSpeed: 6
					moveSpeed: 7
					setMotion: MoveTo 181 125
				)
			)
			(1
				(messager say: N_COME_ON_DOWN V_DOIT C_LAUREL_HARDY1 0 self 400)
			)
			(2
				(= cycles 20)
			)
			(3
				(laurel cycleSpeed: 10 loop: 1 cel: 0)
				(= cycles 20)
			)
			(4
				(hardy cycleSpeed: 10 loop: 1 cel: 0)
				(= cycles 20)
			)
			(5
				(hardy setCycle: EndLoop self)
			)
			(6
				(= cycles 20)
			)
			(7
				(hardy loop: 2 cel: 0)
				(messager say: N_COME_ON_DOWN V_DOIT C_LAUREL_HARDY2 0 self 400)
			)
			(8
				(hardy setCycle: EndLoop self)
			)
			(9
				(= cycles 20)
			)
			(10
				(hardy loop: 3 cel: 0)
				(messager say: N_COME_ON_DOWN V_DOIT C_LAUREL_HARDY3 1 self 400)
			)
			(11
				(laurel setCycle: EndLoop self)
			)
			(12
				(= cycles 20)
			)
			(13
				(messager say: N_COME_ON_DOWN V_DOIT C_LAUREL_HARDY3 2 self 400)
			)
			(14
				(hardy setCycle: EndLoop self)
			)
			(15
				(= cycles 20)
			)
			(16
				(messager say: N_COME_ON_DOWN V_DOIT C_LAUREL_HARDY4 0 self 400)
			)
			(17
				(laurel setCycle: BegLoop self)
			)
			(18
				(hardy
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo -15 123
				)
				(laurel
					setLoop: 0
					cel: 0
					cycleSpeed: 6
					setCycle: Walk
					setMotion: MoveTo -15 133 self
				)
			)
			(19
				(Face ego laurel self)
			)
			(20
				(= cycles 2)
			)
			(21
				(messager say: N_EGO_TELL V_DOIT C_FAMILIAR 0 self 400)
			)
			(22
				(SetCursor 1)
				(theGame setCursor: oldCur TRUE)
				(theIconBar enable:)
				(laurel dispose:)
				(laurelTalker dispose:)
				(hardy dispose:)
				(hardyTalker dispose:)
				(self dispose:)
			)
		)
	)
)

(instance laurel of Actor
	(properties
		x 352
		y 116
		view 408
		signal ignrAct
		origStep 1028
	)
)

(instance laurelTalker of GloryTalker
	(properties
		x 1
		y 1
		view 409
		loop 1
		talkWidth 150
		back 57
		textX 137
		textY 5
	)
	
	(method (init)
		(super init: laurelBrow laurelEyes laurelMouth &rest)
	)
)

(instance laurelEyes of Prop
	(properties
		nsTop 38
		nsLeft 38
		view 409
		loop 2
	)
)

(instance laurelBrow of Prop
	(properties
		nsTop 29
		nsLeft 37
		view 409
		loop 3
	)
)

(instance laurelMouth of Prop
	(properties
		nsTop 54
		nsLeft 38
		view 409
		cel 2
	)
)

(instance hardy of Actor
	(properties
		x 325
		y 106
		view 406
		signal ignrAct
		origStep 1028
	)
)

(instance hardyTalker of GloryTalker
	(properties
		x 1
		y 1
		view 407
		loop 1
		talkWidth 150
		back 57
		textX 137
		textY 5
	)
	
	(method (init)
		(super init: hardyBrow hardyEyes hardyMouth &rest)
	)
	
	(method (show)
		(bust cel: (Random 0 1))
		(super show: &rest)
	)
)

(instance hardyEyes of Prop
	(properties
		nsTop 36
		nsLeft 36
		view 407
		loop 2
		cel 2
	)
)

(instance hardyBrow of Prop
	(properties
		nsTop 28
		nsLeft 31
		view 407
		loop 3
		cel 1
	)
)

(instance hardyMouth of Prop
	(properties
		nsTop 51
		nsLeft 26
		view 407
		cel 8
	)
)
