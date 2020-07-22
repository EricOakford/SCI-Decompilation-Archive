;;; Sierra Script 1.0 - (do not remove this comment)
(script# 240)
(include game.sh)
(use Main)
(use Procs)
(use HandsOffScript)
(use Motion)
(use Game)
(use Actor)

(public
	rm240 0
)

(local
	local0
	local1
	[local2 9] = [12 22 30 38 52 60 12 22 30]
	[local11 20]
)
(instance rm240 of Room
	(properties
		lookStr {You are in the circuit room.}
		picture 240
		style IRISOUT
	)
	
	(method (init)
		(Load VIEW 240)
		(super init: &rest)
		(c1 setCycle: Forward init:)
		(c2 setCycle: Forward init:)
		(c3 setCycle: Forward init:)
		(c4 setCycle: Forward init:)
		(c5 setCycle: Forward init:)
		(self setScript: demo1)
	)
	
	(method (doit)
		(super doit: &rest)
		(Palette 6 224 230 -1 234 254 1)
	)
)

(instance demo1 of HandsOffScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= register 0)
				(= ticks 150)
			)
			(1
				(= [local11 register]
					(DoDisplay
						{At last I know what makes my computer rock!}
						67
						80
						159
						70
						320
						28
						[local2 (mod register 6)]
					)
				)
				(= ticks 50)
			)
			(2
				(if (< (++ register) 9)
					(self changeState: 1)
				else
					(= local0 8)
					(while (>= local0 0)
						(DoDisplay [local11 local0])
						(-- local0)
					)
					(= ticks 1)
				)
			)
			(3 (curRoom newRoom: 380 8))
		)
	)
)

(instance c1 of Prop
	(properties
		x 16
		y 144
		view 240
	)
)

(instance c2 of Prop
	(properties
		x 295
		y 94
		view 240
		loop 1
		cel 13
	)
)

(instance c3 of Prop
	(properties
		x 307
		y 54
		view 240
		loop 2
		cel 3
	)
)

(instance c4 of Prop
	(properties
		x 285
		y 78
		view 240
		loop 3
		cel 7
	)
)

(instance c5 of Prop
	(properties
		x 18
		y 95
		view 240
		loop 4
	)
)
