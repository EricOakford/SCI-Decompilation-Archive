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
	(if (> argc 1) (newStr format: {%hu} param2))
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
	(= temp22 (Str newWith: temp7 {}))
	(= temp8 (Str newWith: temp7 {}))
	(= temp10 (Str newWith: temp7 {}))
	(= temp12 (Str newWith: temp7 {}))
	(= temp14 (Str newWith: temp7 {}))
	(= temp16 (Str newWith: temp7 {}))
	(= temp20 (Str newWith: temp7 {}))
	(= temp18 (Str newWith: temp7 {}))
	(= temp23 (Str newWith: temp7 {}))
	(= temp26 (Str newWith: temp7 {}))
	(temp22
		format:
			{instance (%hu) %s of %s}
			param1
			((param1 -super-?) name?)
			(param1 name?)
	)
	(temp8 format: {%hu} (param1 view?))
	(temp10 format: {%d} (param1 loop?))
	(temp12 format: {%d} (param1 cel:))
	(temp14 format: {%d} (param1 x?))
	(temp16 format: {%d} (param1 y?))
	(temp18 format: {%d} (param1 priority?))
	(= temp25 (/ (* 100 (param1 scaleY?)) 128))
	(temp23 format: {%d} temp25)
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
		addText: {view} temp3 temp4
		addEdit: temp8 temp7 temp5 temp4
		addText: {loop} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp10 temp7 temp5 temp2
		addText: {cel} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp12 temp7 temp5 temp2
		addText: {x} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp14 temp7 temp5 temp2
		addText: {y} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp16 temp7 temp5 temp2
		addText: {priority} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp18 temp7 temp5 temp2
		addText: {Scale %} temp3 (= temp4 (+ temp4 temp6))
		addEdit: temp23 temp7 temp5 temp2
	)
	(if (param1 isKindOf: Actor)
		(Print
			addText: {heading} temp3 (= temp4 (+ temp4 temp6))
			addEdit: temp20 temp7 temp5 (temp20 format: {%d} (param1 heading?))
		)
		(if (param1 scaler?)
			(temp26
				format:
					{Scaler %d__%d__%d__%d}
					((param1 scaler?) frontSize?)
					((param1 scaler?) backSize?)
					((param1 scaler?) frontY?)
					((param1 scaler?) backY?)
			)
			(Print addText: temp26 temp3 (= temp4 (+ temp4 temp6)))
		else
			(Print
				addText: {No scaler} temp3 (= temp4 (+ temp4 temp6))
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
						(Prints
							{ ALT-Drag - drag&scale cast member around screen\nCTRL-LShft-Click - make nearest cast member current\nCTRL-Click - mouse coordinates\nCTRL-Arrow - move current view 1 pixel\n ALT-A_____- add animation/view\n ALT-B_____- caputure BMP\n ALT-C_____- show Cast info\n ALT-D_____- show current view info\n ALT-E_____- show Ego\n ALT-F_____- edit Features\n ALT-G_____- show/set/clear Flag\n ALT-H_____- show features and obstacles\n ALT-I_____- get Inventory Item\n ALT-J_____- load a pic\n ALT-K_____- blinK current view\n ALT-L_____- error logging\n ALT-P_____- edit Polygons\n ALT-R_____- show Room info\n ALT-S_____- scaler tool on current actor\n ALT-T_____- teleport\n ALT-U_____- handsOn\n ALT-X_____- exit the Game\n ALT-Y_____- show room polygons\n ALT-Z_____- Animation Editor\nDELETE_____- delete current object\n8__________- Rotate current (actor) right\n2__________- Rotate current (actor) left\n4__________- Decrease current (prop) cel\n6__________- Increase current (prop) cel\n1__________- Decrease current (prop) loop\n3__________- Increase current (prop) loop\nCTRL-G_____- Give ego necessary items for room\nCTRL-R_____- Play a robot\nCTRL-S_____- test a sound\n}
						)
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
						(if (not (KString 7 (printInit name?) {temp}))
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
						(MonoOut {Cur Cel: %d} (printInit cel:))
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
						(MonoOut {Cur Cel: %d} (printInit cel:))
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
						(MonoOut {Cur Loop: %d} (printInit loop?))
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
						(MonoOut {Cur Loop: %d} (printInit loop?))
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
						(MonoOut {CurPos: %d, %d} (printInit x?) (printInit y?))
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
						(MonoOut {CurPos: %d, %d} (printInit x?) (printInit y?))
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
						(MonoOut {CurPos: %d, %d} (printInit x?) (printInit y?))
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
						(MonoOut {CurPos: %d, %d} (printInit x?) (printInit y?))
						(UpdateScreenItem printInit)
					)
					(KEY_ALT_a
						(= temp23 (localproc_09ac event))
						(if
							(and
								(= temp7 (localproc_0092 {View #?}))
								(ResCheck 128 temp7)
							)
							((= newActor (Actor new:))
								name: {temp}
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
							(Prints {That is not a valid view.})
						)
					)
					(KEY_ALT_b
						(= temp23 (localproc_09ac event))
						(while
							(and
								(< local0 999)
								(= temp5 (Str format: {TP%03d.BMP} local0))
								(FileIO 10 (temp5 data?))
							)
							(++ local0)
						)
						(if (< local0 999)
							(SaveScreen (temp5 data?))
							(Printf {Screen saved as\n___%s} (temp5 data?))
						else
							(Prints
								{Sorry, no can do. How did you get so many files?}
							)
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
							(Prints {No One Home!})
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
							(Prints {no ego!})
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
						(temp11 format: {%hu.fea} curRoomNum)
						(Print
							addTitle: {Output File Name:}
							addEdit: temp3 12 0 0 temp11
							init:
						)
						(FrameOut)
						(poly_pol name: (temp3 data?) open: 0)
						(poly_pol
							writeString: {;***************************\0D\n}
						)
						(= temp6 0)
						(if (PEditor size:)
							(= temp6 (Str newWith: 5 {}))
							(= temp8 (KList 3 (PEditor elements?)))
							(while temp8
								(= temp9 (KList 6 temp8))
								(= temp11 (KList 8 temp8))
								(poly_pol writeString: {\0D\n})
								(poly_pol writeString: {(instance_})
								(poly_pol writeString: (temp11 name?))
								(poly_pol writeString: { of Feature\0D\n})
								(poly_pol writeString: {\t(properties\0D\n})
								(poly_pol writeString: {\t\tx})
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
								(poly_pol writeString: {\t\t\t\t})
								(temp6 format: {%d} temp0)
								(poly_pol writeString: (temp6 data?))
								(poly_pol writeString: {\0D\n})
								(poly_pol writeString: {\t\ty})
								(poly_pol writeString: {\t\t\t\t})
								(temp6 format: {%d} temp1)
								(poly_pol writeString: (temp6 data?))
								(poly_pol writeString: {\t)\0D\n})
								(poly_pol writeString: {\t(method (init)\0D\n})
								(poly_pol writeString: {\t\t(self setPolygon:\0D\n})
								(temp11 saveMessages: poly_pol)
								(poly_pol writeString: {\t\t)\0D\n})
								(poly_pol writeString: {\t\t(super init: &rest)\0D\n})
								(poly_pol writeString: {\t)\0D\n})
								(poly_pol writeString: {);})
								(poly_pol writeString: (temp11 name?))
								(poly_pol writeString: {\0D\n})
								(= temp8 temp9)
							)
						)
						(poly_pol close:)
						(if temp6 (temp6 dispose:))
						(temp3 dispose:)
						(PEditor scratch: 0 dispose:)
					)
					(KEY_ALT_g
						(= temp23 (localproc_09ac event))
						(= temp6 (Str newWith: 75 {}))
						(Print
							font: userFont
							y: 100
							addTextF: {Flag num (max %d)} 145
							addEdit: temp6 5 50
							init:
						)
						(if (>= (= temp4 (temp6 asInteger:)) 145)
							(Prints {Flag too large, ignoring})
							(return)
						)
						(temp6 dispose:)
						(switch
							(Print
								font: userFont
								y: 50
								addTextF:
									(if ((ScriptID 64017 0) test: temp4)
										{flag %d is SET}
									else
										{flag %d is CLEARED}
									)
									temp4
								addButton: 1 { set_} 0 12
								addButton: 2 {clear} 0 26
								addButton: -1 {cancel} 0 40
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
								addButton: temp19 {Exit} temp18 (if temp20 else temp17)
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
									(= temp7 (localproc_0092 {Pic #?} (curRoom picture?)))
									-1
								)
								(ResCheck 129 temp7)
							)
							(curRoom picture: temp7)
							(curRoom drawPic: temp7)
							(FrameOut)
						else
							(Printf {Picture number %hu not available} temp7)
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
						(temp11 format: {%hu.pol} curRoomNum)
						(Print
							addTitle: {Output File Name:}
							addEdit: temp3 12 0 0 temp11
							init:
						)
						(FrameOut)
						(poly_pol name: (temp3 data?) open: 0)
						(poly_pol writeString: {\t\t;********************\0D\n})
						(poly_pol writeString: {\t\t(curRoom addObstacle:\0D\n})
						(if (PEditor size:)
							(PEditor eachElementDo: #saveMessages poly_pol)
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
						(poly_pol writeString: {\t\t)\0D\n})
						(poly_pol close:)
						(temp3 dispose:)
						(PEditor dispose:)
					)
					(KEY_ALT_r
						(= temp23 (localproc_09ac event))
						(Printf
							{name: %s\nnumber: %hu\npicture: %hu\nstyle: %d\nhorizon: %d\nnorth: %d\nsouth: %d\neast: %d\nwest: %d\nscript: %s_}
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
								{..none..}
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
								(> (= temp7 (localproc_0092 {Robot #?} local4)) 0)
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
							(Printf {Robot number %hu not available} temp7)
						)
					)
					(KEY_PAUSE
						(= temp23 (localproc_09ac event))
						(= temp4 (GetNumber {setLoop?}))
						(= temp3 (localproc_0092 {which sound number?}))
						(debugSound setLoop: temp4 number: temp3 play:)
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
							(else (Prints {Sorry, this room has no polygons.}))
						)
					)
					(KEY_ALT_z
						(Prints {Animation Editor Mode.})
						(animEdit init:)
					)
					(else  (event claimed: 0))
				)
			)
			(evMOUSEBUTTON
				(switch (event modifiers?)
					(emCTRL
						(= temp23 (localproc_09ac event))
						(MonoOut { local: %d/%d} (event x?) (event y?))
						(event globalize:)
						(MonoOut {global: %d/%d} (event x?) (event y?))
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
							{; %s (#%d)\nposn: %d %d}
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
