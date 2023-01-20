;;; Sierra Script 1.0 - (do not remove this comment)
(script# 44)
(include sci.sh)
(use Main)
(use Intrface)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm44 0
)

(local
	local0
	saveBits
	local2
	gIconBarCurIcon
	gIconBarCurInvIcon
)
(procedure (localproc_1388)
	(if saveBits (localproc_13b0))
	(= saveBits
		(Display
			&rest
			dsALIGN
			1
			dsWIDTH
			160
			dsCOORD
			80
			126
			dsCOLOR
			colBlack
			dsSAVEPIXELS
		)
	)
)

(procedure (localproc_13b0)
	(Display 44 20 108 saveBits)
	(= saveBits 0)
)

(procedure (localproc_13c2 param1 param2 param3)
	(InRect
		(param1 nsLeft?)
		(param1 nsTop?)
		(param1 nsRight?)
		(param1 nsBottom?)
		param2
		param3
	)
)

(instance rm44 of Rm
	(properties
		lookStr {It's a "Slots-O-Death" slot machine. They play for keeps around here.}
		picture 44
	)
	
	(method (init &tmp [temp0 30])
		(LoadMany 132 621 619 620 625 627 628 629)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(if (< numVoices 4) (theMusic stop:))
		(super init:)
		(HandsOn)
		(Bclr 12)
		(tmp1 init: stopUpd:)
		(tmp2 init: stopUpd:)
		(tmp3 init: stopUpd:)
		(butn init:)
		(exitBtn init: stopUpd:)
		(coinSlot init:)
		(info init: cel: 1 stopUpd:)
		(pic1 cel: (& slotsCel $000f) init:)
		(pic2 cel: (& (>> slotsCel $0004) $000f) init:)
		(pic3 cel: (& (>> slotsCel $0008) $000f) init:)
		(theMusic setVol: 70)
		(if insertedBuckazoids
			(info cel: 1 stopUpd:)
			(localproc_1388
				(Format @temp0 44 0 insertedBuckazoids buckazoids)
			)
		else
			(info setCycle: Fwd)
		)
	)
	
	(method (doit &tmp temp0 temp1 userCurEvent)
		(super doit: &rest)
		(cond 
			((coinSlot onMe:) (theGame setCursor:))
			(butn (theGame setCursor:))
		)
		(= userCurEvent (User curEvent?))
		(= temp0 ((User curEvent?) x?))
		(= temp1 ((User curEvent?) y?))
		(if
			(and
				(ego has: 10)
				(localproc_13c2 coinSlot temp0 temp1)
				(not gIconBarCurIcon)
			)
			(= gIconBarCurIcon (theIconBar curIcon?))
			(= gIconBarCurInvIcon (theIconBar curInvIcon?))
			(theIconBar
				at: 6
				curIcon: (theIconBar at: 6)
				curInvIcon: (Inv at: 10)
			)
			(theGame setCursor: ((theIconBar curInvIcon?) cursor?))
		)
		(if
			(and
				insertedBuckazoids
				(localproc_13c2 butn temp0 temp1)
				(not gIconBarCurIcon)
				(!= (theIconBar curIcon?) (theIconBar at: 2))
			)
			(= gIconBarCurIcon (theIconBar curIcon?))
			(= gIconBarCurInvIcon (theIconBar curInvIcon?))
			(theIconBar at: 2 curIcon: (theIconBar at: 2))
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
		)
		(if
			(and
				gIconBarCurIcon
				(or
					(and
						(not (ego has: 10))
						(localproc_13c2 coinSlot temp0 temp1)
					)
					(and
						(not (localproc_13c2 coinSlot temp0 temp1))
						(not (localproc_13c2 butn temp0 temp1))
					)
				)
			)
			(theIconBar
				curIcon: gIconBarCurIcon
				curInvIcon: gIconBarCurInvIcon
			)
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
			(= gIconBarCurInvIcon 0)
			(= gIconBarCurIcon 0)
		)
		(userCurEvent dispose:)
	)
	
	(method (dispose)
		(= slotsCel
			(+
				(pic1 cel?)
				(<< (pic2 cel?) $0004)
				(<< (pic3 cel?) $0008)
			)
		)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(theMusic setVol: 127)
		(theMusic2 flags: 0)
		(super dispose: &rest)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event &rest))
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: 0))
					(not (pic3 script?))
					(not (curRoom script?))
					(or
						(== (event message?) JOY_UP)
						(& (event type?) evJOYSTICK)
					)
				)
				(curRoom newRoom: 43)
				(event claimed: 1)
			)
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: 6))
					(== (theIconBar curInvIcon?) (Inv at: 15))
					(== (event message?) JOY_DOWNRIGHT)
					(not (pic3 script?))
					(not (curRoom script?))
				)
				(Print 44 1)
				(Bset 12)
				(curRoom newRoom: 43)
				(event claimed: 1)
			)
		)
	)
)

(instance initScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 30])
		(switch (= state newState)
			(0 (HandsOff) (= cycles 2))
			(1
				(if insertedBuckazoids
					(info cel: 1 stopUpd:)
					(localproc_1388
						(Format @temp0 44 0 insertedBuckazoids buckazoids)
					)
				else
					(info setCycle: Fwd)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance spinPic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if register
					(client setMotion: MoveTo (client x?) 8 self)
					(register
						loop: 1
						cel: (+ (client cel?) 1)
						posn: (client x?) 96
						setMotion: MoveTo (client x?) 52
					)
				else
					(client stopUpd:)
					(self dispose:)
				)
			)
			(1
				(client cel: (register cel?) y: 52)
				(cond 
					((register priority?) 0)
					((client priority?) (client setPri: (- (client priority?) 1)))
					((and (== local2 1) (== (client cel?) 0))
						(register loop: 3)
						(slotSound number: 621 loop: 1 play:)
						(= register 0)
					)
					(local2
						(if (== (client cel?) (- local2 1))
							(slotSound number: 621 loop: 1 play:)
							(register loop: 3)
							(= register 0)
						)
					)
					((Random 0 4)
						(slotSound number: 621 loop: 1 play:)
						(register loop: 3)
						(= register 0)
					)
				)
				(self init:)
			)
		)
	)
)

(instance spinDone of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 30])
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(= local0
					(cond 
						(
							(and
								(== (pic1 cel?) (pic2 cel?))
								(== (pic2 cel?) (pic3 cel?))
							)
							(switch (pic1 cel?)
								(0 1000)
								(2 5)
								(3 10)
								(1 20)
							)
						)
						(
						(and (== 2 (pic1 cel?)) (== (pic1 cel?) (pic2 cel?))) 3)
						((== 2 (pic1 cel?)) 1)
						(else
							(switch (Random 0 6)
								(0 (localproc_1388 44 2))
								(1 (localproc_1388 44 3))
								(2 (localproc_1388 44 4))
								(3 (localproc_1388 44 5))
								(4 (localproc_1388 44 6))
								(5 (localproc_1388 44 7))
								(6 (localproc_1388 44 8))
							)
							0
						)
					)
				)
				(= cycles 3)
			)
			(2
				(theMusic2
					number:
						(cond 
							((== local0 1000) 626)
							((>= local0 5) 620)
							((and (< 0 local0) (< local0 5)) 619)
							(else 629)
						)
					loop: 1
					play: self
				)
			)
			(3
				(if (!= local0 1000)
					(= buckazoids
						(+ buckazoids (* local0 insertedBuckazoids))
					)
					(if local0
						(if (not (ego has: 10)) (ego get: 10))
						(localproc_1388
							(Format
								@temp0
								44
								9
								(* local0 insertedBuckazoids)
								buckazoids
							)
						)
						(if (> buckazoids 300)
							(Bset 63)
							(Print 44 10)
							(curRoom newRoom: 43)
						)
					)
					(= insertedBuckazoids 0)
					(= seconds 4)
				else
					(Bset 39)
					(localproc_1388 44 11)
					(curRoom newRoom: 43)
				)
			)
			(4
				(localproc_13b0)
				(info cel: 0 setCycle: Fwd)
				(self dispose:)
			)
		)
	)
)

(instance pic1 of Actor
	(properties
		x 95
		y 52
		description {pretty picture}
		lookStr {These are the slot machine's pretty pictures. Put some money in the slot, push the button, and watch them go 'round and 'round.}
		yStep 11
		view 144
		loop 1
		priority 10
		signal $4810
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 44 12))
			(11 (Print 44 13))
			(12 (Print 44 14))
			(4
				(switch theItem
					(15
						(if (or (pic3 script?) (curRoom script?))
							0
						else
							(Print 44 1)
							(Bset 12)
							(curRoom newRoom: 43)
						)
					)
					(10 (Print 44 15))
					(else 
						(super doVerb: theVerb theItem &rest)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
	
	(method (cue)
		(tmp2 setPri: 0)
		(tmp1 loop: 3)
		(super cue: &rest)
	)
)

(instance pic2 of Actor
	(properties
		x 160
		y 52
		description {pretty picture}
		lookStr {These are the slot machine's pretty pictures. Put some money in the slot, push the button, and watch them go 'round and 'round.}
		yStep 11
		view 144
		loop 1
		cel 3
		priority 10
		signal $4810
	)
	
	(method (doVerb)
		(pic1 doVerb: &rest)
	)
	
	(method (cue)
		(tmp3 setPri: 0)
		(tmp2 loop: 3)
		(super cue: &rest)
	)
)

(instance pic3 of Actor
	(properties
		x 223
		y 52
		description {pretty picture}
		lookStr {These are the slot machine's pretty pictures. Put some money in the slot, push the button, and watch them go 'round and 'round.}
		yStep 11
		view 144
		loop 1
		cel 2
		priority 10
		signal $4810
	)
	
	(method (doVerb)
		(pic1 doVerb: &rest)
	)
	
	(method (cue)
		(curRoom setScript: spinDone)
		(tmp3 loop: 3)
		(theMusic2 hold: 0)
		(super cue: &rest)
	)
)

(instance tmp1 of Actor
	(properties
		x 95
		y 94
		description {pretty picture}
		lookStr {These are the slot machine's pretty pictures. Put some money in the slot, push the button, and watch them go 'round and 'round.}
		yStep 11
		view 144
		loop 1
		signal $4810
	)
	
	(method (doVerb)
		(pic1 doVerb: &rest)
	)
)

(instance tmp2 of Actor
	(properties
		x 160
		y 94
		description {pretty picture}
		lookStr {These are the slot machine's pretty pictures. Put some money in the slot, push the button, and watch them go 'round and 'round.}
		yStep 11
		view 144
		loop 1
		priority 10
		signal $4810
	)
	
	(method (doVerb)
		(pic1 doVerb: &rest)
	)
)

(instance tmp3 of Actor
	(properties
		x 223
		y 94
		description {pretty picture}
		lookStr {These are the slot machine's pretty pictures. Put some money in the slot, push the button, and watch them go 'round and 'round.}
		yStep 11
		view 144
		loop 1
		priority 10
		signal $4810
	)
	
	(method (doVerb)
		(pic1 doVerb: &rest)
	)
)

(instance coinSlot of Feature
	(properties
		nsTop 126
		nsLeft 50
		nsBottom 156
		nsRight 84
		description {buckazoid slot}
		lookStr {This is where you deposit buckazoids. You may deposit up to three buckazoids for each spin.}
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 30])
		(switch theVerb
			(4
				(switch theItem
					(10
						(cond 
							((or (pic3 script?) (curRoom script?)) 0)
							((not buckazoids) (Print 44 16))
							((< insertedBuckazoids 3)
								(soundFx number: 625 loop: 1 play:)
								(if (not insertedBuckazoids)
									(info setCycle: 0 cel: 1 stopUpd:)
								)
								(++ insertedBuckazoids)
								(if (not (-- buckazoids)) (Print 44 17) (ego put: 10))
								(localproc_1388
									(Format @temp0 44 0 insertedBuckazoids buckazoids)
								)
							)
							(else (Print 44 18))
						)
					)
					(15
						(if (or (pic3 script?) (curRoom script?))
							0
						else
							(Print 44 1)
							(Bset 12)
							(curRoom newRoom: 43)
						)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance butn of Feature
	(properties
		nsTop 125
		nsLeft 238
		nsBottom 155
		nsRight 272
		description {button}
		lookStr {Push this button to start the wheels spinning.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3
				(cond 
					((or (pic3 script?) (curRoom script?)) 0)
					((not insertedBuckazoids) (curRoom setScript: pleaseInsert))
					((Btst 13)
						(= local0
							(switch (= local2 (Random 2 4))
								(2 5)
								(3 20)
								(4 10)
							)
						)
					)
					((not buckazoids) (= local2 1))
					((not (Random 0 6)) (= local2 1))
					(else (= local2 0))
				)
				(if
					(and
						insertedBuckazoids
						(not (pic3 script?))
						(not (curRoom script?))
					)
					(soundFx number: 627 loop: 1 play:)
					(theMusic2 number: 628 loop: 1 flags: 1 play: hold: 1)
					(pic1 setPri: 10 setScript: (Clone spinPic) pic1 tmp1)
					(pic2 setPri: 10 setScript: (Clone spinPic) pic2 tmp2)
					(pic3 setPri: 10 setScript: (Clone spinPic) pic3 tmp3)
					(tmp2 setPri: 1)
					(tmp3 setPri: 1)
				)
			)
			(4
				(if (== theItem 15)
					(if (or (pic3 script?) (curRoom script?))
						0
					else
						(Print 44 1)
						(Bset 12)
						(curRoom newRoom: 43)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance pleaseInsert of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(info setCycle: 0 cel: 1)
				(localproc_1388 44 19)
				(= seconds 3)
			)
			(1
				(localproc_13b0)
				(info setCycle: Fwd)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance info of Prop
	(properties
		x 166
		y 133
		description {information window}
		lookStr {This little window tells you how to win, how much you can win, and other useful information.}
		view 144
		cel 3
		priority 14
		signal $0010
		cycleSpeed 150
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(4
				(if (== theItem 15)
					(if (or (pic3 script?) (curRoom script?))
						0
					else
						(Print 44 1)
						(Bset 12)
						(curRoom newRoom: 43)
					)
				else
					(super doVerb: theVerb theItem &rest)
				)
			)
			(else 
				(super doVerb: theVerb theItem &rest)
			)
		)
	)
)

(instance exitBtn of Prop
	(properties
		x 160
		y 186
		description {exit button}
		lookStr {This button takes you back to the bar.}
		view 244
	)
	
	(method (doVerb theVerb theItem)
		(cond 
			((and (== theVerb 4) (== theItem 15))
				(if (or (pic3 script?) (curRoom script?))
					0
				else
					(Print 44 1)
					(Bset 12)
					(curRoom newRoom: 43)
				)
			)
			((OneOf theVerb 3 11 1)
				(if (or (pic3 script?) (curRoom script?))
					0
				else
					(self cel: 1)
					(Animate (cast elements?) 0)
					(Animate (cast elements?) 0)
					(Animate (cast elements?) 0)
					(curRoom newRoom: 43)
				)
			)
			(else (super doVerb: theVerb theItem))
		)
	)
)

(instance slotSound of Sound
	(properties)
)
