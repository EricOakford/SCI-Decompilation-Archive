;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include game.sh) (include "460.shm")
(use Main)
(use SQRoom)
(use DText)
(use String)
(use Array)
(use Feature)
(use Sound)
(use Actor)
(use System)

(public
	rm460 0
)

(local
	[newButton 10]
	[local10 10] = [201 185 201 217 185 201 217 185 201 217]
	[local20 10] = [109 68 68 68 81 81 81 95 95 95]
	[newDText 10]
	[newDText_2 4]
	local44
	local45
	local46
	local47
	local48
	theValue =  -1
	theTheValue
	local51
	local52
	local53
	local54
	local55
	local56 =  1
	local57
	theTheValue_2
	local59
	theTheValue_3
	theTheValue_4
	local62
	local63
	local64
)
(procedure (localproc_21ec &tmp temp0)
	(= temp0 0)
	(while (< temp0 10)
		(if [newDText temp0]
			([newDText temp0] dispose:)
			(= [newDText temp0] 0)
		)
		(++ temp0)
	)
	(= local55 0)
	(= temp0 1)
	(while (< temp0 4)
		(if [newDText_2 temp0]
			([newDText_2 temp0] dispose:)
			(= [newDText_2 temp0] 0)
		)
		(++ temp0)
	)
	(= theTheValue_3 0)
)

(procedure (localproc_2245 param1 param2)
	(if (or (< argc 2) (and (> argc 1) param2))
		(if local55 ([newDText local55] fore: 102 draw:))
		([newDText param1] fore: 145 draw:)
		(= local55 param1)
	else
		([newDText param1] fore: 102 draw:)
		(= local55 0)
	)
)

(procedure (localproc_2296 param1 &tmp temp0 temp1)
	(if (< argc 7)
		(Message
			MsgGet
			460
			[param1 0]
			[param1 1]
			[param1 2]
			[param1 3]
			(local44 data?)
		)
		(= temp0 [param1 4])
		(= temp1 [param1 5])
	else
		(Message
			MsgGet
			460
			[param1 0]
			[param1 1]
			[param1 2]
			[param1 3]
			(local45 data?)
		)
		(local44 format: {%s %s} [param1 4] (local45 data?))
		(= temp0 [param1 5])
		(= temp1 [param1 6])
	)
	(Text TextSize (local46 data?) (local44 data?) 460 110)
	((= [newDText temp0] (DText new:))
		font: 460
		text: (String StrDup (local44 data?))
		fore: 102
		back: 101
		x:
			(if (== temp1 -1)
				(+
					65
					(/ (- 110 (- (local46 at: 2) (local46 at: 0))) 2)
				)
			else
				(+ 65 (* temp1 3))
			)
		y: (+ 27 (* 5 temp0))
		setSize: 110
		setPri: (+ (GetHighPlanePri) 1)
		init:
	)
)

(procedure (localproc_23c6 param1 param2 param3 param4 param5 param6)
	(if [newDText param5]
		([newDText param5] dispose:)
		(= [newDText param5] 0)
	)
	(Message
		MsgGet
		460
		param1
		param2
		param3
		param4
		(local44 data?)
	)
	(if (!= theValue -1)
		(local47 format: {%s %d} (local44 data?) theValue)
	else
		(local47 dispose:)
		(= local47 (String newCopies: (local44 data?)))
	)
	(Text TextSize (local46 data?) (local47 data?) 460 110)
	((= [newDText param5] (DText new:))
		font: 460
		text: (String StrDup (local47 data?))
		fore: 102
		back: 101
		x:
			(if (== param6 -1)
				(+
					65
					(/ (- 110 (- (local46 at: 2) (local46 at: 0))) 2)
				)
			else
				(+ 65 (* param6 3))
			)
		y: (+ 27 (* 5 param5))
		setSize: 110
		setPri: (+ (GetHighPlanePri) 1)
		init:
	)
)

(procedure (localproc_24e3 param1 param2 param3 param4 param5 param6)
	(Message
		MsgGet
		460
		param1
		param2
		param3
		param4
		(local44 data?)
	)
	(Text TextSize (local46 data?) (local44 data?) 460 110)
	((= [newDText param5] (DText new:))
		font: 460
		text: (String StrDup (local44 data?))
		fore: 102
		back: 101
		x:
			(if (== param6 -1)
				(+
					65
					(/ (- 110 (- (local46 at: 2) (local46 at: 0))) 2)
				)
			else
				(+ 65 (* param6 3))
			)
		y: (+ 27 (* 5 param5))
		setSize: 110
		setPri: (+ (GetHighPlanePri) 1)
		init:
	)
	(= local51 16)
	(cond 
		(
			(and
				(== param4 1)
				(not
					(Message
						MsgGet
						460
						param1
						param2
						param3
						2
						(local44 data?)
					)
				)
			)
			(localproc_271e 6 49 0 1 {@.} 1 14)
			(localproc_27c7 1)
			(= local59 0)
		)
		(
			(and
				(== param4 1)
				(Message
					MsgGet
					460
					param1
					param2
					param3
					2
					(local44 data?)
				)
			)
			(localproc_271e 6 50 0 1 {@.} 1 8)
			(localproc_271e 6 49 0 1 {2.} 2 20)
			(localproc_27c7 1)
			(= local59 1)
		)
		(
			(and
				(Message
					MsgGet
					460
					param1
					param2
					param3
					(- param4 1)
					(local44 data?)
				)
				(not
					(Message
						MsgGet
						460
						param1
						param2
						param3
						(+ param4 1)
						(local44 data?)
					)
				)
			)
			(localproc_271e 6 39 0 1 {@.} 1 8)
			(localproc_271e 6 49 0 1 {2.} 2 20)
			(localproc_27c7 2)
			(= local59 2)
		)
		(else
			(localproc_271e 6 50 0 1 {@.} 1 2)
			(localproc_271e 6 39 0 1 {2.} 2 14)
			(localproc_271e 6 49 0 1 {3.} 3 27)
			(if local63 (localproc_27c7 2) else (localproc_27c7 1))
			(= local59 3)
		)
	)
)

(procedure (localproc_271e param1 &tmp temp0 temp1)
	(Message
		MsgGet
		460
		[param1 0]
		[param1 1]
		[param1 2]
		[param1 3]
		(local45 data?)
	)
	(local44 format: {%s %s} [param1 4] (local45 data?))
	(= temp0 [param1 5])
	(= temp1 [param1 6])
	((= [newDText_2 temp0] (DText new:))
		font: 460
		text: (String StrDup (local44 data?))
		fore: 102
		back: 101
		x: (+ 65 (* temp1 3))
		y: 72
		setSize: 110
		setPri: (+ (GetHighPlanePri) 1)
		init:
	)
)

(procedure (localproc_27c7 theTheTheValue_3 param2)
	(if (or (< argc 2) (and (> argc 1) param2))
		(if theTheValue_3
			([newDText_2 theTheValue_3] fore: 102 draw:)
		)
		([newDText_2 theTheTheValue_3] fore: 145 draw:)
		(= theTheValue_3 theTheTheValue_3)
	else
		([newDText_2 theTheTheValue_3] fore: 102 draw:)
		(= theTheValue_3 0)
	)
)

(instance rm460 of SQRoom
	(properties
		picture 460
	)
	
	(method (init &tmp temp0)
		(super init:)
		(theGame handsOn:)
		(theIconBar select: (theIconBar at: ICON_DO) disable: ICON_TALK ICON_INVENTORY)
		(= local44 (String newWith: 80 {}))
		(= local45 (String newWith: 80 {}))
		(= local47 (String newWith: 80 {}))
		(= local46 (IntArray newWith: 4 {}))
		(compost init:)
		(intraShip init:)
		(comm init:)
		(database init:)
		(cyberSpace init:)
		(= temp0 0)
		(while (< temp0 10)
			((= [newButton temp0] (Button new:))
				init:
				setLoop: temp0
				posn: [local10 temp0] [local20 temp0]
				value: temp0
			)
			(++ temp0)
		)
		(enterButton init:)
		(leftArrow init:)
		(upArrow init:)
		(rightArrow init:)
		(downArrow init:)
		(localproc_2296 1 0 10 1 0 -1)
		(localproc_2296 1 0 11 1 2 -1)
		(localproc_2296 1 0 2 1 3 -1)
		(localproc_2296 1 0 3 1 4 -1)
		(localproc_2296 1 0 4 1 5 -1)
		(localproc_2296 1 0 5 1 6 -1)
		(= selectedRoom 0)
		(theIconBar setupExit: 1)
	)
	
	(method (dispose)
		(theIconBar setupExit: 0)
		(theIconBar enable: ICON_TALK)
		(local44 dispose:)
		(local45 dispose:)
		(local47 dispose:)
		(local46 dispose:)
		(super dispose:)
	)
	
	(method (doVerb)
		(= selectedRoom 0)
		(curRoom newRoom: prevRoomNum)
	)
)

(class DoitFeature of Feature
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle ftrDefault
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
	)
	
	(method (init param1)
		(super init: &rest)
		(theDoits add: self)
	)
	
	(method (doit)
		(if
		(not ((theIconBar plane?) onMe: (user curEvent?)))
			(cond 
				(
					(and
						(user canInput:)
						(not (self onMe: (user curEvent?)))
						(!= theCursor exitCursor)
					)
					(theGame setCursor: exitCursor TRUE)
				)
				(
					(and
						(user canInput:)
						(self onMe: (user curEvent?))
						(!= theCursor ((theIconBar curIcon?) getCursor:))
					)
					(theGame setCursor: ((theIconBar curIcon?) getCursor:))
				)
			)
		)
	)
	
	(method (dispose)
		(theDoits delete: self)
		(super dispose:)
	)
)

(class Button of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle ftrDefault
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 201
		y 109
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view 461
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal (| ignrAct setBaseRect canUpdate) ;$4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		value 0
	)
	
	(method (doit)
		(if scratch
			(if (> (Abs (- gameTime scratch)) 20)
				(self setCel: 0 scratch: 0)
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb &tmp temp0)
		(switch theVerb
			(V_DO
				(sfxButton play:)
				(= scratch gameTime)
				(self setCel: 1)
				(cond 
					(theTheValue_3
						(cond 
							((or (== local59 1) (== local59 2))
								(if (and (< 0 value) (< value 3))
									(localproc_27c7 value)
								)
							)
							((and (== local59 3) (< 0 value) (< value 4)) (localproc_27c7 value))
						)
					)
					(local48
						(= theValue value)
						(localproc_23c6 7 0 13 1 9 -1)
						(cond 
							((== local51 2)
								(switch theValue
									(1 (localproc_2245 3))
									(2 (localproc_2245 5))
									(else 
										(= temp0 local55)
										(localproc_2245 local55 0)
										(= local55 temp0)
									)
								)
							)
							(
								(and
									(< 0 theValue)
									(< theValue (+ (- local54 local53) 2))
								)
								(localproc_2245 (+ local53 (- theValue 1)))
							)
							(else
								(= temp0 local55)
								(localproc_2245 local55 0)
								(= local55 temp0)
							)
						)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(class ArrowCursor of View
	(properties
		scratch 0
		heading 0
		noun 0
		case 0
		modNum -1
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		sightAngle ftrDefault
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y 0
		z 0
		scaleX 128
		scaleY 128
		maxScale 128
		scaleType 0
		priority 0
		fixPriority 0
		inLeft 0
		inTop 0
		inRight 0
		inBottom 0
		useInsetRect 0
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $4021
		lsLeft 0
		lsTop 0
		lsRight 0
		lsBottom 0
		brLeft 0
		brTop 0
		brRight 0
		brBottom 0
		scaleSignal $0000
		magnifier 0
		oldScaleX 128
		theDirection 0
	)
	
	(method (doVerb theVerb &tmp newEvent temp1 theGameTime)
		(switch theVerb
			(4
				(= cel (= temp1 1))
				(UpdateScreenItem self)
				(FrameOut)
				(= scratch gameTime)
				(= theGameTime 0)
				(while (!= ((= newEvent (Event new:)) type?) 2)
					(newEvent localize: plane)
					(cond 
						((self onMe: newEvent)
							(if (not temp1)
								(= cel (= temp1 1))
								(UpdateScreenItem self)
								(FrameOut)
							)
							(if
								(and
									(not (mod (Abs (- gameTime scratch)) 30))
									(!= theGameTime gameTime)
								)
								(if theTheValue_3
									(cond 
										((or (== local59 1) (== local59 2))
											(if (== theTheValue_3 1)
												(localproc_27c7 2)
											else
												(localproc_27c7 1)
											)
										)
										((and (== local59 3) (== theDirection 2))
											(if (== theTheValue_3 3)
												(localproc_27c7 1)
											else
												(localproc_27c7 (+ theTheValue_3 1))
											)
										)
										((and (== local59 3) (== theDirection 4))
											(if (== theTheValue_3 1)
												(localproc_27c7 3)
											else
												(localproc_27c7 (- theTheValue_3 1))
											)
										)
									)
								)
								(if local55
									(if (== local51 2)
										(if (== local55 3)
											(localproc_2245 5)
											(= theValue 2)
										else
											(localproc_2245 3)
											(= theValue 1)
										)
										(localproc_23c6 7 0 13 1 9 -1)
									else
										(switch theDirection
											(3
												(if (< local55 local54)
													(localproc_2245 (+ local55 1))
													(= theValue (+ (- local55 local53) 1))
												else
													(localproc_2245 local53)
													(= theValue 1)
												)
												(localproc_23c6 7 0 13 1 9 -1)
											)
											(1
												(if (> local55 local53)
													(localproc_2245 (- local55 1))
													(= theValue (+ (- local55 local53) 1))
												else
													(localproc_2245 local54)
													(= theValue (+ (- local54 local53) 1))
												)
												(localproc_23c6 7 0 13 1 9 -1)
											)
										)
									)
								)
								(FrameOut)
							)
							(= theGameTime gameTime)
							(= gameTime (+ tickOffset (GetTime)))
						)
						(temp1 (= cel (= temp1 0)) (UpdateScreenItem self) (FrameOut))
					)
					(newEvent dispose:)
				)
				(newEvent dispose:)
				(if (== temp1 1)
					(= cel 0)
					(UpdateScreenItem self)
					(FrameOut)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance compost of DoitFeature
	(properties
		nsLeft 56
		nsTop 17
		nsRight 265
		nsBottom 125
		x 160
		z 71
	)
)

(instance intraShip of View
	(properties
		x 61
		y 88
		view 460
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(self setCel: 1)
				(comm setCel: 0)
				(database setCel: 0)
				(cyberSpace setCel: 0)
				(localproc_21ec)
				(localproc_2296 7 0 10 1 0 -1)
				(localproc_2296 7 0 1 1 1 -1)
				(localproc_2296 7 0 2 1 {@.} 3 6)
				(localproc_2296 7 0 3 1 {2.} 4 6)
				(localproc_2296 7 0 4 1 {3.} 5 6)
				(localproc_2296 7 0 5 1 {4.} 6 6)
				(localproc_2245 3)
				(= local53 3)
				(= local54 6)
				(= theValue 1)
				(= local51 1)
				(= local48 1)
				(localproc_23c6 7 0 13 1 9 -1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance comm of View
	(properties
		x 61
		y 97
		view 460
		loop 1
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(= local51 0)
				(= local48 0)
				(= theValue -1)
				(= local55 0)
				(self setCel: 1)
				(intraShip setCel: 0)
				(database setCel: 0)
				(cyberSpace setCel: 0)
				(localproc_21ec)
				(localproc_2296 5 0 15 1 0 -1)
				(localproc_2296 5 0 3 1 2 0)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance database of View
	(properties
		x 61
		y 105
		view 460
		loop 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(self setCel: 1)
				(intraShip setCel: 0)
				(comm setCel: 0)
				(cyberSpace setCel: 0)
				(localproc_21ec)
				(localproc_2296 6 0 10 1 0 -1)
				(localproc_2296 6 0 11 1 {@.} 2 8)
				(localproc_2296 6 0 2 1 {2.} 3 8)
				(localproc_2296 6 0 3 1 {3.} 4 8)
				(localproc_2245 2)
				(= local53 2)
				(= local54 4)
				(= local51 3)
				(= local48 1)
				(= theValue 1)
				(localproc_23c6 7 0 13 1 9 -1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance cyberSpace of View
	(properties
		x 61
		y 114
		view 460
		loop 3
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(= local51 0)
				(= local48 0)
				(= theValue -1)
				(= local55 0)
				(self setCel: 1)
				(intraShip setCel: 0)
				(comm setCel: 0)
				(database setCel: 0)
				(localproc_21ec)
				(localproc_2296 4 0 0 1 2 -1)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance enterButton of Button
	(properties
		x 233
		y 68
		loop 10
		value 10
	)
	
	(method (doit)
		(super doit:)
		(if local52 (++ local64))
		(if (and local52 (> local64 150))
			(curRoom newRoom: prevRoomNum)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if (not (sfxEnter handle?)) (sfxEnter play:))
				(= scratch gameTime)
				(self setCel: 1)
				(cond 
					((== local51 1)
						(cond 
							((and (< 0 theValue) (< theValue 5))
								(switch (= theTheValue theValue)
									(1 (= selectedRoom 360))
									(2 (= selectedRoom 390))
									(3 (= selectedRoom 430))
									(4 (= selectedRoom 450))
								)
								(if (== selectedRoom prevRoomNum)
									(curRoom setScript: sInvalidEntry 0 1)
								else
									(localproc_21ec)
									(localproc_2296 7 29 2 1 {@.} 3 6)
									(localproc_2296 7 30 4 1 {2.} 5 6)
									(= local55 0)
									(= local53 3)
									(= local54 5)
									(localproc_2245 3)
									(= theValue 1)
									(= local51 2)
									(localproc_23c6 7 0 13 1 9 -1)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 2)
						(cond 
							((== theValue 1) (theGame handsOff:) (= local64 0) (= local52 1))
							((== theValue 2) (= selectedRoom 0) (intraShip doVerb: V_DO))
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 3)
						(cond 
							((and (< 0 theValue) (< theValue 4))
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 6 0 11 1 0 -1)
										(localproc_2296 8 33 0 1 {@.} 2 8)
										(localproc_2296 8 34 0 1 {2.} 3 8)
										(localproc_2296 6 49 0 1 {3.} 4 8)
										(localproc_2245 2)
										(= local53 2)
										(= local54 4)
										(= local51 4)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(2
										(localproc_21ec)
										(localproc_2296 6 0 2 1 0 -1)
										(localproc_2296 9 35 0 1 {@.} 2 8)
										(localproc_2296 9 36 0 1 {2.} 3 8)
										(localproc_2296 6 49 0 1 {3.} 4 8)
										(localproc_2245 2)
										(= local53 2)
										(= local54 4)
										(= local51 5)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(3
										(localproc_21ec)
										(localproc_2296 6 0 3 1 0 -1)
										(localproc_2296 10 37 0 1 {@.} 2 6)
										(localproc_2296 10 38 0 1 {2.} 3 6)
										(localproc_2296 6 49 0 1 {3.} 4 6)
										(localproc_2245 2)
										(= local53 2)
										(= local54 4)
										(= local51 6)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 4)
						(cond 
							((and (< 0 theValue) (< theValue 4))
								(= local57 4)
								(= theTheValue_2 theValue)
								(= local62 3)
								(= theTheValue_4 1)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 8 33 0 1 0 -1)
										(localproc_24e3 8 33 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 8 34 0 1 0 -1)
										(localproc_2296 12 45 0 1 {@.} 2 13)
										(localproc_2296 12 46 0 1 {2.} 3 13)
										(localproc_2296 12 47 0 1 {3.} 4 13)
										(localproc_2296 12 48 0 1 {4.} 5 13)
										(localproc_2296 6 49 0 1 {5.} 6 13)
										(localproc_2245 2)
										(= local53 2)
										(= local54 6)
										(= local51 7)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(3 (database doVerb: V_DO))
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 5)
						(cond 
							((and (< 0 theValue) (< theValue 4))
								(= local57 5)
								(= theTheValue_2 theValue)
								(= local62 3)
								(= theTheValue_4 2)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 9 35 0 1 0 -1)
										(localproc_24e3 9 35 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 9 36 0 1 0 -1)
										(localproc_24e3 9 36 16 local56 2 0)
									)
									(3 (database doVerb: V_DO))
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 6)
						(cond 
							((and (< 0 theValue) (< theValue 4))
								(= local57 6)
								(= theTheValue_2 theValue)
								(= local62 3)
								(= theTheValue_4 3)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 10 37 0 1 0 -1)
										(localproc_24e3 10 37 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 10 38 0 1 0 -1)
										(localproc_24e3 10 38 16 local56 2 0)
									)
									(3 (database doVerb: V_DO))
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 7)
						(cond 
							((and (< 0 theValue) (< theValue 6))
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 11 0 15 1 0 -1)
										(localproc_2296 11 40 0 1 {@.} 2 6)
										(localproc_2296 11 41 0 1 {2.} 3 6)
										(localproc_2296 11 42 0 1 {3.} 4 6)
										(localproc_2296 11 43 0 1 {4.} 5 6)
										(localproc_2296 6 51 0 1 {5.} 6 6)
										(localproc_2296 6 49 0 1 {6.} 7 6)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 8)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(2
										(localproc_21ec)
										(localproc_2296 13 0 15 1 0 -1)
										(localproc_2296 13 40 0 1 {@.} 2 0)
										(localproc_2296 13 41 0 1 {2.} 3 0)
										(localproc_2296 13 42 0 1 {3.} 4 0)
										(localproc_2296 13 43 0 1 {4.} 5 0)
										(localproc_2296 6 51 0 1 {5.} 6 0)
										(localproc_2296 6 49 0 1 {6.} 7 0)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 10)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(3
										(localproc_21ec)
										(localproc_2296 14 0 15 1 0 -1)
										(localproc_2296 14 40 0 1 {@.} 2 3)
										(localproc_2296 14 41 0 1 {2.} 3 3)
										(localproc_2296 14 42 0 1 {3.} 4 3)
										(localproc_2296 14 43 0 1 {4.} 5 3)
										(localproc_2296 6 51 0 1 {5.} 6 3)
										(localproc_2296 6 49 0 1 {6.} 7 3)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 13)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(4
										(localproc_21ec)
										(localproc_2296 15 0 15 1 0 -1)
										(localproc_2296 15 40 0 1 {@.} 2 8)
										(localproc_2296 15 41 0 1 {2.} 3 8)
										(localproc_2296 15 42 0 1 {3.} 4 8)
										(localproc_2296 15 43 0 1 {4.} 5 8)
										(localproc_2296 6 49 0 1 {5.} 6 8)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 15)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(5
										(= local51 3)
										(= theValue 1)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 8)
						(cond 
							((and (< 0 theValue) (< theValue 7))
								(= local57 8)
								(= theTheValue_2 theValue)
								(= local62 7)
								(= theTheValue_4 1)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 11 40 0 1 0 -1)
										(localproc_24e3 11 40 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 11 41 0 1 0 -1)
										(localproc_24e3 11 41 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 11 42 0 1 0 -1)
										(localproc_24e3 11 42 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 11 43 0 1 0 -1)
										(localproc_24e3 11 43 16 local56 2 0)
									)
									(5
										(localproc_21ec)
										(localproc_2296 11 0 15 1 0 -1)
										(localproc_2296 11 44 0 1 {@.} 2 4)
										(localproc_2296 11 52 0 1 {2.} 3 4)
										(localproc_2296 11 53 0 1 {3.} 4 4)
										(localproc_2296 11 54 0 1 {4.} 5 4)
										(localproc_2296 6 51 0 1 {5.} 6 4)
										(localproc_2296 6 49 0 1 {6.} 7 4)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 9)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(6
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 9)
						(cond 
							((and (< 0 theValue) (< theValue 7))
								(= local57 9)
								(= theTheValue_2 theValue)
								(= local62 8)
								(= theTheValue_4 5)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 11 44 0 1 0 -1)
										(localproc_24e3 11 44 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 11 52 0 1 0 -1)
										(localproc_24e3 11 52 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 11 53 0 1 0 -1)
										(localproc_24e3 11 53 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 11 54 0 1 0 -1)
										(localproc_24e3 11 54 16 local56 2 0)
									)
									(5
										(= local51 7)
										(= theValue 1)
										(enterButton doVerb: V_DO)
									)
									(6
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 10)
						(cond 
							((and (< 0 theValue) (< theValue 7))
								(= local57 10)
								(= theTheValue_2 theValue)
								(= local62 7)
								(= theTheValue_4 2)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 13 40 0 1 0 -1)
										(localproc_24e3 13 40 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 13 41 0 1 0 -1)
										(localproc_24e3 13 41 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 13 42 0 1 0 -1)
										(localproc_24e3 13 42 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 13 43 0 1 0 -1)
										(localproc_24e3 13 43 16 local56 2 0)
									)
									(5
										(localproc_21ec)
										(localproc_2296 13 0 15 1 0 -1)
										(localproc_2296 13 44 0 1 {@.} 2 4)
										(localproc_2296 13 52 0 1 {2.} 3 4)
										(localproc_2296 13 53 0 1 {3.} 4 4)
										(localproc_2296 13 54 0 1 {4.} 5 4)
										(localproc_2296 6 51 0 1 {5.} 6 4)
										(localproc_2296 6 49 0 1 {6.} 7 4)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 11)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(6
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 11)
						(cond 
							((and (< 0 theValue) (< theValue 7))
								(= local57 11)
								(= theTheValue_2 theValue)
								(= local62 10)
								(= theTheValue_4 5)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 13 44 0 1 0 -1)
										(localproc_24e3 13 44 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 13 52 0 1 0 -1)
										(localproc_24e3 13 52 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 13 53 0 1 0 -1)
										(localproc_24e3 13 53 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 13 54 0 1 0 -1)
										(localproc_24e3 13 54 16 local56 2 0)
									)
									(5
										(localproc_21ec)
										(localproc_2296 13 0 15 1 0 -1)
										(localproc_2296 13 55 0 1 {@.} 2 4)
										(localproc_2296 13 56 0 1 {2.} 3 4)
										(localproc_2296 13 57 0 1 {3.} 4 4)
										(localproc_2296 13 58 0 1 {4.} 5 4)
										(localproc_2296 6 51 0 1 {5.} 6 4)
										(localproc_2296 6 49 0 1 {6.} 7 4)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 12)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(6
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 12)
						(cond 
							((and (< 0 theValue) (< theValue 7))
								(= local57 12)
								(= theTheValue_2 theValue)
								(= local62 11)
								(= theTheValue_4 5)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 13 55 0 1 0 -1)
										(localproc_24e3 13 55 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 13 56 0 1 0 -1)
										(localproc_24e3 13 56 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 13 57 0 1 0 -1)
										(localproc_24e3 13 57 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 13 58 0 1 0 -1)
										(localproc_24e3 13 58 16 local56 2 0)
									)
									(5
										(= local51 7)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
									(6
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 13)
						(cond 
							((and (< 0 theValue) (< theValue 7))
								(= local57 13)
								(= theTheValue_2 theValue)
								(= local62 7)
								(= theTheValue_4 3)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 14 40 0 1 0 -1)
										(localproc_24e3 14 40 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 14 41 0 1 0 -1)
										(localproc_24e3 14 41 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 14 42 0 1 0 -1)
										(localproc_24e3 14 42 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 14 43 0 1 0 -1)
										(localproc_24e3 14 43 16 local56 2 0)
									)
									(5
										(localproc_21ec)
										(localproc_2296 14 0 15 1 0 -1)
										(localproc_2296 14 44 0 1 {@.} 2 8)
										(localproc_2296 14 52 0 1 {2.} 3 8)
										(localproc_2296 6 51 0 1 {3.} 4 8)
										(localproc_2296 6 49 0 1 {4.} 5 8)
										(localproc_2245 2)
										(= local53 2)
										(= local54 7)
										(= local51 14)
										(= theValue 1)
										(localproc_23c6 7 0 13 1 9 -1)
									)
									(6
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 14)
						(cond 
							((and (< 0 theValue) (< theValue 5))
								(= local57 14)
								(= theTheValue_2 theValue)
								(= local62 13)
								(= theTheValue_4 5)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 14 44 0 1 0 -1)
										(localproc_24e3 14 44 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 14 52 0 1 0 -1)
										(localproc_24e3 14 52 16 local56 2 0)
									)
									(3
										(= local51 7)
										(= theValue 3)
										(enterButton doVerb: V_DO)
									)
									(4
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 15)
						(cond 
							((and (< 0 theValue) (< theValue 6))
								(= local57 15)
								(= theTheValue_2 theValue)
								(= local62 7)
								(= theTheValue_4 4)
								(switch theValue
									(1
										(localproc_21ec)
										(localproc_2296 15 40 0 1 0 -1)
										(localproc_24e3 15 40 16 local56 2 0)
									)
									(2
										(localproc_21ec)
										(localproc_2296 15 41 0 1 0 -1)
										(localproc_24e3 15 41 16 local56 2 0)
									)
									(3
										(localproc_21ec)
										(localproc_2296 15 42 0 1 0 -1)
										(localproc_24e3 15 42 16 local56 2 0)
									)
									(4
										(localproc_21ec)
										(localproc_2296 15 43 0 1 0 -1)
										(localproc_24e3 15 43 16 local56 2 0)
									)
									(5
										(= local51 4)
										(= theValue 2)
										(enterButton doVerb: V_DO)
									)
								)
							)
							((!= theValue -1) (curRoom setScript: sInvalidEntry))
						)
					)
					((== local51 16)
						(= theValue theTheValue_3)
						(switch local59
							(0
								(= local63 0)
								(= local51 local62)
								(= theValue theTheValue_4)
								(= local56 1)
							)
							(1
								(switch theValue
									(1
										(= local63 0)
										(++ local56)
										(= local51 local57)
										(= theValue theTheValue_2)
									)
									(2
										(= local63 0)
										(= local51 local62)
										(= theValue theTheValue_4)
										(= local56 1)
									)
								)
							)
							(2
								(switch theValue
									(1
										(= local63 1)
										(-- local56)
										(= local51 local57)
										(= theValue theTheValue_2)
									)
									(2
										(= local63 0)
										(= local51 local62)
										(= theValue theTheValue_4)
										(= local56 1)
									)
								)
							)
							(3
								(switch theValue
									(1
										(= local63 0)
										(++ local56)
										(= local51 local57)
										(= theValue theTheValue_2)
									)
									(2
										(= local63 1)
										(-- local56)
										(= local51 local57)
										(= theValue theTheValue_2)
									)
									(3
										(= local63 0)
										(= local51 local62)
										(= theValue theTheValue_4)
										(= local56 1)
									)
								)
							)
						)
						(enterButton doVerb: V_DO)
					)
				)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance leftArrow of ArrowCursor
	(properties
		x 140
		y 99
		view 460
		loop 5
		theDirection 4
	)
)

(instance upArrow of ArrowCursor
	(properties
		x 154
		y 88
		view 460
		loop 4
		theDirection 1
	)
)

(instance rightArrow of ArrowCursor
	(properties
		x 168
		y 99
		view 460
		loop 7
		theDirection 2
	)
)

(instance downArrow of ArrowCursor
	(properties
		x 154
		y 111
		view 460
		loop 6
		theDirection 3
	)
)

(instance sInvalidEntry of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(localproc_2296 7 32 14 1 8 -1)
				else
					(localproc_2296 7 31 14 1 8 -1)
				)
				(localproc_23c6 7 0 13 1 9 -1)
				(= ticks 30)
			)
			(1
				(if [newDText 8]
					([newDText 8] dispose:)
					(= [newDText 8] 0)
				)
				(= ticks 30)
			)
			(2
				(if register
					(localproc_2296 7 32 14 1 8 -1)
				else
					(localproc_2296 7 31 14 1 8 -1)
				)
				(localproc_23c6 7 0 13 1 9 -1)
				(= ticks 30)
			)
			(3
				(if [newDText 8]
					([newDText 8] dispose:)
					(= [newDText 8] 0)
				)
				(= ticks 30)
			)
			(4
				(if register
					(localproc_2296 7 32 14 1 8 -1)
				else
					(localproc_2296 7 31 14 1 8 -1)
				)
				(localproc_23c6 7 0 13 1 9 -1)
				(= ticks 30)
			)
			(5
				(if [newDText 8]
					([newDText 8] dispose:)
					(= [newDText 8] 0)
				)
				(= ticks 30)
			)
			(6
				(= local48 1)
				(if (== local51 2)
					(if (== local55 3) (= theValue 1) else (= theValue 2))
				else
					(= theValue (+ (- local55 local53) 1))
				)
				(if local55 (localproc_2245 local55))
				(localproc_23c6 7 0 13 1 9 -1)
				(theGame handsOn:)
				(theIconBar select: (theIconBar at: 2) disable: 3)
				(self dispose:)
			)
		)
	)
)

(instance sfxButton of Sound
	(properties
		flags mNOPAUSE
		number 921
	)
)

(instance sfxEnter of Sound
	(properties
		flags mNOPAUSE
		number 916
	)
)
