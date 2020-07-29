;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include game.sh)
(use Main)
(use Timer)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	TTDialer 0
)

(local
	[numBuf 100]
	local100
	[local101 14] = [0 1 -16 1 19 -16 1 19 -16 1 19 -16 19 -1]
	[local115 14] = [0 -15 -69 -69 -69 -52 -52 -52 -34 -34 -34 -15 -15 -3]
	[local129 6] = [551 552 553 554 555 556]
	[local135 10]
	local145
	local146
	local147
	local148
	userCanInput
	oldMusicVol
	oldSoundFxVol
	theDisabledIcons
	local153
	local154
	local155
)
(instance TTDialer of Prop
	(properties
		x 275
		y 89
		view 10
		priority 14
		signal fixPriOn
	)
	
	(method (init param1 param2)
		(switch curRoomNum
			(258 (= x 41) (= y 189))
			(870 (= x 41) (= y 89))
			(else  (= x 275) (= y 89))
		)
		(= local147 param1)
		(if (> argc 1) (= local148 param2) else (= local148 0))
		(= numBuf 0)
		(= local100 0)
		(= local145 0)
		(= local146 0)
		(= userCanInput (User canInput:))
		(= theDisabledIcons disabledIcons)
		(if
		(!= (= local153 ((theIconBar at: 0) cursor?)) 6)
			(InFirstPerson 1)
		)
		(HandsOff)
		(User canInput: 1 mapKeyToDir: 0)
		(theIconBar enable: 0 2 curIcon: (theIconBar at: 2))
		(theGame setCursor: 2)
		(if (theMusic handle?)
			(= oldMusicVol (theMusic vol?))
			(theMusic setVol: (/ oldMusicVol 2))
		)
		(if (theMusic2 handle?)
			(= oldSoundFxVol (theMusic2 vol?))
			(theMusic2 setVol: (/ oldSoundFxVol 2))
		)
		(if (== local147 1)
			(dialToneSound
				number: 33
				loop: -1
				flags: 1
				play:
				hold: 10
			)
		)
		(super init: stopUpd:)
		(btn0 init: stopUpd:)
		(btn1 init: stopUpd:)
		(btn2 init: stopUpd:)
		(btn3 init: stopUpd:)
		(btn4 init: stopUpd:)
		(btn5 init: stopUpd:)
		(btn6 init: stopUpd:)
		(btn7 init: stopUpd:)
		(btn8 init: stopUpd:)
		(btn9 init: stopUpd:)
		(if (== local147 1)
			(btnStar init: stopUpd:)
			(btnCross init: stopUpd:)
			(hangUp init: stopUpd:)
		)
		(mouseDownHandler addToEnd: self)
		(keyDownHandler addToEnd: self)
	)
	
	(method (dispose)
		(theIconBar disable:)
		(User canInput: 0 canControl: 0)
		(btn0 dispose:)
		(btn1 dispose:)
		(btn2 dispose:)
		(btn3 dispose:)
		(btn4 dispose:)
		(btn5 dispose:)
		(btn6 dispose:)
		(btn7 dispose:)
		(btn8 dispose:)
		(btn9 dispose:)
		(if (== local147 1)
			(btnStar dispose:)
			(btnCross dispose:)
			(hangUp dispose:)
		)
		(self hide:)
		(= local100 254)
		(hangUpTimer setReal: self 2)
	)
	
	(method (handleEvent event)
		(cond 
			((not (User canInput:)))
			((event modifiers?)
				(super handleEvent: event)
			)
			(
				(or
					(and
						(OneOf (event type?) mouseDown keyDown)
						(== ((theIconBar at: ICON_WALK) cursor?) 6)
						(== (theIconBar curIcon?)
							(theIconBar at: ICON_WALK)
						)
					)
					(== (event message?) verbWalk)
				)
				(event claimed: TRUE)
				(= local154 -1)
				(self dispose:)
			)
			(else
				(event claimed: TRUE)
			)
		)
	)
	
	(method (cue &tmp temp0)
		(switch (++ local100)
			(1
				(if
				(and (== local147 1) (not (ReadNumber @numBuf)))
					(self setScript: sInformation)
				)
			)
			(3
				(if (== local147 1)
					(= local145 (ReadNumber @numBuf))
					(= numBuf 0)
					(if (OneOf local145 411 911)
						(self setScript: sInformation)
					)
				)
			)
			(5
				(if (== local147 0)
					(if (not (StrCmp @numBuf {!!!!!}))
						(= local154 -2)
					else
						(= local154 (ReadNumber @numBuf))
					)
					(self dispose:)
				)
			)
			(7
				(= local146 (ReadNumber @numBuf))
				(if
					(and
						(== local147 1)
						(== local145 555)
						(== local146 1212)
					)
					(self setScript: sInformation)
				else
					(User canInput: 0)
					(self setScript: sMessage self)
				)
			)
			(8
				(if local146
					(= local154 local146)
					(self dispose:)
				else
					(= numBuf 0)
					(= local100 0)
					(= local145 0)
					(= local146 0)
				)
			)
			(255
				(= temp0 0)
				(while (< temp0 10)
					(= [local135 temp0] 0)
					(++ temp0)
				)
				(User canInput: userCanInput mapKeyToDir: 1)
				(if (!= local153 6) (InFirstPerson 0))
				(DisableIcons)
				(= disabledIcons theDisabledIcons)
				(if (theMusic handle?) (theMusic setVol: oldMusicVol))
				(if (theMusic2 handle?) (theMusic2 setVol: oldSoundFxVol))
				(mouseDownHandler delete: self)
				(keyDownHandler delete: self)
				(curRoom notify: local154)
				(if local148 (local148 cue:))
				(dialToneSound stop:)
				(hangUpTimer dispose: delete:)
				(theIconBar enable: curIcon: (theIconBar at: 2))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(super dispose:)
			)
		)
	)
)

(instance sMessage of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(and
							(!= curRoomNum 200)
							(== local145 [local129 currentCity])
						)
						(and (== curRoomNum 200) (== local145 556))
					)
					(= register 1)
				)
				(if (!= register 1)
					(phoneSound number: 35 play: self)
				else
					(= cycles 1)
				)
			)
			(1
				(if (!= register 1)
					(TimePrint 20 0)
					(TimePrint 20 1)
					(TimePrint 20 2 #at -1 185)
					(= local146 0)
				else
					(TimePrint 20 3)
				)
				(User canInput: 1)
				(= local154 local146)
				(client dispose:)
			)
		)
	)
)

(instance sInformation of Script
	(properties)
	
	(method (changeState newState &tmp [str 222])
		(switch (= state newState)
			(0 (= ticks 120))
			(1
				(Format @str 20 4
					(if (== curRoom 200)
						{Washington, D. C.}
					else
						(switch currentCity
							(NEW_YORK
								{New York City Metropolitan}
							)
							(ATLANTIC_CITY
								{Atlantic City Non-Tramp Infested}
							)
							(MIAMI
								{Dade County}
							)
							(else
								{`Enter Your City Here'}
							)
						)
					)
					(if (== curRoom 200)
						{floating down the Potomac}
					else
						(switch currentCity
							(NEW_YORK
								{getting mugged}
							)
							(ATLANTIC_CITY
								{feeding the slots}
							)
							(MIAMI
								{lying on the beach}
							)
							(else
								{sitting on the can!}
							)
						)
					)
				)
				(TimePrint @str)
				(= cycles 1)
			)
			(2
				(= numBuf 0)
				(= local100 0)
				(= local145 0)
				(= local146 0)
				(= local154 -1)
				(client dispose:)
			)
		)
	)
)

(instance dialToneSound of Sound)

(instance phoneSound of Sound)

(instance hangUpTimer of Timer)

(class PushButton of Prop
	(properties
		priority 14
		signal fixPriOn
		keyValue 45
		scanValue 0
		keyStr {x}
		tone 0
	)
	
	(method (init &tmp theLoop)
		(= x (+ (TTDialer x?) [local101 loop]))
		(= y (+ (TTDialer y?) [local115 loop] 1000))
		(if (== curRoomNum 258)
			(= view 11)
			(= theLoop (Random 1 10))
			(while [local135 theLoop]
				(= theLoop (Random 1 10))
			)
			(= [local135 theLoop] 1)
			(= loop theLoop)
		else
			(= view 10)
		)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp [temp0 10] eType eMsg temp12)
		(= eType (event type?))
		(= eMsg (event message?))
		(if
			(or
				(and (== eType mouseDown) (self onMe: event))
				(and
					(== eType keyDown)
					(== eMsg ENTER)
					(self onMe: event)
				)
				(and
					(== eType keyDown)
					(OneOf eMsg keyValue scanValue)
				)
			)
			(event claimed: TRUE)
			(dialToneSound stop:)
			(if (== loop 13)
				(= local154 -1)
				(TTDialer dispose:)
			else
				(if (and (== (event modifiers?) 12) (== loop 1))
					(= keyStr {!})
				else
					(switch loop
						(11 (= keyStr {#}))
						(12 (= keyStr {*}))
						(else 
							(Format keyStr 20 5 (- loop 1))
						)
					)
				)
				(= temp12 (if (== curRoomNum 258) 256 else tone))
				(self setScript: (sButton new:) 0 temp12)
			)
		)
	)
)

(instance sButton of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (client setCycle: EndLoop self))
			(1
				(if register (phoneSound number: register play:))
				(= cycles 1)
			)
			(2
				(StrCat @numBuf (client keyStr?))
				(TTDialer cue:)
				(client setCel: 0)
				(self dispose:)
			)
		)
	)
)

(instance btn0 of PushButton
	(properties
		z 1000
		loop 1
		keyValue 48
		scanValue 20992
		tone 30
	)
)

(instance btn1 of PushButton
	(properties
		z 1000
		loop 2
		keyValue 49
		scanValue 20224
		tone 21
	)
	
	(method (init)
		(= keyValue (if (== curRoomNum 258) 55 else 49))
		(= scanValue (if (== curRoomNum 258) 18176 else 20224))
		(super init:)
	)
)

(instance btn2 of PushButton
	(properties
		z 1000
		loop 3
		keyValue 50
		scanValue 20480
		tone 22
	)
	
	(method (init)
		(= keyValue (if (== curRoomNum 258) 56 else 50))
		(= scanValue (if (== curRoomNum 258) 18432 else 20480))
		(super init:)
	)
)

(instance btn3 of PushButton
	(properties
		z 1000
		loop 4
		keyValue 51
		scanValue 20736
		tone 23
	)
	
	(method (init)
		(= keyValue (if (== curRoomNum 258) 57 else 51))
		(= scanValue (if (== curRoomNum 258) 18688 else 20736))
		(super init:)
	)
)

(instance btn4 of PushButton
	(properties
		z 1000
		loop 5
		keyValue 52
		scanValue 19200
		tone 24
	)
)

(instance btn5 of PushButton
	(properties
		z 1000
		loop 6
		keyValue 53
		scanValue 19456
		tone 25
	)
)

(instance btn6 of PushButton
	(properties
		z 1000
		loop 7
		keyValue 54
		scanValue 19712
		tone 26
	)
)

(instance btn7 of PushButton
	(properties
		z 1000
		loop 8
		keyValue 55
		scanValue 18176
		tone 27
	)
	
	(method (init)
		(= keyValue (if (== curRoomNum 258) 49 else 55))
		(= scanValue (if (== curRoomNum 258) 20224 else 18176))
		(super init:)
	)
)

(instance btn8 of PushButton
	(properties
		z 1000
		loop 9
		keyValue 56
		scanValue 18432
		tone 28
	)
	
	(method (init)
		(= keyValue (if (== curRoomNum 258) 50 else 56))
		(= scanValue (if (== curRoomNum 258) 20480 else 18432))
		(super init:)
	)
)

(instance btn9 of PushButton
	(properties
		z 1000
		loop 10
		keyValue 57
		scanValue 18688
		tone 29
	)
	
	(method (init)
		(= keyValue (if (== curRoomNum 258) 51 else 57))
		(= scanValue (if (== curRoomNum 258) 20736 else 18688))
		(super init:)
	)
)

(instance btnCross of PushButton
	(properties
		z 1000
		loop 11
		keyValue 35
		tone 31
	)
)

(instance btnStar of PushButton
	(properties
		z 1000
		loop 12
		keyValue 42
		tone 32
	)
)

(instance hangUp of PushButton
	(properties
		z 1000
		loop 13
		keyValue 32
	)
)
