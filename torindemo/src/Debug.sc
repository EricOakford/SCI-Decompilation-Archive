;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64014)
(include sci.sh)
(use Main)
(use DebPoly)
(use TPSound)
(use Intrface)
(use List)
(use Event)
(use AnimEditorClass)
(use String)
(use Array)
(use Print)
(use Polygon)
(use WalkTalk)
(use Motion)
(use File)
(use Actor)
(use System)

(public
	Debugger 0
	proc64014_1 1
	proc64014_2 2
)

(local
	local0
	local1
	local2
	theActorSel_141
	local4
)
(procedure (proc64014_1)
	(super init: &rest)
	(gOEventHandler scriptId: self)
)

(procedure (proc64014_2)
	(super init: &rest)
	(gOEventHandler scriptId: self)
)

(procedure (localproc_0092 param1 param2 &tmp newStr temp1)
	(= newStr (Str new:))
	(if (> argc 1) (newStr format: LOOKUP_ERROR param2))
	(= temp1
		(if (GetInput newStr 5 param1)
			(newStr asInteger:)
		else
			-1
		)
	)
	(newStr dispose:)
	(return temp1)
)

(procedure (localproc_0110 param1 &tmp printInit temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 temp21 temp22 temp23 temp24 temp25 temp26)
	(= temp7 10)
	(= temp22 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp8 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp10 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp12 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp14 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp16 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp20 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp18 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp23 (Str newWith: temp7 LOOKUP_ERROR))
	(= temp26 (Str newWith: temp7 LOOKUP_ERROR))
	(temp22
		format: LOOKUP_ERROR param1 ((param1 -super-?) name?) (param1 name?)
	)
	(temp8 format: LOOKUP_ERROR (param1 view?))
	(temp10 format: LOOKUP_ERROR (param1 loop?))
	(temp12 format: LOOKUP_ERROR (param1 cel:))
	(temp14 format: LOOKUP_ERROR (param1 x?))
	(temp16 format: LOOKUP_ERROR (param1 y?))
	(temp18 format: LOOKUP_ERROR (param1 priority?))
	(= temp25 (/ (* 100 (param1 scaleY?)) 128))
	(temp23 format: LOOKUP_ERROR temp25)
	(= temp5
		(+
			(= temp3
				(CelWide (param1 view?) (param1 loop?) (param1 cel:))
			)
			50
		)
	)
	(= temp4 0)
	(= temp6 12)
	(= temp1
		(CelInfo 0 (param1 view?) (param1 loop?) (param1 cel:))
	)
	(= temp2
		(CelInfo 1 (param1 view?) (param1 loop?) (param1 cel:))
	)
	(Print
		y: 20
		fore: 235
		back: 227
		skip: 0
		font: userFont
		addTitle: temp22
		addIcon: (param1 view?) (param1 loop?) (param1 cel:) temp1 temp2
		addText: LOOKUP_ERROR temp3 temp4
		addEdit: temp8 temp7 temp5 temp4
		addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp10 temp7 temp5 temp2
		addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp12 temp7 temp5 temp2
		addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp14 temp7 temp5 temp2
		addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp16 temp7 temp5 temp2
		addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp18 temp7 temp5 temp2
		addText: {Cur Loop: %d} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp23 temp7 temp5 temp2
	)
	(if (param1 isKindOf: Actor)
		(Print
			addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
			addEdit:
				temp20
				temp7
				temp5
				(temp20 format: LOOKUP_ERROR (param1 heading?))
		)
		(if (param1 scaler?)
			(temp26
				format:
					{%hu.fea}
					((param1 scaler?) frontSize?)
					((param1 scaler?) backSize?)
					((param1 scaler?) frontY?)
					((param1 scaler?) backY?)
			)
			(Print addText: temp26 temp3 (= temp4 (+ temp4 temp6)))
		else
			(Print
				addText: LOOKUP_ERROR temp3 (= temp4 (+ temp4 temp6))
			)
		)
	)
	(if (= printInit (Print init:))
		(= temp9 (temp8 asInteger:))
		(= temp11 (temp10 asInteger:))
		(= temp13 (temp12 asInteger:))
		(= temp15 (temp14 asInteger:))
		(= temp17 (temp16 asInteger:))
		(= temp19 (temp18 asInteger:))
		(= temp24 (temp23 asInteger:))
		(if (param1 isKindOf: Actor)
			(localproc_0955 param1 (temp20 asInteger:))
		)
		(param1
			view: temp9
			setLoop: temp11
			setCel: temp13
			posn: temp15 temp17
			setPri: temp19
		)
		(if (!= temp24 temp25) (param1 nCurPosY: temp24))
	)
	(temp22 dispose:)
	(temp8 dispose:)
	(temp10 dispose:)
	(temp12 dispose:)
	(temp14 dispose:)
	(temp16 dispose:)
	(temp20 dispose:)
	(temp18 dispose:)
	(temp23 dispose:)
	(temp26 dispose:)
	(return printInit)
)

(procedure (localproc_074d param1 &tmp temp0)
	(DeleteScreenItem param1)
	(FrameOut)
	(= temp0 250)
	(while temp0
		(cond 
			((== 50 temp0) (DeleteScreenItem param1))
			((== 100 temp0) (AddScreenItem param1))
			((== 150 temp0) (DeleteScreenItem param1))
			((== 200 temp0) (AddScreenItem param1))
		)
		(FrameOut)
		(-- temp0)
	)
	(AddScreenItem param1)
	(FrameOut)
)

(procedure (localproc_07f8 param1 param2 param3 param4)
	(if
		(and
			(or
				(not theActorSel_141)
				(not (theActorSel_141 isKindOf: param2))
			)
			(= theActorSel_141
				(localproc_0853 param1 param2 param3 param4)
			)
		)
		(localproc_074d theActorSel_141)
	)
	(return theActorSel_141)
)

(procedure (localproc_0853 param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3 temp4)
	(= temp2 32764)
	(= temp1 0)
	(= temp4 (param1 first:))
	(while temp4
		(if
			(and
				((= temp0 (KList 8 temp4)) isKindOf: param2)
				(<
					(= temp3
						(GetDistance param3 param4 (temp0 x?) (temp0 y?))
					)
					temp2
				)
			)
			(= temp1 temp0)
			(= temp2 temp3)
		)
		(= temp4 (param1 next: temp4))
	)
	(return temp1)
)

(procedure (localproc_08f5 param1 param2 &tmp temp0)
	(= temp0 (+ param2 (param1 heading?)))
	(while (> temp0 360)
		(= temp0 (- temp0 360))
	)
	(while (< temp0 0)
		(= temp0 (+ temp0 360))
	)
	(param1 setHeading: temp0)
)

(procedure (localproc_0955 param1 param2 &tmp temp0)
	(= temp0 param2)
	(while (> temp0 360)
		(= temp0 (- temp0 360))
	)
	(while (< temp0 0)
		(= temp0 (+ temp0 360))
	)
	(param1 setHeading: temp0)
)

(procedure (localproc_09ac param1 &tmp temp0 theTheThePlane theThePlane)
	(FrameOut)
	(= theThePlane thePlane)
	(= temp0 (planes next: (planes first:)))
	(while temp0
		(if
		((= theTheThePlane (KList 8 temp0)) onMe: param1)
			(= theThePlane theTheThePlane)
			(break)
		)
		(= temp0 (planes next: temp0))
	)
	(param1 localize: theThePlane)
	(param1 claimed: 1)
	(return theThePlane)
)

(instance debugSound of TPSound
	(properties)
)

(instance animEdit of AnimEditorClass
	(properties)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler scriptId: self)
	)
	
	(method (dispose)
		(gOEventHandler bHasFF: self)
		(super dispose:)
	)
)

(class Debugger of Code
	(properties
		scratch 0
	)
	
	(method (init)
		(super init:)
		(gOEventHandler scriptId: self)
		(= local1 0)
	)
	
	(method (dispose)
		(if local1 (DeletePolygon local1 local2) (= local1 0))
		(gOEventHandler bHasFF: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0 temp1 newEvent temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 printInit newActor temp23 printInitCel printInitLoop)
		(if (event claimed?) (return))
		(switch (event type?)
			(evKEYBOARD
				(switch (event message?)
					(KEY_QUESTION
						(event claimed: 1)
						(Prints LOOKUP_ERROR)
					)
					(KEY_DELETE
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Actor
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(if
						(not (KString 7 (printInit name?) LOOKUP_ERROR))
							(printInit dispose:)
							(= theActorSel_141 0)
						)
					)
					(KEY_2
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Actor
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(localproc_08f5 printInit 45)
						(UpdateScreenItem printInit)
					)
					(KEY_8
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Actor
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(localproc_08f5 printInit -45)
						(UpdateScreenItem printInit)
					)
					(KEY_6
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Prop
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(= printInitCel (printInit cel:))
						(if (< (printInit cel:) (printInit lastCel:))
							(printInit cel: (+ (printInit cel:) 1))
						else
							(printInit cel: 0)
						)
						(MonoOut LOOKUP_ERROR (printInit cel:))
						(UpdateScreenItem printInit)
					)
					(KEY_4
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Prop
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(= printInitCel (printInit cel:))
						(if (> (printInit cel:) 0)
							(printInit cel: (- (printInit cel:) 1))
						else
							(printInit cel: (printInit lastCel:))
						)
						(MonoOut LOOKUP_ERROR (printInit cel:))
						(UpdateScreenItem printInit)
					)
					(KEY_3
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Prop
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(= printInitLoop (printInit loop?))
						(if (< (printInit loop?) (printInit lastLoop:))
							(printInit loop: (+ (printInit loop?) 1))
						else
							(printInit loop: 0)
						)
						(MonoOut LOOKUP_ERROR (printInit loop?))
						(UpdateScreenItem printInit)
					)
					(KEY_1
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Prop
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(= printInitLoop (printInit loop?))
						(if (> (printInit loop?) 0)
							(printInit loop: (- (printInit loop?) 1))
						else
							(printInit loop: (printInit lastLoop:))
						)
						(MonoOut LOOKUP_ERROR (printInit loop?))
						(UpdateScreenItem printInit)
					)
					(KEY_NUMPAD4
						(if (!= (event modifiers?) emCTRL) (return))
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(printInit posn: (- (printInit x?) 1) (printInit y?))
						(MonoOut LOOKUP_ERROR (printInit x?) (printInit y?))
						(UpdateScreenItem printInit)
					)
					(KEY_RIGHT
						(if (!= (event modifiers?) emCTRL) (return))
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(printInit posn: (+ (printInit x?) 1) (printInit y?))
						(MonoOut LOOKUP_ERROR (printInit x?) (printInit y?))
						(UpdateScreenItem printInit)
					)
					(KEY_UP
						(if (!= (event modifiers?) emCTRL) (return))
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(printInit posn: (printInit x?) (- (printInit y?) 1))
						(MonoOut LOOKUP_ERROR (printInit x?) (printInit y?))
						(UpdateScreenItem printInit)
					)
					(KEY_NUMPAD2
						(if (!= (event modifiers?) emCTRL) (return))
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(printInit posn: (printInit x?) (+ (printInit y?) 1))
						(MonoOut LOOKUP_ERROR (printInit x?) (printInit y?))
						(UpdateScreenItem printInit)
					)
					(KEY_ALT_a
						(= temp23 (localproc_09ac event))
						(if
							(and
								(= temp7 (localproc_0092 'LOOKUP_ERROR'))
								(ResCheck 128 temp7)
							)
							((= newActor (Actor new:))
								name: LOOKUP_ERROR
								x: (event x?)
								y: (event y?)
								view: temp7
								setCel: 0
								setLoop: 0
								cycleSpeed: (ego cycleSpeed?)
								moveSpeed: (ego moveSpeed?)
								scaleX: 0
								scaleY: 0
								scaleSignal: 0
								setScale: 0
								priority: 110
								fixPriority: 0
								setCycle: Walk
								init: temp23
							)
							(= theActorSel_141 newActor)
							(UpdateScreenItem newActor)
							(FrameOut)
						else
							(Prints LOOKUP_ERROR)
						)
					)
					(KEY_ALT_b
						(= temp23 (localproc_09ac event))
						(while
							(and
								(< local0 999)
								(= temp5 (Str format: LOOKUP_ERROR local0))
								(FileIO 10 (temp5 data?))
							)
							(++ local0)
						)
						(if (< local0 999)
							(SaveScreen (temp5 data?))
							(Printf LOOKUP_ERROR (temp5 data?))
						else
							(Prints LOOKUP_ERROR)
						)
					)
					(KEY_ALT_c
						(= temp23 (localproc_09ac event))
						(if ((temp23 nSpeed:) size:)
							(= temp4 ((temp23 nSpeed:) first:))
							(while temp4
								(= temp3 (KList 8 temp4))
								(if (not (localproc_0110 temp3)) (break))
								(= temp4 ((temp23 nSpeed:) next: temp4))
							)
						else
							(Prints LOOKUP_ERROR)
							(return)
						)
					)
					(KEY_ALT_d
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(localproc_0110 printInit)
					)
					(KEY_ALT_e
						(= temp23 (localproc_09ac event))
						(if ((temp23 nSpeed:) contains: (user alterEgo?))
							(localproc_0110 (user alterEgo?))
						else
							(Prints LOOKUP_ERROR)
							(return)
						)
					)
					(KEY_ALT_f
						(= temp23 (localproc_09ac event))
						(if (not (features size:))
							(PEditor init: 1 temp23)
						else
							(PEditor init: 0 temp23)
						)
						(= temp4 0)
						(while (< temp4 (features size:))
							(if ((= temp3 (features at: temp4)) onMeCheck?)
								(if ((temp3 onMeCheck?) isKindOf: List)
									(= temp10 0)
									(while (< temp10 ((temp3 onMeCheck?) size:))
										(= temp8 ((temp3 onMeCheck?) at: temp10))
										(PEditor speakerCel: temp8)
										(++ temp10)
									)
								else
									(PEditor speakerCel: (temp3 onMeCheck?))
								)
								((PEditor at: (- (PEditor size:) 1))
									name: (temp3 name?)
								)
							)
							(++ temp4)
						)
						(PEditor show: 1 scratch: 1)
						(FrameOut)
						(PEditor doit:)
						(= temp3 (Str new: 33))
						(= temp11 (Str new: 33))
						(temp11 format: LOOKUP_ERROR curRoomNum)
						(Print
							addTitle: LOOKUP_ERROR
							addEdit: temp3 12 0 0 temp11
							init:
						)
						(FrameOut)
						(LOOKUP_ERROR name: (temp3 data?) open: 0)
						(LOOKUP_ERROR writeString: LOOKUP_ERROR)
						(= temp6 0)
						(if (PEditor size:)
							(= temp6 (Str newWith: 5 LOOKUP_ERROR))
							(= temp8 (KList 3 (PEditor elements?)))
							(while temp8
								(= temp9 (KList 6 temp8))
								(= temp11 (KList 8 temp8))
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: (temp11 name?))
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(= temp12 (= temp14 32767))
								(= temp13 (= temp15 0))
								(= temp4 0)
								(while (< temp4 (temp11 size:))
									(temp11 bWander: temp4)
									(if (< (temp11 rightSide?) temp12)
										(= temp12 (temp11 rightSide?))
									)
									(if (> (temp11 rightSide?) temp13)
										(= temp13 (temp11 rightSide?))
									)
									(if (< (temp11 destX?) temp14)
										(= temp14 (temp11 destX?))
									)
									(if (> (temp11 destX?) temp15)
										(= temp15 (temp11 destX?))
									)
									(++ temp4)
								)
								(= temp0 (+ (/ (- temp13 temp12) 2) temp12))
								(= temp1 (+ (/ (- temp15 temp14) 2) temp14))
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(temp6 format: LOOKUP_ERROR temp0)
								(LOOKUP_ERROR writeString: (temp6 data?))
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(temp6 format: LOOKUP_ERROR temp1)
								(LOOKUP_ERROR writeString: (temp6 data?))
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(temp11 saveMessages: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(LOOKUP_ERROR writeString: (temp11 name?))
								(LOOKUP_ERROR writeString: LOOKUP_ERROR)
								(= temp8 temp9)
							)
						)
						(LOOKUP_ERROR close:)
						(if temp6 (temp6 dispose:))
						(temp3 dispose:)
						(PEditor scratch: 0 dispose:)
					)
					(KEY_ALT_g
						(= temp23 (localproc_09ac event))
						(= temp6 (Str newWith: 75 LOOKUP_ERROR))
						(Print
							font: userFont
							y: 100
							addTextF: LOOKUP_ERROR 145
							addEdit: temp6 5 50
							init:
						)
						(if (>= (= temp4 (temp6 asInteger:)) 145)
							(Prints LOOKUP_ERROR)
							(return)
						)
						(temp6 dispose:)
						(switch
							(Print
								font: userFont
								y: 50
								addTextF:
									(if ((ScriptID 64017 0) test: temp4)
										'LOOKUP_ERROR'
									else
										'LOOKUP_ERROR'
									)
									temp4
								addButton: 1 LOOKUP_ERROR 0 12
								addButton: 2 LOOKUP_ERROR 0 26
								addButton: -1 LOOKUP_ERROR 0 40
								init:
							)
							(1
								((ScriptID 64017 0) set: temp4)
							)
							(2
								((ScriptID 64017 0) clear: temp4)
							)
						)
					)
					(JOY_LEFT
						(= temp23 (localproc_09ac event))
						(if (and curRoom (curRoom respondsTo: #intoPouch))
							(curRoom intoPouch:)
						)
					)
					(KEY_ALT_h
						(= temp23 (localproc_09ac event))
						(PEditor init: 1 temp23)
						(if (curRoom obstacles?)
							(PEditor oWho: (curRoom obstacles?))
						)
						(= temp4 0)
						(while (< temp4 (features size:))
							(if ((= temp3 (features at: temp4)) onMeCheck?)
								(if ((temp3 onMeCheck?) isKindOf: List)
									(= temp10 0)
									(while (< temp10 ((temp3 onMeCheck?) size:))
										(= temp8 ((temp3 onMeCheck?) at: temp10))
										(PEditor speakerCel: temp8)
										(++ temp10)
									)
								else
									(PEditor speakerCel: (temp3 onMeCheck?))
								)
								((PEditor at: (- (PEditor size:) 1))
									name: (temp3 name?)
								)
							)
							(++ temp4)
						)
						(PEditor show: dispose:)
					)
					(KEY_ALT_i
						(= temp23 (localproc_09ac event))
						(theGame setCursor: (ScriptID 64006 0) 1)
						(repeat
							(= temp17 (= temp18 (= temp20 0)))
							(Print font: smallFont)
							(= temp19 0)
							(while
							(< temp19 (((ScriptID 64001 0) nHBorder?) size:))
								(if
									(not
										(= temp16 (((ScriptID 64001 0) nHBorder?) at: temp19))
									)
								else
									(breakif (>= temp19 150))
									(Print addButton: temp19 (temp16 name?) temp18 temp17)
									((Print dialog?) setSize:)
									(if (> temp17 250)
										(= temp20 temp17)
										(= temp17 0)
										(= temp18
											(-
												((Print dialog?) nsRight?)
												((Print dialog?) nsLeft?)
											)
										)
									else
										(= temp17 (+ temp17 14))
									)
								)
								(++ temp19)
							)
							(Print
								addButton: temp19 LOOKUP_ERROR temp18 (if temp20 else temp17)
							)
							(breakif (== (= printInit (Print init:)) temp19))
							(ego
								get: (((ScriptID 64001 0) nHBorder?) at: printInit)
							)
						)
						(Print font: userFont)
					)
					(KEY_ALT_j
						(= temp23 (localproc_09ac event))
						(if
							(and
								(!=
									(= temp7
										(localproc_0092 'LOOKUP_ERROR' (curRoom picture?))
									)
									-1
								)
								(ResCheck 129 temp7)
							)
							(curRoom picture: temp7)
							(curRoom drawPic: temp7)
							(FrameOut)
						else
							(Printf LOOKUP_ERROR temp7)
						)
					)
					(KEY_ALT_k
						(= temp23 (localproc_09ac event))
						(if (not theActorSel_141) (return))
						(localproc_074d theActorSel_141)
					)
					(KEY_ALT_l
						((ScriptID 64952 0) doit:)
					)
					(KEY_ALT_p
						(= temp23 (localproc_09ac event))
						(if
							(or
								(not (curRoom obstacles?))
								(not ((curRoom obstacles?) size:))
							)
							(PEditor init: 1 temp23)
						else
							(PEditor init: 0 temp23)
						)
						(if (curRoom obstacles?)
							(PEditor oWho: (curRoom obstacles?))
						)
						(PEditor doit:)
						(= temp3 (Str new: 13))
						(= temp11 (Str new: 13))
						(temp11 format: LOOKUP_ERROR curRoomNum)
						(Print
							addTitle: LOOKUP_ERROR
							addEdit: temp3 12 0 0 temp11
							init:
						)
						(FrameOut)
						(LOOKUP_ERROR name: (temp3 data?) open: 0)
						(LOOKUP_ERROR writeString: LOOKUP_ERROR)
						(LOOKUP_ERROR writeString: LOOKUP_ERROR)
						(if (PEditor size:)
							(PEditor eachElementDo: #saveMessages LOOKUP_ERROR)
							(if (curRoom obstacles?)
								((curRoom obstacles?) dispose:)
								(curRoom obstacles: 0)
							)
							(= temp8 (KList 3 (PEditor elements?)))
							(while temp8
								(= temp9 (KList 6 temp8))
								(= temp11 (KList 8 temp8))
								(curRoom
									addObstacle:
										((Polygon new:)
											type: (temp11 type?)
											size: (temp11 size:)
											dynamic: 1
											points: ((IntArray new:) copy: (temp11 points?))
											yourself:
										)
								)
								(= temp8 temp9)
							)
						)
						(LOOKUP_ERROR writeString: 'LOOKUP_ERROR')
						(LOOKUP_ERROR close:)
						(temp3 dispose:)
						(PEditor dispose:)
					)
					(KEY_ALT_r
						(= temp23 (localproc_09ac event))
						(Printf
							LOOKUP_ERROR
							(curRoom name?)
							curRoomNum
							(curRoom picture?)
							(curRoom style?)
							(curRoom horizon?)
							(curRoom north?)
							(curRoom south?)
							(curRoom east?)
							(curRoom west?)
							(if (curRoom script?)
								((curRoom script?) name?)
							else
								'LOOKUP_ERROR'
							)
							78
							120
						)
					)
					(KEY_ALT_s
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_07f8
										(temp23 nSpeed:)
										Actor
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						((ScriptID 64012 0)
							init: printInit temp23
							doit:
							dispose:
						)
					)
					(KEY_MENU
						(= temp23 (localproc_09ac event))
						(if
							(and
								(> (= temp7 (localproc_0092 'LOOKUP_ERROR' local4)) 0)
								(ResCheck 150 temp7)
							)
							(= local4 temp7)
							(event localize: (curRoom plane?))
							(= autoRobot
								((WalkieTalkie new:)
									x: (event x?)
									y: (event y?)
									init: 0 temp7 0
									start:
									yourself:
								)
							)
						else
							(Printf LOOKUP_ERROR temp7)
						)
					)
					(KEY_PAUSE
						(= temp23 (localproc_09ac event))
						(= temp4 (GetNumber LOOKUP_ERROR))
						(= temp3 (localproc_0092 'LOOKUP_ERROR'))
						(LOOKUP_ERROR setLoop: temp4 number: temp3 play:)
					)
					(KEY_ALT_t
						(= global242 1)
						(curRoom newRoom: -4436)
					)
					(KEY_ALT_u
						(= temp23 (localproc_09ac event))
						(theGame handsOn:)
					)
					(KEY_ALT_x
						(= temp23 (localproc_09ac event))
						(= quit 1)
					)
					(KEY_ALT_y
						(= temp23 (localproc_09ac event))
						(cond 
							(local1 0)
							((curRoom obstacles?)
								(= local2 temp23)
								(= local1 (AddPolygon local2 (curRoom obstacles?)))
							)
							(else (Prints LOOKUP_ERROR))
						)
					)
					(KEY_ALT_z
						(Prints LOOKUP_ERROR)
						(LOOKUP_ERROR init:)
					)
					(else  (event claimed: 0))
				)
			)
			(evMOUSEBUTTON
				(switch (event modifiers?)
					(emCTRL
						(= temp23 (localproc_09ac event))
						(MonoOut LOOKUP_ERROR (event x?) (event y?))
						(event globalize:)
						(MonoOut LOOKUP_ERROR (event x?) (event y?))
					)
					(emALT
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_0853
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(localproc_074d printInit)
						(= theActorSel_141 printInit)
						(ego setMotion: 0)
						(while (!= ((= newEvent (Event new:)) type?) 2)
							(newEvent localize: temp23)
							(printInit posn: (newEvent x?) (newEvent y?))
							(if
							(and (RespondsTo printInit 340) (printInit scaler?))
								((printInit scaler?) doit:)
							)
							(UpdateScreenItem printInit)
							(FrameOut)
							(newEvent dispose:)
						)
						(MonoOut
							LOOKUP_ERROR
							(printInit name?)
							printInit
							(printInit x?)
							(printInit y?)
						)
						(newEvent dispose:)
					)
					(7
						(= temp23 (localproc_09ac event))
						(if
							(not
								(= printInit
									(localproc_0853
										(temp23 nSpeed:)
										View
										(event x?)
										(event y?)
									)
								)
							)
							(return)
						)
						(localproc_074d printInit)
						(= theActorSel_141 printInit)
					)
				)
			)
		)
	)
)

(instance poly_pol of File
	(properties
		name "poly.pol"
	)
)
