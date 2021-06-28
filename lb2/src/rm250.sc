;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include game.sh) (include "250.shm")
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
	nextRoom
	theTrash
	local2
)
(instance rm250 of LBRoom
	(properties
		noun N_ROOM
		picture 250
	)
	
	(method (init &tmp [temp0 50])
		(LoadMany RES_VIEW 250 251 252 253 254)
		(LoadMany RES_SOUND 300 41 250 252)
		(noise number: 41 flags: mNOPAUSE play:)
		(super init:)
		(InFirstPerson TRUE)
		(walkHandler addToFront: curRoom)
		(User canInput: TRUE)
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
					(not (ego has: iClaimTicket))
					(not (ego has: iEveningGown))
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
			((and (== prevRoomNum 300) (ego wearingGown?))
				(self setScript: sACTBREAK)
			)
			((not (ego has: iPressPass))
				(if (cast contains: trash1)
					(self setScript: sNoPressPassD)
				else
					(self setScript: sNoPressPassC)
				)
			)
			(else
				(self setScript: sHasPressPass)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom)
		(if inset
			(inset dispose:)
		)
		(walkHandler delete: curRoom)
		(InFirstPerson FALSE)
		(if (& msgType CD_MSG)
			(DoAudio Stop)
		)
		(theMusic stop:)
		(theMusic2 stop:)
		(super newRoom: &rest)
	)
)

(instance sACTBREAK of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(theIconBar disable: ICON_CONTROL)
				(proc27_2)
				((ScriptID 21 1) doit: 1029)
				(win1 setCycle: Forward)
				(win2 setCycle: Forward)
				(win3 setCycle: Forward)
				(win4 setCycle: Forward)
				(win5 setCycle: Forward)
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
				(theMusic number: 300 loop: 1 flags: mNOPAUSE play: self)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: TRUE)
				(= cycles 1)
			)
			(1
				(messager say: N_CABBIE NULL C_NO_PASS_BOB 1 self)
			)
			(2
				(messager say: N_CABBIE NULL C_LAURA_INSISTS 1 self)
			)
			(3
				(messager say: N_CABBIE NULL C_NO_PASS_BOB 2 self)
			)
			(4
				(theGame handsOn:)
				(= seconds 15)
			)
			(5
				(messager say: N_CABBIE NULL C_NO_PASS_BOB 3 self)
			)
			(6
				(curRoom newRoom: (if prevRoomNum else 210))
			)
		)
	)
)

(instance sNoPressPassC of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: TRUE)
				(= cycles 1)
			)
			(1 (messager say: N_CABBIE NULL C_NO_PASS_ROCCO 1 self))
			(2
				(messager say: N_CABBIE NULL C_LAURA_INSISTS 1 self)
			)
			(3
				(messager say: N_CABBIE NULL C_NO_PASS_ROCCO 2 self)
			)
			(4
				(theGame handsOn:)
				(= seconds 15)
			)
			(5 (messager say: N_CABBIE NULL C_NO_PASS_ROCCO 3 self))
			(6
				(curRoom newRoom: (if prevRoomNum else 210))
			)
		)
	)
)

(instance sHasPressPass of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: TRUE)
				(= cycles 1)
			)
			(1
				(cond 
					((and (cast contains: trash1) (not (Btst fBeenInDirtyTaxi)))
						(client setScript: s1stTimeInDirtyTaxi self)
					)
					((cast contains: trash1)
						(messager say: N_CABBIE NULL C_BOB 6 self)
					)
					((not (cast contains: trash1))
						(messager say: N_CABBIE NULL C_ROCCO 0 self)
					)
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
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: TRUE)
				(= cycles 1)
			)
			(1 (messager say: N_CABBIE NULL C_BOB 1 self))
			(2
				(messager say: N_CABBIE NULL C_BOB 2 self)
				(Bset fBeenInDirtyTaxi)
			)
			(3
				(= register 0)
				(switch
					(Print
						addText: N_RESPONSE_TITLE NULL NULL 0
						addButton: 1 N_RESPONSE NULL NULL 1 5 18
						addButton: 2 N_RESPONSE NULL NULL 2 5 48
						init:
					)
					(1
						(messager say: N_CABBIE NULL C_BOB 6 self)
					)
					(2
						(messager say: N_CABBIE NULL C_BOB 5 self)
						(= register 1)
					)
					(else
						(= cycles 1)
					)
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

	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: TRUE)
				(= cycles 1)
			)
			(1
				(if (not (cast contains: trash1))
					(messager say: N_ROCCO V_PRESS_PASS C_ROCCO 0 self)
				else
					(messager say: N_BOB V_PRESS_PASS C_BOB 0 self)
				)
			)
			(2
				(switch (curRoom setInset: (ScriptID 20 0))
					(513 (= nextRoom 210))
					(515 (= nextRoom 260))
					(516 (= nextRoom 240))
					(514 (= nextRoom 280))
					(518 (= nextRoom 300))
					(520 (= nextRoom 300))
					(517 (= nextRoom 330))
					(519
						(= local2 1)
						(= nextRoom 250)
						(if (not (cast contains: trash1))
							(messager say: N_BE_SPECIFIC V_NOTEBOOK C_ROCCO 0)
						else
							(messager say: N_BE_SPECIFIC V_NOTEBOOK C_BOB 0)
						)
					)
					(-1 (= nextRoom 250))
					(else 
						(= nextRoom 250)
						(if (not (cast contains: trash1))
							(messager say: N_HUH V_NOTEBOOK C_ROCCO 0)
						else
							(messager say: N_HUH V_NOTEBOOK C_BOB 0)
						)
					)
				)
				(= cycles 1)
			)
			(3
				(cond 
					((or (== nextRoom prevRoomNum) (== nextRoom curRoomNum))
						(theGame handsOn:)
						(= cycles 1)
					)
					((not (cast contains: trash1))
						(curRoom setScript: sDoTakeOffFlight)
					)
					(else
						(curRoom setScript: sMoveBuildings)
					)
				)
			)
			(4
				(theIconBar enable: ICON_ITEM)
				(if (!= nextRoom curRoomNum)
					(curRoom newRoom: nextRoom)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sDoTakeOffFlight of Script

	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(User canInput: TRUE)
				(= cycles 1)
			)
			(1
				(win1 setCycle: Forward)
				(win2 setCycle: Forward)
				(win3 setCycle: Forward)
				(win4 setCycle: Forward)
				(win5 setCycle: Forward)
				(= cycles 1)
			)
			(2
				(theMusic number: 250 loop: 1 flags: mNOPAUSE play:)
				(theGame handsOn:)
				(theIconBar disable: ICON_ITEM ICON_INVENTORY ICON_WALK)
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
				((ScriptID 1902 13) modeless: TRUE)
				((ScriptID 1903 14) modeless: TRUE)
				(= register (Random 11 17))
				(cond 
					((== register 17) (= seconds 8))
					((== register 16) (= seconds 8))
					(else (messager say: 10 NULL register 0 self))
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
						(if (== (DoAudio Loc) -1)
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
				(if (& msgType CD_MSG) ((ScriptID 1902 13) dispose:))
				(theGame handsOn:)
				(theIconBar enable: ICON_ITEM)
				(if (!= nextRoom curRoomNum)
					(curRoom newRoom: nextRoom)
				else
					(self dispose:)
				)
			)
		)
	)
)

(instance sMoveBuildings of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(win1 setCycle: Forward)
				(win2 setCycle: Forward)
				(win3 setCycle: Forward)
				(win4 setCycle: Forward)
				(win5 setCycle: Forward)
				(= cycles 1)
			)
			(1
				(theMusic number: 250 loop: 1 flags: mNOPAUSE play:)
				(theGame handsOn:)
				(theIconBar disable: ICON_ITEM ICON_INVENTORY ICON_WALK)
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
						(if (== (DoAudio Loc) -1)
							(== (theMusic prevSignal?) -1)
						)
					)
					(-- state)
				)
				(= cycles 1)
			)
			(7
				(theMusic fade:)
				(theIconBar enable: ICON_ITEM)
				(if (& msgType CD_MSG) ((ScriptID 1903 14) dispose:))
				(if (!= nextRoom curRoomNum)
					(curRoom newRoom: nextRoom)
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
		signal (| skipCheck fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_EXIT)
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
		noun N_BOB
		view 252
		loop 1
		priority 4
		signal (| skipCheck fixedLoop fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(messager say: N_BOB V_LOOK C_BOB 0)
			)
			(V_DO
				(messager say: N_BOB V_DO C_BOB 0)
			)
			(V_EXIT
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(V_ASK
				(cond 
					(local2
						(curRoom setScript: sWhereToBud)
					)
					(
						(and
							(<= 512 (= temp0 (curRoom setInset: (ScriptID 20 0))))
							(<= temp0 665)
						)
						(messager say: 11 V_ASK C_BOB 0)
					)
					(else (messager say: 12 V_ASK C_BOB 0))
				)
			)
			(V_TALK
				(messager say: N_BOB V_TALK C_BOB 0)
			)
			(V_PRESS_PASS
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
		noun N_ROCCO
		view 252
		priority 4
		signal (| skipCheck fixedLoop fixPriOn stopUpdOn)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_LOOK
				(messager say: N_ROCCO V_LOOK C_ROCCO 0)
			)
			(V_DO
				(messager say: N_ROCCO V_DO C_ROCCO 0)
			)
			(V_ASK
				(cond 
					(local2 (curRoom setScript: sWhereToBud))
					(
						(and
							(<= 512 (= temp0 (curRoom setInset: (ScriptID 20 0))))
							(<= temp0 665)
						)
						(messager say: 11 V_ASK C_ROCCO 0)
					)
					(else (messager say: 12 V_ASK C_ROCCO 0))
				)
			)
			(V_TALK
				(messager say: N_ROCCO V_TALK C_ROCCO 0)
			)
			(V_EXIT
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(V_PRESS_PASS
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
		noun N_WINDOW
		view 253
		priority 2
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
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
		noun N_WINDOW
		view 253
		loop 1
		priority 2
		signal (| ignrAct fixPriOn stopUpdOn)
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
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
		noun N_WINDOW
		view 254
		priority 2
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
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
		noun N_WINDOW
		view 254
		loop 1
		priority 2
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
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
		noun N_WINDOW
		view 254
		loop 2
		priority 2
		signal (| fixPriOn stopUpdOn)
		cycleSpeed 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
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
		disposeNotOnMe TRUE
		noun N_TICKET
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				((ScriptID 21 0) doit: 770)
				(ticket dispose:)
				(inTicket dispose:)
				(ego get: -1 iClaimTicket)
				(Bset fGotClaimTicket)
			)
			(V_EXIT
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance showTicket of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(curRoom setInset: inTicket)
				(= cycles 5)
			)
			(1
				(inTicket doVerb: V_LOOK)
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
		signal fixPriOn
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(curRoom setScript: showTicket)
			)
			(V_DO
				(ego get: iClaimTicket)
				(Bset fGotClaimTicket)
				((ScriptID 21 0) doit: 770)
				(self dispose:)
			)
			(V_EXIT
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
		(if (cast contains: trash1)
			(= noun N_BOB_LICENSE)
		else
			(= noun N_ROCCO_LICENSE)
		)
		(switch theVerb
			(V_EXIT
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
		noun N_TRASH
		view 250
		loop 1
		cel 0
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
					(== (event message?) ENTER)
					(== (event type?) keyDown)
					(== (theIconBar curIcon?)
						(theIconBar at: ICON_DO)
					)
					(self onMe: event)
				)
				(if (!= theTrash self)
					(= theTrash self)
					(noise number: 54 loop: 1 flags: mNOPAUSE play:)
				else
					(= theTrash 0)
				)
				(event claimed: TRUE)
			)
			(
				(and
					(== (event type?) mouseDown)
					(== (theIconBar curIcon?) (theIconBar at: ICON_DO))
					(self onMe: event)
				)
				(noise number: 54 loop: 1 flags: mNOPAUSE play:)
				(= theTrash self)
				(event claimed: 1)
			)
			(
				(and
					(== (event type?) mouseUp)
					(self onMe: event)
				)
				(= theTrash 0)
				(event claimed: TRUE)
			)
			(else
				(super handleEvent: event &rest)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_EXIT
				(curRoom newRoom: (if prevRoomNum else 210))
			)
			(V_LOOK
				(messager say: 3 V_LOOK C_GOT_TICKET)
			)
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
		signal ignrAct
	)
)

(instance trash2 of Trash
	(properties
		x 145
		y 163
		cel 3
		signal ignrAct
	)
)

(instance trash3 of Trash
	(properties
		x 148
		y 181
		cel 4
		signal ignrAct
	)
)

(instance trash4 of Trash
	(properties
		x 112
		y 174
		cel 5
		signal ignrAct
	)
)

(instance trash5 of Trash
	(properties
		x 58
		y 174
		signal ignrAct
	)
)

(instance cornerTrash of View
	(properties
		x 261
		y 189
		noun N_TRASH
		view 250
		loop 3
		priority 1
		signal (| fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(cond 
					((Btst fGotClaimTicket)
						(messager say: N_TRASH V_DO C_GOT_TICKET)
					)
					((TriggerEvent 16 1)
						(messager say: N_TRASH V_DO C_FIND_TICKET))
					((ego wearingGown?)
						(messager say: N_TRASH V_DO C_GOT_TICKET))
					((ego has: iCoupon)
						(messager say: N_TRASH V_DO C_GOT_TICKET)
					)
					(else
						(messager say: N_TRASH V_DO C_GOT_TICKET)
					)
				)
			)
			(V_LOOK
				(messager say: 3 V_LOOK C_GOT_TICKET)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance noise of Sound
	(properties
		flags mNOPAUSE
	)
)
