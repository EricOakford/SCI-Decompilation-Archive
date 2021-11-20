;;; Sierra Script 1.0 - (do not remove this comment)
(script# 180)
(include sci.sh)
(use Main)
(use GloryRm)
(use EgoDead)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm180 0
)

(local
	local0
)
(instance rm180 of GloryRm
	(properties
		picture 180
	)
	
	(method (init)
		(Bclr 6)
		(theIconBar disable:)
		(super init: &rest)
		(theGame handsOff:)
		(darkOne init:)
		(curRoom setScript: sRise)
		(theMusic number: 180 setLoop: 1 play:)
	)
	
	(method (dispose)
		(theIconBar enable:)
		(Bset 6)
		(super dispose: &rest)
	)
)

(instance darkOne of Prop
	(properties
		x 158
		y 81
		view 180
		signal $6000
		cycleSpeed 10
	)
)

(instance sRise of Script
	(properties)
	
	(method (doit)
		(super doit: &rest)
		(switch local0
			(5
				(cond 
					(register (Palette palSET_FLAG 0 255 (= register (- register 2))))
					((Btst 316) (curRoom newRoom: 780))
					(else (curRoom newRoom: 100))
				)
			)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(= seconds 2)
				(soundFX number: 978 setLoop: -1 play:)
			)
			(1
				(if (Btst 316) (Palette palSET_FLAG 0 255 100))
				(= seconds 3)
			)
			(2
				(PalVary pvINIT 180 10)
				(windFX number: 983 setLoop: -1 play:)
				(= seconds 10)
			)
			(3
				(soundFX number: 974 setLoop: 1 play:)
				(darkOne
					signal: (| (darkOne signal?) $0001)
					setCycle: End self
				)
			)
			(4
				(darkOne setLoop: 1 setCel: 0 setCycle: End self)
			)
			(5
				(soundFX number: 975 setLoop: 1 play:)
				(darkOne
					view: 181
					setLoop: 0
					setCel: 0
					setCycle: End self
				)
			)
			(6
				(darkOne view: 182 setCel: 0 setCycle: End self)
			)
			(7
				(darkOne view: 183 setCel: 0 setCycle: End self)
			)
			(8
				(darkOne view: 184 setCel: 0 setCycle: End self)
			)
			(9
				(darkOne view: 185 setCel: 0 setCycle: End self)
			)
			(10
				(if (== prevRoomNum 730)
					(EgoDead 1 180)
					(= register 0)
					(= local0 5)
				else
					(darkOne
						view: 186
						setLoop: 0
						setCel: 0
						setCycle: End self
					)
				)
			)
			(11
				(theMusic fade:)
				(soundFX number: 974 play:)
				(RemapColors 0)
				(Palette palSET_FLAG 0 255 1500)
				(= ticks 1)
			)
			(12
				(Palette palSET_FLAG 0 255 100)
				(= ticks 24)
			)
			(13
				(Palette palSET_FLAG 0 255 1500)
				(= ticks 1)
			)
			(14
				(Palette palSET_FLAG 0 255 100)
				(= ticks 1)
			)
			(15
				(= register 100)
				(= local0 5)
			)
			(16
				(= local0 2)
				(soundFX number: 974 play:)
			)
			(17
				(= register 50)
				(= local0 3)
			)
			(18
				(= local0 4)
				(soundFX stop:)
				(theMusic2 number: 974 setLoop: 1 play:)
			)
			(19
				(= register 30)
				(= local0 5)
			)
		)
	)
)

(instance soundFX of Sound
	(properties)
)

(instance windFX of Sound
	(properties)
)
