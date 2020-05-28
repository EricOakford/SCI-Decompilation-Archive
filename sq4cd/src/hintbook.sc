;;; Sierra Script 1.0 - (do not remove this comment)
(script# HINTBOOK) ;708
(include game.sh)
(use Main)
(use Sq4Feature)
(use System)

(public
	hintBookScript 0
)

(local
	theOverlays =  -1
	userControls
	[sizeBuf 4]
	[local6 12] = [99 1 10 19 27 35 42 50 57 62 64 69]
	local18
	currentPage =  1
	[local20 14]
	local34 =  -1
	local35
	[hintStr 275]
)
(procedure (RestoreLists param1 param2 &tmp temp0 temp1)
	(while
		(and
			(= temp0 (FirstNode (param1 elements?)))
			(IsObject (= temp1 (NodeValue temp0)))
		)
		(param2 add: temp1)
		(param1 delete: temp1)
	)
)

(procedure (localproc_02c3 param1 param2 &tmp temp0)
	(if
		(OneOf param1
			1 6 12 18 24 29 34 39 41 46 50 55 57
			63 65 67
		)
		(RevealHint param1 param2 local18)
		(= temp0 0)
	else
		(Message MsgGet 708 2 0 param1 1 @hintStr)
		(TextSize @sizeBuf @hintStr 30 140)
		(localproc_0627
			(- (+ local18 [sizeBuf 0]) 3)
			(- (+ param2 [sizeBuf 1]) 3)
			(+ local18 [sizeBuf 2])
			(+ param2 [sizeBuf 3])
		)
		(Display
			{\04}
			p_font
			30
			p_at
			(- param2 12)
			(- local18 1)
		)
		(= [local20 (++ local34)]
			((bulletFeat new:)
				x: (- param2 9)
				y: (+ local18 2)
				nsLeft: (- param2 13)
				nsTop: (- local18 1)
				nsRight: (- param2 5)
				nsBottom: (+ local18 5)
				who: param1
				whoX: param2
				whoY: local18
				yourself:
			)
		)
		([local20 local34] init:)
		(if
			(&
				[hiddenHints (/ param1 16)]
				(>> $8000 (mod param1 16))
			)
			(RevealHint param1 param2 local18)
		)
		(= temp0 2)
	)
	(TextSize @sizeBuf @hintStr 30 140)
	(= local18 (+ local18 [sizeBuf 2] 3 temp0))
)

(procedure (TurnPage &tmp temp0 temp1 [temp2 22])
	(= temp0 0)
	(while (< temp0 14)
		(if (IsObject [local20 temp0])
			([local20 temp0] dispose:)
		)
		(= [local20 temp0] 0)
		(++ temp0)
	)
	(= local34 -1)
	(Graph GFillRect 0 0 189 320 3 myTopBordColor 15)
	(Graph GShowBits 0 0 189 320 1)
	(Graph GDrawLine 0 159 189 159 myLowlightColor -1 -1)
	(Graph GShowBits 0 159 190 160 1)
	(Display 708 0 p_font 30 p_at 50 3)
	(Display 708 0 p_font 30 p_at 200 3)
	(Display 708 1 p_font 30 p_at 5 3)
	(Display 708 1 p_font 30 p_at 290 3)
	(if (> currentPage 9)
		(Format @temp2 708 2 currentPage)
	else
		(Format @temp2 708 3 currentPage)
	)
	(Display
		@temp2
		p_font
		30
		p_back
		myTopBordColor
		p_at
		25
		3
	)
	(if (> currentPage 8)
		(Format @temp2 708 2 (+ currentPage 1))
	else
		(Format @temp2 708 3 (+ currentPage 1))
	)
	(Display
		@temp2
		p_font
		30
		p_back
		myTopBordColor
		p_at
		310
		3
	)
	(localproc_0690
		exitBut
		(+ (exitBut nsLeft?) 1)
		(+ (exitBut nsTop?) 2)
		{exit}
	)
	(localproc_0690
		nextBut
		(+ (nextBut nsLeft?) 1)
		(+ (nextBut nsTop?) 2)
		{next}
	)
	(localproc_0690
		prevBut
		(+ (prevBut nsLeft?) 1)
		(+ (prevBut nsTop?) 2)
		{prev}
	)
	(= local18 14)
	(= temp0 [local6 currentPage])
	(while (< temp0 [local6 (+ currentPage 1)])
		(localproc_02c3 temp0 14)
		(++ temp0)
	)
	(++ currentPage)
	(= local18 14)
	(= temp0 [local6 currentPage])
	(while (< temp0 [local6 (+ currentPage 1)])
		(localproc_02c3 temp0 174)
		(++ temp0)
	)
)

(procedure (localproc_0627 param1 param2 param3 param4)
	(Graph
		GDrawLine
		param1
		param2
		param1
		param4
		myLowlightColor
		-1
		-1
	)
	(Graph
		GDrawLine
		param1
		param4
		param3
		param4
		myLowlightColor
		-1
		-1
	)
	(Graph
		GDrawLine
		param3
		param2
		param3
		param4
		myLowlightColor
		-1
		-1
	)
	(Graph
		GDrawLine
		param1
		param2
		param3
		param2
		myLowlightColor
		-1
		-1
	)
	(Graph
		GShowBits
		param1
		param2
		(+ param3 1)
		(+ param4 1)
		1
	)
)

(procedure (localproc_0690 param1 param2 param3 param4)
	(localproc_0627
		(param1 nsTop?)
		(param1 nsLeft?)
		(param1 nsBottom?)
		(param1 nsRight?)
	)
	(Display param4 p_font 30 p_at param2 param3)
)

(procedure (RevealHint param1 param2 param3)
	(if (== param1 33)
		(StrCpy @hintStr {\0E \0B \0C})
	else
		(Message MsgGet 708 2 0 param1 1 @hintStr)
	)
	(Display
		@hintStr
		p_font
		30
		p_width
		140
		p_at
		param2
		param3
	)
)

(instance hintBookScript of Script
	(properties)
	
	(method (doit)
		(if (GameIsRestarting)
			(= start 2)
			(-- currentPage)
			(self init:)
		)
		(super doit:)
	)
	
	(method (dispose &tmp temp0)
		(features release:)
		(curRoom drawPic: register 10)
		(if (!= (= overlays theOverlays) -1)
			(DrawPic overlays FADEOUT FALSE)
		)
		(= temp0 0)
		(while (< temp0 14)
			(if (IsObject [local20 temp0])
				([local20 temp0] dispose:)
			)
			(= [local20 temp0] 0)
			(++ temp0)
		)
		(if (== local35 999)
			(RestoreLists aList addToPics)
			(RestoreLists fList features)
			(RestoreLists cList cast)
			(addToPics doit:)
		)
		(aList
			eachElementDo: #dispose
			eachElementDo: #delete
			release:
			dispose:
		)
		(fList eachElementDo: #perform fdc release: dispose:)
		(cList
			eachElementDo: #dispose
			eachElementDo: #delete
			dispose:
		)
		(user canControl: userControls)
		(if (== curRoomNum 391) (theIconBar enable: 0))
		(super dispose:)
		(DisposeScript 708)
	)
	
	(method (changeState newState &tmp temp0 port)
		(switch (= state newState)
			(0
				(= currentPage 1)
				(= local18 0)
				(ego setMotion: 0)
				(= userControls (user controls?))
				(user canControl: FALSE)
				(= cycles 2)
			)
			(1
				(if (== curRoomNum 391) (theIconBar disable: ICON_WALK))
				(= register (curRoom picture?))
				(= theOverlays overlays)
				(RestoreLists addToPics aList)
				(RestoreLists features fList)
				(RestoreLists cast cList)
				(curRoom drawPic: 699 10)
				(= cycles 2)
			)
			(2
				(= port (GetPort))
				(SetPort port)
				(exitBut init:)
				(nextBut init:)
				(prevBut init:)
				(TurnPage)
				(theGame setCursor: ((inventory curIcon?) cursor?) TRUE)
			)
			(3
				(= local35 999)
				(self dispose:)
			)
		)
	)
)

(instance fdc of Code
	(properties)
	
	(method (doit param1)
		(if (param1 respondsTo: #delete)
			(param1
				signal: (& (param1 signal?) $ffdf)
				dispose:
				delete:
			)
		else
			(param1 dispose:)
		)
	)
)

(instance aList of Collection
	(properties)
)

(instance fList of Collection
	(properties)
)

(instance cList of Collection
	(properties)
)

(instance exitBut of Sq4Feature
	(properties
		x 14
		y 183
		nsTop 178
		nsLeft 4
		nsBottom 188
		nsRight 24
		sightAngle 180
		lookStr 1
	)
	
	(method (doVerb theVerb &tmp ret)
		(= ret 0)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 708 say: 1)
			)
			(V_DO (= ret 1))
			(else  (= ret 1))
		)
		(if ret (hintBookScript cue:))
	)
)

(instance prevBut of Sq4Feature
	(properties
		x 41
		y 183
		nsTop 178
		nsLeft 29
		nsBottom 188
		nsRight 49
		sightAngle 180
		lookStr 2
	)
	
	(method (doVerb theVerb &tmp ret)
		(= ret 0)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 708 say: 2)
			)
			(V_DO (= ret TRUE))
			(else  (= ret TRUE))
		)
		(if (and ret (> currentPage 3))
			(= currentPage (- currentPage 3))
			(TurnPage)
		)
	)
)

(instance nextBut of Sq4Feature
	(properties
		x 54
		y 183
		nsTop 178
		nsLeft 54
		nsBottom 188
		nsRight 74
		sightAngle 180
		lookStr 3
	)
	
	(method (doVerb theVerb &tmp ret)
		(= ret 0)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 708 say: 3)
			)
			(V_DO (= ret TRUE))
			(else  (= ret TRUE))
		)
		(if (and ret (< currentPage 10))
			(++ currentPage)
			(TurnPage)
		)
	)
)

(class BulletFeat of Sq4Feature
	(properties
		who 0
		whoX 0
		whoY 0
	)
)

(instance bulletFeat of BulletFeat
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 708 say: 4)
			)
			(V_DO
				(narrator modNum: 708 say: 5)
			)
			(V_PEN
				(cond 
					((> revealedHints 100) (narrator modNum: 708 say: 6))
					(
					(& [hiddenHints (/ who 16)] (>> $8000 (mod who 16))) 0)
					(else
						(= [hiddenHints (/ who 16)]
							(| [hiddenHints (/ who 16)] (>> $8000 (mod who 16)))
						)
						(RevealHint who whoX whoY)
						(if (== who 33)
							(Animate (cast elements?) FALSE)
							(narrator modNum: 708 say: 7)
						)
						(++ revealedHints)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
