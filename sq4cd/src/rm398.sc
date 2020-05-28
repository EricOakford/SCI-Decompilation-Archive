;;; Sierra Script 1.0 - (do not remove this comment)
(script# 398)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Narrator)
(use Sq4Feature)
(use User)
(use System)

(public
	rm398 0
)

(local
	local0
	local1
	theBoxProp
	theTheBoxProp
	local4
	boxShown
	saveBits
	local7
	local8
	theBoxProp_2
	oldSortedFeatures
	oldSpeed
	[str 100]
	[priceButBuf 30]
	[keepButBuf 30]
	[resumeButBuf 30]
	[doneButBuf 30]
	[local232 40]
	[priceBuf 40]
)

(enum ;box IDs
	boxNONE
	boxCLUCKEGGER
	boxCHECKERBOARD
	boxBOOM
	boxHINTBOOK
	boxHYMIE
	boxKQ43
	boxSIMSIM
	boxSTUNTFLYER
	boxDESSERT
	boxDACRONDANNY
)

(instance rm398 of SQRoom
	(properties
		picture 398
	)
	
	(method (init)
		(myIcon init:)
		(super init:)
		(HandsOn)
		(theRoom init:)
		(= oldSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(box1 posn: 218 125 init: stopUpd:)
		(box2 posn: 175 123 init: stopUpd:)
		(box3 posn: 50 150 init: stopUpd:)
		(box5 posn: 130 143 init: stopUpd:)
		(box6 posn: 216 83 init: stopUpd:)
		(box7 posn: 142 94 init: stopUpd:)
		(box9 posn: 152 164 init: stopUpd:)
		(if (not (Btst fBoughtHintbook))
			(box10 posn: 186 128 init: stopUpd:)
		)
		(= local4 11)
		(cast eachElementDo: #perform syncPriority)
		(= oldSpeed speed)
		(= speed 0)
		(theIconBar disable: ICON_WALK ICON_TALK ICON_SMELL ICON_TASTE ICON_ITEM)
	)
	
	(method (dispose)
		(theIconBar enable:)
		(= useSortedFeatures oldSortedFeatures)
		(= speed oldSpeed)
		(super dispose:)
	)
)

(instance showBox of Script
	(properties)
	
	(method (doit &tmp temp0 temp1)
		(super doit:)
		(if
			(and
				(or
					(== (= temp0 ((User curEvent?) type?)) mouseDown)
					(== temp0 4)
				)
				state
			)
			(= temp0 0)
			(self cue:)
		)
	)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(curRoom drawPic: (+ 699 boxShown) 100)
				(cast eachElementDo: #hide)
				(scriptHandleEvent init:)
				(= cycles 1)
			)
			(1
				(switch boxShown
					(boxCLUCKEGGER
						(salesPitch talkWidth: 160 x: 160 y: 10 say: 1 self)
					)
					(boxCHECKERBOARD
						(salesPitch talkWidth: 160 x: 160 y: 10 say: 2 self)
					)
					(boxBOOM
						(salesPitch talkWidth: 160 x: 160 y: 10 say: 3 self)
					)
					(boxHINTBOOK
						(salesPitch talkWidth: 170 x: 150 y: 1 say: 4 self)
					)
					(boxHYMIE
						(salesPitch talkWidth: 160 x: 160 y: 10 say: 5 self)
					)
					(boxKQ43
						(salesPitch talkWidth: 160 x: 160 y: 10 say: 6 self)
					)
					(boxSIMSIM
						(salesPitch talkWidth: 160 x: 160 y: 5 say: 7 self)
					)
					(boxSTUNTFLYER
						(salesPitch talkWidth: 160 x: 160 y: 5 say: 8 self)
					)
					(boxDESSERT
						(salesPitch talkWidth: 160 x: 160 y: 5 say: 9 self)
					)
					(boxDACRONDANNY
						(salesPitch talkWidth: 160 x: 160 y: 5 say: 10 self)
					)
				)
			)
			(2
				(Message MsgGet 398 97 0 1 1 @str)
				(Message MsgGet 398 97 0 2 1 @priceButBuf)
				(Message MsgGet 398 97 0 3 1 @keepButBuf)
				(Message MsgGet 398 97 0 4 1 @resumeButBuf)
				(Message MsgGet 398 97 0 13 1 @doneButBuf)
				(switch
					(SQ4Print @str
						#mode teJustLeft
						#button @priceButBuf 0
						#button @keepButBuf 1
						#button @resumeButBuf 2
						#button @doneButBuf 3
					)
					(0
						(Message MsgGet 398 99 0 14 1 @priceBuf)
						(Format @local232 @priceBuf (theBoxProp_2 myPrice?))
						(= temp0
							(Display @local232
								p_at 20 180
								p_width 120
								p_color myTextColor2
								p_save
							)
						)
						(= state (- state 1))
						(= cycles 1)
					)
					(1
						(if (!= boxShown 4)
							(narrator say: 12)
							(= state (- state 1))
							(= cycles 1)
						else
							(ego get: iHintbook)
							(ego get: iPen)
							(= theBoxProp 0)
							(= heldBox boxShown)
							(box10 dispose:)
						)
					)
					(2 0)
					(3
						(theIconBar enable:)
						(curRoom newRoom: 397)
					)
				)
				(scriptHandleEvent dispose:)
				(= cycles 1)
			)
			(3
				(theIconBar disable:)
				(Display 398 0 p_restore temp0)
				(= cycles 1)
			)
			(4
				(cast eachElementDo: #show)
				(curRoom drawPic: 398)
				(= cycles 1)
			)
			(5
				(theIconBar enable:)
				(client setScript: 0)
			)
		)
	)
)

(instance syncPriority of Code
	(properties)
	
	(method (doit param1)
		(-- local4)
		(param1 setPri: local4)
	)
)

(class boxProp of Sq4Prop
	(properties
		boxID 0
		pIndex -1
		tooHigh 0
		myPrice 0
	)
	
	(method (init)
		(super init:)
		(if (== boxID 4)
			(self myPrice: 5)
		else
			(self myPrice: (+ buckazoids (Random 1 15)))
		)
		(if (== heldBox (self boxID?))
			(self delete:)
			(self dispose:)
		)
	)
	
	(method (doit &tmp userCurEvent [temp1 2])
		(super doit:)
		(if
			(and
				(== theBoxProp self)
				(== ((theIconBar curIcon?) message?) 4)
			)
			(= local0 ((= userCurEvent (User curEvent?)) x?))
			(= local1 (+ (userCurEvent y?) 10))
			(if (> local0 235) (= local0 235))
			(if (< local0 50) (= local0 50))
			(if (> local1 155) (= local1 155))
			(self x: local0 y: local1)
		)
		(if (== (self tooHigh?) 1)
			(if (< (self y?) (Random 110 160))
				(self y: (+ (self y?) 15) startUpd:)
			else
				(self stopUpd:)
				(= tooHigh 0)
			)
		)
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb V_DO)
				(cond 
					((== theBoxProp 0)
						(self startUpd:)
						(= theBoxProp self)
						(self doit:)
						(cast delete: self)
						(cast addToFront: self)
						(self pIndex: 12)
					)
					((== theBoxProp self)
						(if (!= theBoxProp theTheBoxProp)
							(= theTheBoxProp theBoxProp)
							(= local4 11)
							(cast eachElementDo: #perform syncPriority)
						)
						(= theBoxProp 0)
						(self pIndex: 11 stopUpd:)
						(if (< (self y?) 90) (= tooHigh 1))
					)
				)
				(self setPri: pIndex)
			)
			((== theVerb V_LOOK)
				(if (and (!= theBoxProp 0) (!= theBoxProp self))
					0
				else
					(= boxShown (self boxID?))
					(= theBoxProp_2 self)
					(curRoom setScript: showBox)
				)
			)
			(else (super doVerb: theVerb))
		)
	)
	
	(method (onMe param1 param2 &tmp temp0 temp1 temp2 temp3)
		(if (IsObject param1)
			(= temp0 (param1 x?))
			(= temp1 (param1 y?))
		else
			(= temp0 param1)
			(= temp1 param2)
		)
		(= temp3
			(if (super onMe: temp0 temp1)
				(if (== theBoxProp self)
				else
					(&
						(= temp2 (OnControl 2 temp0 temp1))
						(<< $0001 priority)
					)
				)
			else
				0
			)
		)
	)
)

(instance box1 of boxProp
	(properties
		view 399
		boxID 1
	)
)

(instance box2 of boxProp
	(properties
		view 399
		cel 1
		boxID 2
	)
)

(instance box3 of boxProp
	(properties
		view 399
		cel 2
		boxID 3
	)
)

(instance box10 of boxProp
	(properties
		view 399
		cel 3
		boxID 4
	)
)

(instance box5 of boxProp
	(properties
		view 399
		cel 4
		boxID 5
	)
)

(instance box6 of boxProp
	(properties
		view 399
		cel 5
		boxID 6
	)
)

(instance box7 of boxProp
	(properties
		view 399
		cel 6
		boxID 7
	)
)

(instance box9 of boxProp
	(properties
		view 399
		cel 8
		boxID 9
	)
)

(instance theRoom of Sq4Feature
	(properties
		x 159
		y 94
		nsTop -1
		nsBottom 189
		nsRight 319
		sightAngle 90
	)
)

(instance myIcon of Sq4Prop
	(properties
		x 280
		y 30
		view 399
		loop 1
	)
	
	(method (doVerb)
		(self setScript: doneWasClicked)
	)
)

(instance doneWasClicked of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(myIcon setCel: 1)
				(= cycles 3)
			)
			(1
				(myIcon setCel: 0)
				(= cycles 1)
			)
			(2
				(curRoom newRoom: 397)
				(self dispose:)
			)
		)
	)
)

(instance salesPitch of Sq4Narrator
	(properties
		noun 99
		modNum 398
		modeless TRUE
		nMsgType 3
	)
	
	(method (dispose)
		(Display 398 0 p_restore saveBits)
		(super dispose: &rest)
	)
	
	(method (display theText)
		(= saveBits
			(Display theText
				p_width talkWidth
				p_at x y
				p_mode teJustLeft
				p_font 68
				p_color myTextColor2
				p_save
			)
		)
	)
)

(instance scriptHandleEvent of Sq4Feature
	(properties)
	
	(method (handleEvent event)
		(event claimed: TRUE)
		(= local8 1)
	)
)
