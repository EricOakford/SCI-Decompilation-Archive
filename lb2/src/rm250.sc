;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include sci.sh)
(use Main)
(use LBRoom)
(use n027)
(use Print)
(use Inset)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm250 0
)

(local
	local0
	theTrash
	local2
)
(instance rm250 of LBRoom
	(properties
		noun 14
		picture 250
	)
	
	(method (init &tmp [temp0 50])
		(LoadMany 128 250 251 252 253 254)
		(LoadMany 132 300 41 250 252)
		(noise number: 41 flags: 1 play:)
		(super init:)
		(InFirstPerson 1)
		(walkHandler addToFront: curRoom)
		(User canInput: 1)
		(laura cel: (if (ego wearingGown?) 1 else 0) addToPic:)
		(license init:)
		(if (TriggerEvent 16 1)
			(trash1 init:)
			(trash2 init:)
			(trash3 init:)
			(trash4 init:)
			(trash5 init:)
			(cornerTrash addToPic:)
			(if
				(and
					(not (ego wearingGown?))
					(not (ego has: 1))
					(not (ego has: 32))
				)
				(ticket init:)
			)
			(DDriver addToPic:)
		else
			(CDriver addToPic:)
		)
		(theMusic number: 250)
		(win1 init: stopUpd:)
		(win2 init: stopUpd:)
		(win3 init: stopUpd:)
		(win4 init: stopUpd:)
		(win5 init: stopUpd:)
		(narrator y: 120)
		(cond 
			((and (== prevRoomNum 300) (ego wearingGown?)) (self setScript: sACTBREAK))
			((not (ego has: 6))
				(if (cast contains: trash1)
					(self setScript: sNoPressPassD)
				else
					(self setScript: sNoPressPassC)
				)
			)
			(else (self setScript: sHasPressPass))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom)
		(if inset (inset dispose:))
		(walkHandler delete: curRoom)
		(InFirstPerson 0)
		(if (& msgType $0002) (DoAudio 3))
		(theMusic stop:)
		(theMusic2 stop:)
		(super newRoom: &rest)
	)
)

(instance sACTBREAK of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(theIconBar disable: 7)
				(proc27_2)
				((ScriptID 21 1) doit: 1029)
				(win1 setCycle: Fwd)
				(win2 setCycle: Fwd)
				(win3 setCycle: Fwd)
				(win4 setCycle: Fwd)
				(win5 setCycle: Fwd)
				(theMusic2 send: 2 224 2400)
				(= cycles 1)
			)
			(1
				(theMusic2 send: 2 224 2800)
				(= cycles 1)
			)
			(2
				(theMusic2 send: 2 224 3200)
				(= cycles 1)
			)
			(3
				(theMusic2 send: 2 224 3600)
				(= cycles 1)
			)
			(4
				(theMusic2 send: 2 224 4000)
				(theMusic number: 300 loop: 1 flags: 1 play: self)
			)
			(5
				(theMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(6
				(theMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(7
				(theMusic2 send: 2 224 1000)
				(= cycles 1)
			)
			(8
				(theMusic2 send: 2 224 500)
				(= cycles 1)
			)
			(9
				(theMusic2 send: 2 224 0)
				(= cycles 5)
			)
			(10
				(theMusic fade:)
				(theMusic2 fade:)
				(curRoom newRoom: 26)
			)
		)
	)
)

(instance sNoPressPassD of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1 (messager say: 1 0 9 1 self))
			(2
				(messager say: 1 0 10 1 self)
			)
			(3 (messager say: 1 0 9 2 self))
			(4
				(theGame handsOn:)
				(= seconds 15)
			)
			(5 (messager say: 1 0 9 3 self))
			(6
				(curRoom newRoom: (if prevRoomNum else 210))
			)
		)
	)
)

(instance sNoPressPassC of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1 (messager say: 1 0 1 1 self))
			(2
				(messager say: 1 0 10 1 self)
			)
			(3 (messager say: 1 0 1 2 self))
			(4
				(theGame handsOn:)
				(= seconds 15)
			)
			(5 (messager say: 1 0 1 3 self))
			(6
				(curRoom newRoom: (if prevRoomNum else 210))
			)
		)
	)
)

(instance sHasPressPass of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(cond 
					((and (cast contains: trash1) (not (Btst 26))) (client setScript: s1stTimeInDirtyTaxi self))
					((cast contains: trash1) (messager say: 1 0 7 6 self))
					((not (cast contains: trash1)) (messager say: 1 0 8 0 self))
					(else (= cycles 1))
				)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance s1stTimeInDirtyTaxi of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1 (messager say: 1 0 7 1 self))
			(2
				(messager say: 1 0 7 2 self)
				(Bset 26)
			)
			(3
				(= register 0)
				(switch
					(Print
						addText: 16 0 0 0
						addButton: 1 15 0 0 1 5 18
						addButton: 2 15 0 0 2 5 48
						init:
					)
					(1 (messager say: 1 0 7 6 self))
					(2
						(messager say: 1 0 7 5 self)
						(= register 1)
					)
					(else  (= cycles 1))
				)
			)
			(4 (= seconds 1))
			(5
				(if (== register 1)
					(curRoom newRoom: (if prevRoomNum else 210))
				else
					(= cycles 1)
				)
			)
			(6
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sWhereToBud of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(if (not (cast contains: trash1))
					(messager say: 5 11 8 0 self)
				else
					(messager say: 4 11 7 0 self)
				)
			)
			(2
				(switch (curRoom setInset: (ScriptID 20 0))
					(513 (= local0 210))
					(515 (= local0 260))
					(516 (= local0 240))
					(514 (= local0 280))
					(518 (= local0 300))
					(520 (= local0 300))
					(517 (= local0 330))
					(519
						(= local2 1)
						(= local0 250)
						(if (not (cast contains: trash1))
							(messager say: 17 14 8 0)
						else
							(messager say: 17 14 7 0)
						)
					)
					(-1 (= local0 250))
					(else 
						(= local0 250)
						(if (not (cast contains: trash1))
							(messager say: 12 14 8 0)
						else
							(messager say: 12 14 7 0)
						)
					)
				)
				(= cycles 1)
			)
			(3
				(cond 
					(
					(or (== local0 prevRoomNum) (== local0 curRoomNum)) (theGame handsOn:) (= cycles 1))
					((not (cast contains: trash1)) (curRoom setScript: sDoTakeOffFlight))
					(else (curRoom setScript: sMoveBuildings))
				)
			)
			(4
				(theIconBar enable: 5)
				(if (!= local0 curRoomNum)
					(curRoom newRoom: local0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sDoTakeOffFlight of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: 1)
				(= cycles 1)
			)
			(1
				(win1 setCycle: Fwd)
				(win2 setCycle: Fwd)
				(win3 setCycle: Fwd)
				(win4 setCycle: Fwd)
				(win5 setCycle: Fwd)
				(= cycles 1)
			)
			(2
				(theMusic number: 250 loop: 1 flags: 1 play:)
				(theGame handsOn:)
				(theIconBar disable: 5 6 0)
				(= cycles 1)
			)
			(3
				(theMusic2 send: 2 224 1000)
				(= cycles 1)
			)
			(4
				(theMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(5
				(theMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(6
				(theMusic2 send: 2 224 4000)
				((ScriptID 1902 13) modeless: 1)
				((ScriptID 1903 14) modeless: 1)
				(= register (Random 11 17))
				(cond 
					((== register 17) (= seconds 8))
					((== register 16) (= seconds 8))
					(else (messager say: 10 0 register 0 self))
				)
			)
			(7
				(theMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(8
				(theMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(9
				(if
					(not
						(if (== (DoAudio 6) -1)
							(== (theMusic prevSignal?) -1)
						)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(10
				(win1 setCycle: 0)
				(win2 setCycle: 0)
				(win3 setCycle: 0)
				(win4 setCycle: 0)
				(win5 setCycle: 0)
				(theMusic2 send: 2 224 1000)
				(messager say: 10 0 16 0 self)
			)
			(11
				(theMusic2 send: 2 224 500)
				(= cycles 1)
			)
			(12
				(theMusic2 send: 2 224 0)
				(if (& msgType $0002) ((ScriptID 1902 13) dispose:))
				(theGame handsOn:)
				(theIconBar enable: 5)
				(if (!= local0 curRoomNum)
					(curRoom newRoom: local0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sMoveBuildings of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(win1 setCycle: Fwd)
				(win2 setCycle: Fwd)
				(win3 setCycle: Fwd)
				(win4 setCycle: Fwd)
				(win5 setCycle: Fwd)
				(= cycles 1)
			)
			(1
				(theMusic number: 250 loop: 1 flags: 1 play:)
				(theGame handsOn:)
				(theIconBar disable: 5 6 0)
				(= cycles 1)
			)
			(2
				(theMusic2 send: 2 224 1000)
				(= cycles 1)
			)
			(3
				(theMusic2 send: 2 224 2000)
				(= cycles 1)
			)
			(4
				(theMusic2 send: 2 224 3000)
				(= cycles 1)
			)
			(5
				(theMusic2 send: 2 224 4000)
				(= seconds (Random 6 10))
			)
			(6
				(if
					(not
						(if (== (DoAudio 6) -1)
							(== (theMusic prevSignal?) -1)
						)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(7
				(theMusic fade:)
				(theIconBar enable: 5)
				(if (& msgType $0002) ((ScriptID 1903 14) dispose:))
				(if (!= local0 curRoomNum)
					(curRoom newRoom: local0)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance laura of View
	(properties
		y 100
		z 75
		view 251
		priority 10
		signal $1011
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 13)
			(curRoom newRoom: (if prevRoomNum else 210))
		else
			(ego doVerb: theVerb &rest)
		)
	)
)

(instance DDriver of View
	(properties
		x 232
		y 104
		noun 4
		view 252
		loop 1
		priority 4
		signal $1811
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1 (messager say: 4 1 7 0))
			(4 (messager say: 4 4 7 0))
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(6
				(cond 
					(local2 (curRoom setScript: sWhereToBud))
					(
						(and
							(<= 512 (= temp0 (curRoom setInset: (ScriptID 20 0))))
							(<= temp0 665)
						)
						(messager say: 11 6 7 0)
					)
					(else (messager say: 12 6 7 0))
				)
			)
			(2 (messager say: 4 2 7 0))
			(11
				(curRoom setScript: sWhereToBud)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance CDriver of View
	(properties
		x 232
		y 104
		noun 5
		view 252
		priority 4
		signal $1811
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(1 (messager say: 5 1 8 0))
			(4 (messager say: 5 4 8 0))
			(6
				(cond 
					(local2 (curRoom setScript: sWhereToBud))
					(
						(and
							(<= 512 (= temp0 (curRoom setInset: (ScriptID 20 0))))
							(<= temp0 665)
						)
						(messager say: 11 6 8 0)
					)
					(else (messager say: 12 6 8 0))
				)
			)
			(2 (messager say: 5 2 8 0))
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(11
				(curRoom setScript: sWhereToBud)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance win1 of Prop
	(properties
		x 87
		y 96
		noun 6
		view 253
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance win2 of Prop
	(properties
		x 141
		y 97
		noun 6
		view 253
		loop 1
		priority 2
		signal $4011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance win3 of Prop
	(properties
		x 159
		y 92
		noun 6
		view 254
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance win4 of Prop
	(properties
		x 213
		y 88
		noun 6
		view 254
		loop 1
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance win5 of Prop
	(properties
		x 268
		y 89
		noun 6
		view 254
		loop 2
		priority 2
		signal $0011
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance inTicket of Inset
	(properties
		view 250
		x 190
		y 154
		disposeNotOnMe 1
		noun 9
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				((ScriptID 21 0) doit: 770)
				(ticket dispose:)
				(inTicket dispose:)
				(ego get: -1 1)
				(Bset 27)
			)
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance showTicket of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom setInset: inTicket)
				(= cycles 5)
			)
			(1
				(inTicket doVerb: 1)
				(self dispose:)
			)
		)
	)
)

(instance ticket of View
	(properties
		x 149
		y 178
		view 250
		loop 2
		priority 8
		signal $0010
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(1
				(curRoom setScript: showTicket)
			)
			(4
				(ego get: 1)
				(Bset 27)
				((ScriptID 21 0) doit: 770)
				(self dispose:)
			)
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance license of Feature
	(properties
		x 246
		y 114
		nsTop 99
		nsLeft 219
		nsBottom 130
		nsRight 274
		sightAngle 40
	)
	
	(method (doVerb theVerb)
		(if (cast contains: trash1) (= noun 7) else (= noun 8))
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(class Trash of View
	(properties
		x 0
		y 0
		z 0
		heading 0
		noun 2
		modNum -1
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		sightAngle 26505
		actions 0
		onMeCheck $6789
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		yStep 2
		view 250
		loop 1
		cel 0
		priority 0
		underBits 0
		signal $0101
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		scaleSignal $0000
		scaleX 128
		scaleY 128
		maxScale 128
		boundryLeft 0
		boundryRight 319
		boundryTop 155
		boundryBottom 189
	)
	
	(method (init)
		(if (TriggerEvent 16 1)
			(mouseDownHandler addToFront: self)
			(keyDownHandler addToFront: self)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(if (and (== theTrash self) (self inBounds:))
			(= x mouseX)
			(= y mouseY)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			(
				(and
					(== (event message?) KEY_RETURN)
					(== (event type?) evKEYBOARD)
					(== (theIconBar curIcon?) (theIconBar at: 2))
					(self onMe: event)
				)
				(if (!= theTrash self)
					(= theTrash self)
					(noise number: 54 loop: 1 flags: 1 play:)
				else
					(= theTrash 0)
				)
				(event claimed: 1)
			)
			(
				(and
					(== (event type?) evMOUSEBUTTON)
					(== (theIconBar curIcon?) (theIconBar at: 2))
					(self onMe: event)
				)
				(noise number: 54 loop: 1 flags: 1 play:)
				(= theTrash self)
				(event claimed: 1)
			)
			(
				(and
					(== (event type?) evMOUSERELEASE)
					(self onMe: event)
				)
				(= theTrash 0)
				(event claimed: 1)
			)
			(else (super handleEvent: event &rest))
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(13
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(1 (messager say: 3 1 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (inBounds)
		(if
			(and
				(> mouseX boundryLeft)
				(< mouseX boundryRight)
				(> mouseY boundryTop)
				(< mouseY boundryBottom)
			)
		)
	)
)

(instance trash1 of Trash
	(properties
		x 166
		y 176
		cel 2
		signal $4000
	)
)

(instance trash2 of Trash
	(properties
		x 145
		y 163
		cel 3
		signal $4000
	)
)

(instance trash3 of Trash
	(properties
		x 148
		y 181
		cel 4
		signal $4000
	)
)

(instance trash4 of Trash
	(properties
		x 112
		y 174
		cel 5
		signal $4000
	)
)

(instance trash5 of Trash
	(properties
		x 58
		y 174
		signal $4000
	)
)

(instance cornerTrash of View
	(properties
		x 261
		y 189
		noun 2
		view 250
		loop 3
		priority 1
		signal $0810
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((Btst 27) (messager say: 2 4 4))
					((TriggerEvent 16 1) (messager say: 2 4 2))
					((ego wearingGown?) (messager say: 2 4 4))
					((ego has: 0) (messager say: 2 4 4))
					(else (messager say: 2 4 4))
				)
			)
			(1 (messager say: 3 1 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance noise of Sound
	(properties
		flags $0001
	)
)
