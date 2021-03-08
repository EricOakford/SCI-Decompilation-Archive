;;; Sierra Script 1.0 - (do not remove this comment)
(script# 208)
(include sci.sh)
(use Main)
(use rm201)
(use sStopEggTimer)
(use eureka)
(use Scaler)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	sWD40Attacks 0
	sWD40Kills 1
	wd40Ship 2
)

(local
	local0
)
(instance explosionSound of Sound
	(properties)
)

(instance sWD40Attacks of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Load rsVIEW 207)
				(= seconds 1)
			)
			(1
				(ShakeScreen 5 3)
				(explosionSound number: 202 setLoop: 1 play:)
				(self setScript: (ScriptID 201 1) self)
				(Bclr 32)
			)
			(2 (proc201_6 self))
			(3 (= cycles 1))
			(4
				(messager say: 1 0 0 1 self 208)
			)
			(5
				(self setScript: (ScriptID 201 4))
				(proc201_7 self)
			)
			(6
				(messager say: 1 0 0 2 4 self 208)
			)
			(7
				(self setScript: (ScriptID 201 8) self)
			)
			(8
				(explosionSound number: 211 loop: -1 play:)
				(= cycles 1)
			)
			(9
				(= eurekaCurLocation 16)
				(eureka state: 2 curLocation: 3)
				(self setScript: (ScriptID 201 39) self)
			)
			(10
				(explosionSound stop:)
				(proc201_7 self)
			)
			(11
				(messager say: 1 0 0 5 self 208)
			)
			(12
				(self setScript: (ScriptID 201 8) self)
			)
			(13
				(eureka state: 3)
				((ScriptID 201 24) startUpd:)
				((ScriptID 201 34) startUpd:)
				((ScriptID 201 2) startUpd:)
				(self setScript: (ScriptID 201 36) self)
			)
			(14
				(wd40Ship init: addToPic:)
				(curRoom drawPic: 41 9)
				(theMusic1 number: 20 setLoop: -1 play:)
				(= seconds 2)
			)
			(15
				((ScriptID 201 9) addToPic:)
				(wd40Ship addToPic:)
				(torpedo init: ignoreActors: 1)
				((ScriptID 201 24) stopUpd:)
				((ScriptID 201 2) stopUpd:)
				(= cycles 2)
			)
			(16
				(torpedo setMotion: MoveTo 284 120 self)
				(theMusic2 number: 657 setLoop: 1 play:)
				(fireBall init:)
			)
			(17
				(= register -20)
				(while (<= register 100)
					(Palette palSET_INTENSITY 236 255 register)
					(if (>= register 100) (= cycles 1))
					(= register (+ register 40))
				)
				(torpedo dispose:)
				(ShakeScreen 3 3)
				(explosionSound number: 202 setLoop: 1 play:)
			)
			(18
				((ScriptID 202 13) init:)
				((ScriptID 202 14) init:)
				((ScriptID 202 15) init:)
				(= register 120)
				(while (>= register 0)
					(Palette palSET_INTENSITY 236 255 register)
					(if (<= register 0) (= cycles 1))
					(= register (- register 40))
				)
			)
			(19
				(fireBall dispose:)
				(= seconds 1)
			)
			(20 (= seconds 2))
			(21 (proc201_6 self))
			(22
				(messager say: 1 0 0 6 self 208)
			)
			(23
				(self setScript: (ScriptID 201 4) self)
			)
			(24
				(self setScript: (ScriptID 209 0) self 207)
				((ScriptID 202 13) dispose:)
				((ScriptID 202 14) dispose:)
				((ScriptID 202 15) dispose:)
				(theMusic2 number: 206 setLoop: -1 play:)
			)
			(25
				((ScriptID 201 15) talkWidth: 250)
				(messager say: 1 0 1 0 self 208)
			)
			(26
				((ScriptID 201 15) talkWidth: 0)
				(eureka state: 3 destination: 0 damaged: 1 hits: 1)
				(self setScript: (ScriptID 209 1) self)
			)
			(27 (proc201_7 self))
			(28
				(messager say: 2 0 0 1 self 208)
			)
			(29
				(self setScript: (ScriptID 201 8) self)
			)
			(30 (proc201_6 self))
			(31
				(messager say: 2 0 0 2 self 208)
			)
			(32
				(self setScript: (ScriptID 201 4) self)
			)
			(33
				(Palette palSET_INTENSITY 1 255 100)
				(eureka setScript: (ScriptID 210 6) 0 120)
				(++ global127)
				(Bset 106)
				(explosionSound dispose:)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWD40Kills of Script
	(properties)
	
	(method (doit)
		(switch state
			(4
				(if (>= local0 100) (self cue:))
			)
			(5
				(if (<= local0 0) (self cue:))
			)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (Btst 84)
					(self setScript: sRemoveEggTimer self)
				else
					(= seconds 1)
				)
				(= register 3)
			)
			(1
				(= seconds 1)
				(fireBall init: hide:)
			)
			(2
				(torpedo init: ignoreActors: 1)
				(= cycles 1)
			)
			(3
				(torpedo setMotion: MoveTo 284 120 self)
				(theMusic2 number: 657 setLoop: 1 play:)
				(fireBall show:)
			)
			(4
				(= local0 -20)
				(while (<= local0 100)
					(Palette palSET_INTENSITY 236 255 local0)
					(= local0 (+ local0 40))
				)
				(ShakeScreen 3 3)
				(theMusic2 number: 202 setLoop: 1 play:)
			)
			(5
				(= local0 100)
				(while (>= local0 0)
					(Palette palSET_INTENSITY 236 255 local0)
					(= local0 (- local0 40))
				)
			)
			(6
				(-- register)
				(if register (= state (- state 6)))
				(= cycles 2)
			)
			(7
				(fireBall dispose:)
				(torpedo dispose:)
				(= seconds 1)
			)
			(8
				(client setScript: (ScriptID 210 3) 0 6)
				(self dispose:)
			)
		)
	)
)

(instance sRemoveEggTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 5))
			(1 (proc204_2) (= cycles 1))
			(2
				(self setScript: (ScriptID 209 0) self 207)
			)
			(3
				(messager say: 3 0 0 1 self 208)
			)
			(4
				(self setScript: (ScriptID 209 1) self)
			)
			(5 (self dispose:))
		)
	)
)

(instance fireBall of View
	(properties
		x 221
		y 65
		view 221
		loop 4
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
		x 164
		y 60
		view 221
		loop 3
		priority 5
		signal $4010
	)
	
	(method (init)
		(self
			setLoop: 3
			setCel: 0
			x: 164
			y: 60
			setStep: 20 20
			moveSpeed: 0
			setScale: Scaler 100 20 97 60
		)
		(super init: &rest)
	)
)

(instance wd40Ship of View
	(properties
		x 19
		y 18
		view 221
		loop 2
		priority 6
		signal $4011
	)
	
	(method (init)
		(if (Btst 61) (super init: &rest) else (self dispose:))
	)
)
