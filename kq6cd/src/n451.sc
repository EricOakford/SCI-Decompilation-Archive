;;; Sierra Script 1.0 - (do not remove this comment)
(script# 451)
(include sci.sh)
(use Main)
(use Gnome)
(use Kq6Procs)
(use Conv)
(use Scaler)
(use PolyPath)
(use LoadMany)
(use Motion)
(use Actor)
(use System)

(public
	proc451_0 0
)

(procedure (proc451_0 param1)
	(curRoom setScript: boreTheOyster 0 param1)
)

(instance boreTheOyster of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(messager say: 1 42 5 1 self 450)
				else
					(messager say: 1 42 1 1 self 451)
				)
			)
			(1
				(messager say: 1 42 1 2 self 451)
			)
			(2
				(ego setMotion: PolyPath 132 141 self)
			)
			(3
				(Bset 59)
				(ego
					view: 451
					loop: 0
					cel: 0
					posn: 115 143
					normal: 0
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(4 (= cycles 3))
			(5
				(ego
					loop: 2
					cel: 0
					posn: 104 146
					cycleSpeed: 6
					setCycle: End self
				)
			)
			(6
				(OI init:)
				((ScriptID 450 1) hide: view: 2002)
				(ego dispose:)
				(= seconds 3)
			)
			(7
				(theGame handsOn:)
				(theIconBar disable: 0 3 4 5)
				(book setCycle: End self)
			)
			(8 (book cel: 0) (= cycles 3))
			(9
				(messager say: 1 42 1 4 self 451)
			)
			(10 (book setCycle: End self))
			(11 (book cel: 0) (= cycles 3))
			(12
				(pearl y: 184 z: 80)
				(soundFx number: 961 setLoop: 1 play:)
				(insetOyster
					view: 4532
					setLoop: 0
					cycleSpeed: 1
					setCycle: End
				)
				(= seconds 2)
			)
			(13
				(if modelessDialog (modelessDialog dispose:))
				(insetOyster setCycle: Beg self)
			)
			(14
				(pearl y: 104 z: 0)
				(insetOyster view: 453 setLoop: 1 cel: 0)
				(= seconds 2)
			)
			(15 (book setCycle: End self))
			(16 (book cel: 0) (= cycles 3))
			(17
				(messager say: 1 42 1 6 self 451)
			)
			(18
				(pearl y: 184 z: 80)
				(soundFx number: 961 setLoop: 1 play:)
				(insetOyster view: 4532 setLoop: 0 setCycle: End)
				(= seconds 3)
			)
			(19
				(if modelessDialog (modelessDialog dispose:))
				(insetOyster setCycle: Beg self)
			)
			(20
				(pearl y: 104 z: 0)
				(insetOyster view: 453 setLoop: 1)
				(= seconds 2)
			)
			(21 (book setCycle: End self))
			(22 (book cel: 0) (= cycles 3))
			(23
				(messager say: 1 42 1 8 self 451)
			)
			(24
				(pearl y: 184 z: 80)
				(soundFx number: 961 setLoop: 1 play:)
				(insetOyster view: 4532 setLoop: 0 setCycle: End)
				(= seconds 6)
			)
			(25
				(if modelessDialog (modelessDialog dispose:))
				(insetOyster setCycle: Beg self)
			)
			(26
				(pearl y: 104 z: 0)
				(insetOyster view: 453 setLoop: 1)
				(= seconds 1)
			)
			(27
				(theGame handsOff:)
				(book setCycle: End self)
			)
			(28 (book cel: 0) (= cycles 3))
			(29
				(soundFx setLoop: 5 play:)
				(insetOyster
					view: 4533
					cycleSpeed: 0
					setLoop: 5
					setCycle: Fwd
				)
				(= seconds 5)
			)
			(30
				(if modelessDialog (modelessDialog dispose:))
				(messager say: 1 42 1 11 self 451)
			)
			(31
				(OI dispose:)
				((ScriptID 450 1)
					view: 4531
					setLoop: 2
					show:
					cycleSpeed: 50
					setCycle: End
				)
				(ego
					view: 451
					loop: 2
					cel: 2
					posn: 104 146
					cycleSpeed: 6
					normal: 0
					setScale: Scaler 100 30 126 70
					init:
				)
				(= cycles 4)
			)
			(32
				(messager say: 1 42 1 12 self 451)
			)
			(33
				(ego
					view: 451
					loop: 0
					cel: 7
					posn: 115 143
					setCycle: Beg self
				)
			)
			(34
				(ego reset: 1 posn: 132 141)
				(curRoom notify:)
				((ScriptID 40 0) oysterDozing: 1)
				(= cycles 6)
			)
			(35
				(Bclr 59)
				(theGame handsOn:)
				(DisposeScript 1038)
				(proc450_8 0)
				(= cycles 4)
			)
			(36
				(self dispose:)
				(DisposeScript 451)
			)
		)
	)
)

(instance grabPearl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame givePoints: 1)
				(insetOyster setCycle: 0)
				(book dispose:)
				(= ticks 4)
			)
			(1
				(messager say: 4 5 0 1 self 451)
			)
			(2
				(arm init: setCycle: CT 1 1 self)
			)
			(3
				(pearl dispose:)
				(arm setCycle: End self)
			)
			(4
				(arm dispose:)
				(insetOyster setCycle: Beg self)
			)
			(5
				(insetOyster
					view: 4533
					cycleSpeed: 3
					setLoop: 5
					setCycle: Fwd
				)
				(= seconds 3)
			)
			(6
				(OI dispose:)
				(Bclr 59)
				((ScriptID 450 1)
					view: 4531
					setLoop: 2
					cel: 0
					show:
					cycleSpeed: 50
					setCycle: End
				)
				(ego
					view: 451
					loop: 2
					cel: 2
					posn: 104 146
					cycleSpeed: 6
					normal: 0
					cycleSpeed: 6
					get: 30
					init:
					setScale: Scaler 100 30 126 70
					setCycle: Beg self
				)
			)
			(7
				(messager say: 4 5 0 2 self 451)
			)
			(8
				(soundFx number: 963 setLoop: 1 play:)
				(curRoom notify:)
				((ScriptID 40 0) oysterDozing: 1)
				(ego
					view: 451
					loop: 0
					cel: 7
					posn: 115 143
					cycleSpeed: 6
					setCycle: Beg self
				)
			)
			(9
				(ego reset: 1 posn: 132 141)
				(= cycles 6)
			)
			(10
				(messager say: 4 5 0 3 self 451)
			)
			(11
				(theGame handsOn:)
				(soundFx setLoop: 5 play:)
				(proc450_8 0)
				(= cycles 4)
			)
			(12
				(self dispose:)
				(LoadMany 0 1038 451)
			)
		)
	)
)

(instance almostGotScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(arm init: setCycle: CT 1 1 self)
			)
			(1 (arm setCycle: Beg self))
			(2 (arm dispose:) (= cycles 2))
			(3
				(messager say: 3 5 2 1 self 451)
			)
			(4
				(theGame handsOn:)
				(theIconBar disable: 0 3 4 5)
				(insetOyster view: 453 setLoop: 1 cel: 0)
				(boreTheOyster start: 20)
				(curRoom setScript: boreTheOyster)
				(self dispose:)
			)
		)
	)
)

(instance myConv of Conversation
	(properties)
)

(instance OI of View
	(properties
		x 122
		y 113
		noun 2
		view 453
		priority 11
		signal $4010
	)
	
	(method (init)
		(theGlobalSound number: 450 setLoop: -1 play:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(book init:)
		(pearl init:)
		(botShell init:)
		(insetOyster init:)
		(super init:)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(curRoom cue:)
		(theGlobalSound fade: 0 10 10)
		(book dispose:)
		(insetOyster dispose:)
		(botShell dispose:)
		(pearl dispose:)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if
			(and
				(not (event modifiers?))
				(or
					(& (event type?) evMOUSEBUTTON)
					(and
						(& (event message?) KEY_RETURN)
						(& (event type?) evKEYBOARD)
					)
				)
				(not prints)
				(not fastCast)
				(!= (event type?) evVERB)
				(not (self onMe: event))
			)
			(event claimed: 1)
			(boreTheOyster start: 31 init:)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager say: noun theVerb 0 1 0 451)
			)
			(1
				(messager say: noun theVerb 0 1 0 451)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance book of Prop
	(properties
		x 198
		y 87
		onMeCheck $0000
		view 453
		loop 2
		priority 13
		signal $4010
	)
)

(instance arm of Prop
	(properties
		x 198
		y 87
		onMeCheck $0000
		view 4532
		loop 1
		priority 14
		signal $4010
	)
)

(instance insetOyster of Prop
	(properties
		x 63
		y 171
		z 70
		noun 3
		view 453
		loop 1
		priority 14
		signal $4010
		cycleSpeed 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager say: noun theVerb 0 1 0 451)
			)
			(1
				(messager say: noun theVerb 0 1 0 451)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance botShell of View
	(properties
		x 87
		y 180
		z 90
		noun 3
		view 453
		cel 1
		priority 12
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(messager say: noun theVerb 0 1 0 451)
			)
			(1
				(messager say: noun theVerb 0 1 0 451)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pearl of Prop
	(properties
		x 93
		y 104
		noun 4
		view 453
		loop 6
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if (!= (insetOyster cel?) (insetOyster lastCel:))
					(theGame handsOff:)
					(curRoom setScript: almostGotScript)
				else
					(theGame handsOff:)
					(curRoom setScript: grabPearl)
				)
			)
			(1
				(messager say: noun theVerb 0 1 0 451)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
