;;; Sierra Script 1.0 - (do not remove this comment)
(script# 755)
(include sci.sh)
(use Main)
(use rm750)
(use ArrayScript)
(use LoadMany)
(use Motion)
(use System)

(public
	startFight 0
	noDagger 1
	cassimaHasDagger 2
	fightPart1 3
)

(local
	[local0 25] = [7504 4 0 158 136 -4095 3 1 -3968 750 1 0 -4064 2 -4095 8 1 -3968 750 1 0 -4064 2 -4094 -1]
	[local25 505] = [751 4 0 136 144 -4095 2 1 -3968 750 1 0 -4064 1 -4094 751 7 0 138 137 -3968 750 1 0 -4064 1 -4095 5 1 -3968 750 1 0 -4064 1 -4094 -3840 -1 6 23 15 3 -3840 -1 6 23 15 4 -3840 -1 6 23 15 5 7511 0 0 101 156 -4095 3 1 -16 128 751 -3968 750 1 0 -4064 1 -4094 7511 2 0 106 147 -4095 1 1 -3968 750 1 0 -4064 1 -4094 7511 1 0 93 151 -4095 3 1 -3968 756 1 0 -4094 7512 2 0 100 165 -4094 7512 0 0 131 171 -4095 1 1 -3968 756 1 0 -4094 7515 3 0 149 162 -4095 2 1 -3968 750 1 0 -4064 1 -4095 6 1 -3968 750 1 0 -4064 1 -4094 7515 0 0 157 170 -4095 1 1 -3968 750 1 0 -4064 1 -4094 7515 1 0 152 171 -4095 3 1 -3968 750 1 0 -4064 1 -4095 5 1 -3968 755 1 0 -4094 7515 2 0 152 174 -4095 3 1 -3968 755 1 0 -4095 8 1 -3968 755 1 0 -4094 7516 0 0 143 158 -4095 2 1 -3968 756 1 0 -4094 7516 4 0 174 151 -4094 7516 1 0 179 152 -4095 3 1 -3968 750 1 0 -4064 1 -4094 7517 1 0 171 131 -4095 2 1 -3968 750 1 0 -4064 1 -4094 7517 0 0 170 139 -4095 3 1 -3968 750 1 0 -4064 1 -4094 7511 0 0 170 158 -4095 3 1 -3968 750 1 0 -4064 1 -4094 7511 1 0 162 143 -4095 3 1 -3968 756 1 0 -4094 7512 0 0 169 156 -4095 1 1 -3968 756 1 0 -4094 7515 0 0 167 156 -4095 1 1 -3968 750 1 0 -4064 1 -4094 7515 1 0 161 158 -4095 3 1 -3968 750 1 0 -4064 1 -4095 5 1 -3968 755 1 0 -4094 7515 2 0 159 159 -4095 3 1 -3968 755 1 0 -4095 8 1 -3968 755 1 0 -4094 7516 0 0 150 143 -4095 2 1 -3968 756 1 0 -4094 7516 1 0 157 145 -4095 3 1 -3968 750 1 0 -4064 1 -4094 7517 0 0 156 144 -4095 3 1 -3968 750 1 0 -4064 1 -4094 7511 0 0 158 163 -4095 3 1 -3968 750 1 0 -4064 1 -4094 -3840 -1 6 23 15 6 7511 1 0 152 148 -4095 3 1 -3968 756 1 0 -4094 7512 0 0 159 162 -4095 1 1 -3968 756 1 0 -4094 -3840 -1 6 23 15 7 -1 -3840 -1 6 23 16 1 7512 1 0 135 171 -4095 3 1 -3968 750 1 0 -4064 1 -4094 -3840 -1 6 23 16 2 -3840 -1 6 23 16 3 7513 0 0 134 161 -4094 7513 1 0 134 161 -4094 -1]
	[local530 52] = [7514 0 0 153 150 -4095 2 1 -3968 751 1 0 -4094 -1 -4092 -3840 -1 1 0 6 1 -3840 -1 1 0 6 2 7513 0 7 159 151 -4094 -3840 -1 1 0 6 3 7513 1 11 159 151 -4094 -3840 -1 1 0 6 4 -1]
)
(instance startFight of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not register)
					(ego
						view: 751
						loop: 6
						cel: 0
						posn: 178 132
						setCycle: End self
					)
				else
					(self cue:)
				)
			)
			(1
				((ScriptID 750 1)
					add: -1 6 23 15 1
					add: -1 6 23 15 2
					init: self
				)
			)
			(2
				((ScriptID 750 3) dispose:)
				(if register
					(theIconBar disable: 6)
					(curRoom
						setScript: (lampStartScr next: fightPart1 yourself:)
					)
				else
					(= next fightPart1)
					(self dispose:)
				)
			)
		)
	)
)

(instance cassimaHasDagger of Script
	(properties)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 750 6)
					view: 753
					setLoop: 2
					setCycle: Walk
					posn: 28 160
					setStep: 5 2
					setMotion: MoveTo 131 160 self
				)
			)
			(1
				(if (curRoom script?) (-- state))
				(= cycles 2)
			)
			(2
				(ego
					view: 753
					loop: 0
					cel: 0
					posn: 159 149
					setCycle: CT 2 1 self
				)
			)
			(3
				(messager say: 6 23 15 8 self)
			)
			(4
				((ScriptID 750 6)
					view: 753
					loop: 1
					cel: 0
					posn: 140 153
					setCycle: CT 3 1 self
				)
			)
			(5
				(theGlobalSound number: 754 setLoop: 1 play:)
				(ego cel: 3)
				(= ticks 3)
			)
			(6
				((ScriptID 750 1)
					add: -1 6 23 15 9
					add: -1 6 23 15 10
					init: self
				)
			)
			(7
				(ego setCycle: End self)
				((ScriptID 750 6) setCycle: End (ScriptID 750 6))
			)
			(8
				(messager say: 6 23 15 11 self)
			)
			(9)
			(10 (= cycles 4))
			(11
				(proc750_5)
				(if (or (not howFast) (not (HaveMouse)))
					(= seconds 15)
				else
					(= seconds 8)
				)
			)
			(12 (proc750_5 1) (= cycles 1))
			(13
				(theGame handsOff:)
				(ego setCycle: Beg self)
			)
			(14
				(messager say: 1 0 6 1 self)
			)
			(15
				(messager say: 1 0 6 2 self)
			)
			(16
				(curRoom setScript: noDagger 0 1)
				(self dispose:)
			)
			(26
				(proc750_5 1)
				(theGame givePoints: 5)
				(= cycles 1)
			)
			(27
				(theGame handsOff:)
				(ego
					view: 7514
					setLoop: 0
					cel: 0
					posn: 153 150
					setCycle: CT 2 1 self
				)
			)
			(28
				(theMusic number: 0 stop:)
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 751 setLoop: 1 play:)
				(= cycles 3)
			)
			(29
				(theMusic number: 752 setLoop: -1 play:)
				(ego setCycle: End self)
			)
			(30
				(ego setLoop: 1 cel: 0 setCycle: CT 7 1 self)
			)
			(31 (ego setCycle: End self))
			(32
				((ScriptID 750 3)
					init:
					view: 7514
					setLoop: 2
					posn: 153 150
					ignoreActors: 1
					addToPic:
				)
				((ScriptID 750 2) dispose:)
				(ego
					oldScaleSignal: 0
					view: 900
					loop: 9
					cel: 2
					setLoop: -1
					setPri: -1
					scaleSignal: 1
					scaleX: 96
					scaleY: 96
					posn: 164 146
					cycleSpeed: 6
					moveSpeed: 6
					ignoreActors: 1
					normal: 1
					setCycle: 0
				)
				(= cycles 2)
			)
			(33
				(ego
					setLoop: -1
					setCycle: Walk
					setMotion: MoveTo 120 143 self
				)
			)
			(34
				(ego
					setLoop: 5
					setMotion:
						MoveTo
						((ScriptID 750 6) x?)
						(- ((ScriptID 750 6) y?) 2)
						self
				)
			)
			(35
				(ego setLoop: 9 cel: 2)
				(= ticks 30)
			)
			(36
				(ego
					view: 758
					setLoop: 0
					cel: 0
					posn: ((ScriptID 750 6) x?) ((ScriptID 750 6) y?)
					normal: 0
				)
				((ScriptID 750 6) hide:)
				(= ticks 30)
			)
			(37
				((ScriptID 750 1)
					add: -1 6 23 10 4
					add: -1 6 23 10 5
					add: -1 6 23 10 6
					add: -1 6 23 10 7
					init: self
				)
			)
			(38 (ego setCycle: End self))
			(39
				((ScriptID 750 1)
					add: -1 6 23 10 8
					add: -1 6 23 10 9
					add: -1 6 23 10 10
					add: -1 6 23 10 11
					init: self
				)
			)
			(40
				(ego setMotion: 0 setCycle: 0)
				(theIconBar enable: 6)
				(curRoom newRoom: 180)
			)
		)
	)
	
	(method (handleEvent event)
		(if
		(and (== state 11) (user canInput:) (ego onMe: event))
			(event claimed: 1)
			(theGame handsOff:)
			(self state: 25 seconds: 0 cycles: 2)
		)
		(event claimed?)
	)
)

(instance startEndingCartoon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(theIconBar enable: 6)
				(= cycles 2)
			)
			(1 (= cycles 2))
			(2 (curRoom newRoom: 740))
		)
	)
)

(class SwordArrayScript of ArrayScript
	(properties
		client 0
		state $ffff
		start 1
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		value 0
	)
	
	(method (play param1 param2 param3)
		(soundFx number: 0 stop:)
		(soundFx number: param1 setLoop: param2 play:)
		(if param3 (soundFx client: param3) else (= cycles 1))
	)
)

(instance lampStartScr of SwordArrayScript
	(properties)
	
	(method (at param1)
		(return [local0 param1])
	)
)

(instance fightPart1 of SwordArrayScript
	(properties)
	
	(method (dispose)
		(theIconBar enable: 6)
		(super dispose: &rest)
	)
	
	(method (at param1)
		(LoadMany 0 751 7511 7512 7515 7516 7517)
		(return [local25 param1])
	)
)

(instance knockOutVizier of SwordArrayScript
	(properties)
	
	(method (at param1)
		(return [local530 param1])
	)
)

(instance noDagger of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(proc750_5 1)
				(theMusic number: 0 stop:)
				(theMusic number: 705 setLoop: 1 play:)
				(= cycles 1)
			)
			(1
				(theGame handsOff:)
				(if (not register)
					(messager say: 6 23 16 1 self)
				else
					(= cycles 2)
				)
			)
			(1
				(ego view: 755 posn: 151 154 setLoop: 0 cel: 6)
				(= cycles 8)
			)
			(2 (ego cel: 5) (= cycles 8))
			(3
				(ego
					posn: 165 163
					view: 7504
					setLoop: 6
					cel: 0
					setCycle: CT 2 1 self
				)
			)
			(4
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 756 setLoop: 1 play:)
				(ego setCycle: End self)
			)
			(5
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 971 setLoop: 1 play: self)
			)
			(6
				(if (not register)
					((ScriptID 750 1)
						add: -1 6 23 16 2
						add: -1 6 23 16 3
						init: self
					)
				else
					(= cycles 2)
				)
			)
			(7
				(if register
					(messager say: 1 0 6 3 self)
				else
					(= cycles 2)
				)
			)
			(8
				(ego
					view: 7513
					posn: 161 150
					setLoop: 0
					cel: 0
					setCycle: End self
				)
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 652 setLoop: 1 play:)
			)
			(9
				(ego setLoop: 1 cel: 0 setCycle: CT 6 1 self)
			)
			(10
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 652 setLoop: 1 play:)
				(ego setCycle: End self)
			)
			(11
				(if register
					(messager say: 1 0 6 4 self)
				else
					(= cycles 2)
				)
			)
			(12
				(if (== ((inventory at: 8) owner?) 870)
					(EgoDead 41)
				else
					(EgoDead 40)
				)
			)
		)
	)
)
