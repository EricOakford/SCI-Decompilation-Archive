;;; Sierra Script 1.0 - (do not remove this comment)
(script# 221)
(include sci.sh)
(use Main)
(use rm201)
(use eureka)
(use Scaler)
(use Osc)
(use Reverse)
(use ScaleTo)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	asteroids 0
	asteroid1 1
	asteroid2 2
	asteroid3 3
	asteroid4 4
	sGoliathAttacks 5
	sInTheAsteroids 6
)

(local
	local0
	local1
	local2
)
(instance staticSound of Sound
	(properties
		number 222
	)
)

(instance sInTheAsteroids of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
				(theGame handsOff:)
			)
			(1
				(= global142 2)
				(= seconds 1)
			)
			(2 (proc201_6 self))
			(3 (= cycles 2))
			(4
				(messager say: 17 0 16 1 self 202)
			)
			(5
				(self setScript: (ScriptID 201 4) self)
			)
			(6
				(self setScript: (ScriptID 209 0) self 204)
				(theMusic1 number: 35 loop: -1 play:)
			)
			(7
				(messager say: 17 0 0 0 self 202)
			)
			(8
				(self setScript: (ScriptID 209 1) self)
			)
			(9
				(theMusic1 number: 101 loop: -1 play: 127)
				(= seconds 3)
			)
			(10
				((ScriptID 202 13) init:)
				(= cycles 1)
			)
			(11
				(messager say: 18 0 0 0 self 202)
			)
			(12
				((ScriptID 202 13) dispose:)
				(= seconds 4)
			)
			(13
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(14
				(staticSound stop:)
				(messager say: 18 0 17 1 self 202)
			)
			(15
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(16
				(staticSound stop:)
				(= seconds 3)
			)
			(17
				(theMusic2 number: 233 setLoop: 1 play: self)
			)
			(18
				(theMusic2 stop:)
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(19
				(staticSound stop:)
				(messager say: 18 0 17 2 self 202)
				(= register 5)
			)
			(20 (= cycles 1))
			(21
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(22
				(staticSound stop:)
				(theMusic2 number: 232 loop: -1 play:)
				(= seconds 3)
			)
			(23
				(theMusic2 stop:)
				(staticSound loop: 1 play:)
				(= ticks 20)
			)
			(24
				(staticSound stop:)
				(messager say: 18 0 17 3 self 202)
			)
			(25 (= cycles 1))
			(26
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(27
				(staticSound stop:)
				(theMusic2 number: 230 setLoop: -1 play:)
				(= seconds 4)
			)
			(28
				(theMusic2 stop:)
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(29
				(staticSound stop:)
				(messager say: 18 0 17 4 self 202)
				(Palette palSET_INTENSITY 0 255 50)
				(= register 10)
			)
			(30 (= cycles 1))
			(31
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(32
				(staticSound stop:)
				(theMusic2 number: 231 loop: -1 play:)
				(= seconds 3)
			)
			(33
				(theMusic2 stop:)
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(34
				(staticSound stop:)
				(messager say: 18 0 17 5 self 202)
				(Palette palSET_INTENSITY 1 255 100)
			)
			(35 (= cycles 1))
			(36
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(37
				(staticSound stop:)
				(messager say: 18 0 17 6 self 202)
			)
			(38 (= cycles 1))
			(39
				(staticSound loop: 1 play:)
				(= ticks 10)
			)
			(40
				(ShakeScreen 3 1)
				(staticSound dispose:)
				(theMusic2 number: 233 setLoop: 1 play:)
				(eureka damaged: 0 hits: 0)
				(= global130 0)
				(= cycles 1)
			)
			(41
				(theMusic1 number: 101 loop: -1 play:)
				(= cycles 1)
			)
			(42
				(theMusic2 number: 206 loop: -1 play:)
				(cliffy init: setMotion: MoveTo 133 79 self)
			)
			(43
				(cliffy dispose:)
				(proc201_7 self)
			)
			(44
				(messager say: 18 0 59 0 self 202)
			)
			(45
				(self setScript: (ScriptID 201 8) self)
			)
			(46
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoliathAttacks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theMusic1 number: 38 loop: -1 play:)
				(= cycles 1)
			)
			(1
				(goliath setScript: sGoliathFires self)
			)
			(2 0)
			(3
				(cond 
					((== (eureka hits?) 1) (self setScript: sGoliathComments self))
					((and (== (eureka hits?) 2) (Btst 40)) (= cycles 1))
					(else
						(curRoom setScript: (ScriptID 210 3) 0 12)
						(self dispose:)
					)
				)
			)
			(4
				(eureka setScript: 0)
				(eureka setScript: (ScriptID 210 1) 0 30)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sGoliathComments of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (proc201_6 self))
			(1
				(messager say: 13 0 13 1 self 202)
			)
			(2
				(self setScript: (ScriptID 201 4) self)
				(proc201_7)
			)
			(3
				(messager say: 13 0 6 0 self 202)
			)
			(4
				(self setScript: (ScriptID 201 8) self)
			)
			(5 (self dispose:))
		)
	)
)

(instance sGoliathFires of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(goliath init: setScale: ScaleTo 127 4 self)
				(torpedo init: hide:)
			)
			(1
				(torpedo setScript: sFireBall sGoliathAttacks)
				(= cycles 2)
			)
			(2
				(goliath setScale: 0 setCycle: End self)
			)
			(3
				(goliath moveSpeed: 0 setMotion: MoveTo 110 40 self)
			)
			(4
				(goliath dispose:)
				(self dispose:)
			)
		)
	)
)

(instance sFireBall of Script
	(properties)
	
	(method (doit)
		(switch state
			(2
				(if (>= register 100) (self cue:))
			)
			(3
				(if (<= register 0) (self cue:))
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(torpedo show: setMotion: MoveTo 246 27 self)
				(= cycles 2)
			)
			(1
				(theMusic2 number: 657 setLoop: 1 play:)
				(fireBall init:)
			)
			(2
				(= register -20)
				(while (<= register 100)
					(Palette palSET_INTENSITY 236 255 register)
					(= register (+ register 40))
				)
				(ShakeScreen 3 3)
				(theMusic2 number: 202 setLoop: 1 play:)
			)
			(3
				(= register 100)
				(while (>= register 0)
					(Palette palSET_INTENSITY 236 255 register)
					(= register (- register 20))
				)
			)
			(4
				(eureka damaged: 1 hits: (+ (eureka hits?) 1))
				(torpedo dispose:)
				(fireBall dispose:)
				(self dispose:)
			)
		)
	)
)

(instance asteroids of View
	(properties
		view 224
		loop 2
		priority 5
		signal $4010
	)
	
	(method (init)
		(switch (eureka state?)
			(2
				(= local0 45)
				(= local1 10)
				(self x: (+ 130 local0) y: (+ 75 local1))
			)
			(3
				(= local1 20)
				(= local0 90)
				(if (== eurekaCurLocation 16)
					(self x: (+ -252 local0) y: (+ 58 local1))
				else
					(self x: (+ 48 local0) y: (+ 58 local1))
				)
			)
		)
		(self
			setLoop: 2
			setCel: 0
			scaleSignal: 1
			scaleX: ((ScriptID 201 9) scaleX?)
			scaleY: ((ScriptID 201 9) scaleY?)
			maxScale: ((ScriptID 201 9) maxScale?)
			signal: 24592
		)
		(super init: &rest)
	)
	
	(method (doit)
		(self
			x: (+ ((ScriptID 201 9) x?) local0)
			y: (+ ((ScriptID 201 9) y?) local1)
			scaleX: ((ScriptID 201 9) scaleX?)
			scaleY: ((ScriptID 201 9) scaleY?)
			maxScale: ((ScriptID 201 9) maxScale?)
		)
		(super doit: &rest)
	)
)

(instance asteroid1 of Actor
	(properties
		x 94
		y 87
		view 220
		priority 5
		signal $4010
	)
	
	(method (init)
		(self setCycle: Fwd cycleSpeed: 18)
		(super init: &rest)
	)
	
	(method (doit)
		(self x: (+ ((ScriptID 201 9) x?) 40))
		(super doit: &rest)
	)
)

(instance asteroid2 of Actor
	(properties
		x 233
		y 74
		view 220
		loop 1
		cel 7
		priority 5
		signal $4010
	)
	
	(method (init)
		(self setCycle: Rev cycleSpeed: 16)
		(super init: &rest)
	)
	
	(method (doit)
		(self x: (+ ((ScriptID 201 9) x?) 185))
		(super doit: &rest)
	)
	
	(method (dispose)
		(theMusic2 stop:)
		(super dispose: &rest)
	)
)

(instance asteroid3 of Actor
	(properties
		x 166
		y 95
		view 220
		loop 2
		cel 7
		priority 5
		signal $4010
	)
	
	(method (doit)
		(self
			x: (+ ((ScriptID 201 9) x?) 118)
			cel: (asteroid2 cel?)
		)
		(super doit: &rest)
	)
)

(instance asteroid4 of Actor
	(properties
		x 208
		y 103
		view 220
		loop 3
		cel 8
		priority 5
		signal $4010
	)
	
	(method (doit)
		(self
			x: (+ ((ScriptID 201 9) x?) 160)
			cel: (asteroid1 cel?)
		)
		(super doit: &rest)
	)
)

(instance fireBall of View
	(properties
		x 183
		y 39
		view 224
		loop 5
		priority 5
		signal $4011
	)
	
	(method (init)
		(super init: &rest)
		(Palette palSET_INTENSITY 236 255 0)
	)
)

(instance torpedo of Actor
	(properties
		view 224
		loop 4
		priority 6
		signal $4010
	)
	
	(method (init)
		(self
			setLoop: 4
			setCel: 0
			x: 148
			y: 67
			scaleSignal: 1
			setStep: 20 20
			moveSpeed: 0
			ignoreActors: 1
			setScale: Scaler 20 100 67 37
		)
		(super init: &rest)
	)
)

(instance cliffy of Actor
	(properties
		view 2201
		signal $6010
	)
	
	(method (init)
		(self
			setLoop: 4
			x: 56
			y: 187
			setPri: 5
			setCycle: Osc
			setSpeed: 7
			setScale: Scaler 100 5 134 79
		)
		(super init: &rest)
	)
)

(instance goliath of Actor
	(properties
		view 224
		loop 3
		priority 5
		signal $6010
	)
	
	(method (init)
		(self
			x: 135
			y: 65
			setLoop: 3
			setSpeed: 2
			setStep: 10 10
			ignoreActors: 1
			scaleSignal: 1
			scaleX: 8
			scaleY: 8
			maxScale: 8
		)
		(super init: &rest)
	)
)
