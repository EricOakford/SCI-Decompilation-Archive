;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include game.sh)
(use Main)
(use Intrface)
(use IconBar)
(use Osc)
(use PrintD)
(use RandCyc)
(use MoveCyc)
(use Polygon)
(use Approach)
(use User)
(use System)


;NOTE: This entire module has been commented out, as it is
; a remnant from the original floppy disk version of SQ4.

;;;(local
;;;	local0
;;;	[fileName 30]
;;;	rTop
;;;	rLft
;;;	rBot
;;;	rRgt
;;;	[editMenuItems 25] = [{EDITING} 0 0 {About} 0 0 {Map} 0 0 {Create} 0 0 {Type} 0 0 {Undo} 0 0 {?} 0 0 {eXit} 120]
;;;	[addMenuItems 22] = [{CREATING} 0 0 {About} 0 0 {Map} 0 0 {Done} 0 0 {Undo} 0 0 {?} 0 0 {eXit} 120]
;;;	[dragMenuItems 4] = [{DRAGGING}]
;;;)
;;;(procedure (localproc_0004)
;;;	(Print
;;;		&rest
;;;		#title
;;;		{Polygon Editor 1.0}
;;;		#font
;;;		999
;;;		#mode
;;;		1
;;;		#width
;;;		240
;;;	)
;;;)
;;;
;;;(procedure (localproc_0020 theTheRLft &tmp temp0 theRLft theRTop)
;;;	(= rRgt (= rBot 0))
;;;	(= rLft (= rTop 32767))
;;;	(= temp0 0)
;;;	(while (< temp0 argc)
;;;		(= theRLft [theTheRLft temp0])
;;;		(if (< (= theRTop [theTheRLft (+ temp0 1)]) rTop)
;;;			(= rTop theRTop)
;;;		)
;;;		(if (> theRTop rBot) (= rBot theRTop))
;;;		(if (< theRLft rLft) (= rLft theRLft))
;;;		(if (> theRLft rRgt) (= rRgt theRLft))
;;;		(= temp0 (+ temp0 2))
;;;	)
;;;	(= rLft (- rLft 2))
;;;	(= rTop (- rTop 2))
;;;	(= rRgt (+ rRgt 2))
;;;	(= rBot (+ rBot 2))
;;;)
;;;
;;;(procedure (localproc_00a0 param1 param2 param3 param4)
;;;	(return
;;;		(+
;;;			(* (+ (/ param1 2) 1) (+ (/ param3 2) 1))
;;;			(* (+ (/ param2 2) 1) (+ (/ param4 2) 1))
;;;		)
;;;	)
;;;)
;;;
;;;(procedure (localproc_00cb param1 param2 param3 param4 param5 param6 &tmp temp0)
;;;	(return
;;;		(if
;;;			(and
;;;				(<=
;;;					0
;;;					(localproc_00a0
;;;						(- param3 param1)
;;;						(- param4 param2)
;;;						(- param5 param1)
;;;						(- param6 param2)
;;;					)
;;;				)
;;;				(<=
;;;					0
;;;					(localproc_00a0
;;;						(- param1 param3)
;;;						(- param2 param4)
;;;						(- param5 param3)
;;;						(- param6 param4)
;;;					)
;;;				)
;;;			)
;;;			(return
;;;				(if
;;;				(= temp0 (GetDistance param1 param2 param3 param4))
;;;					(/
;;;						(Abs
;;;							(localproc_00a0
;;;								(- param4 param2)
;;;								(- param1 param3)
;;;								(- param5 param1)
;;;								(- param6 param2)
;;;							)
;;;						)
;;;						temp0
;;;					)
;;;				else
;;;					0
;;;				)
;;;			)
;;;		else
;;;			(return
;;;				(Min
;;;					(GetDistance param5 param6 param1 param2)
;;;					(GetDistance param5 param6 param3 param4)
;;;				)
;;;			)
;;;		)
;;;	)
;;;)
;;;
;;;(class ClickMenu of Obj
;;;	(properties
;;;		client 0
;;;		caller 0
;;;	)
;;;	
;;;	(method (flags theCaller &tmp temp0 temp1 temp2 temp3 [temp4 4] [temp8 40] temp48 temp49)
;;;		(= client (Memory 2 81))
;;;		(Memory 6 client 0)
;;;		(= temp1 (= caller theCaller))
;;;		(= temp48 0)
;;;		(= temp0 0)
;;;		(while (= temp2 (Memory 5 temp1))
;;;			(StrCpy @temp8 temp2)
;;;			(if (not temp0) (StrCat @temp8 {:_}))
;;;			(StrCat @temp8 {_})
;;;			(StrCat client @temp8)
;;;			(TextSize @temp4 @temp8 0 0)
;;;			(= temp48 (+ temp48 [temp4 3]))
;;;			(Memory 6 (+ temp1 4) temp48)
;;;			(if (not (Memory 5 (+ temp1 2)))
;;;				(if
;;;					(and
;;;						(<= 65 (= temp49 (StrAt temp2 0)))
;;;						(<= temp49 90)
;;;					)
;;;					(= temp49 (+ temp49 32))
;;;				)
;;;				(Memory 6 (+ temp1 2) temp49)
;;;			)
;;;			(++ temp0)
;;;			(= temp1 (+ temp1 6))
;;;		)
;;;		(DrawStatus client)
;;;	)
;;;	
;;;	(method (quitGame)
;;;		(Memory 3 client)
;;;		(super quitGame:)
;;;	)
;;;	
;;;	(method (prev param1 &tmp theCaller temp1)
;;;		(if (!= (param1 type?) 1) (return 0))
;;;		(if (>= (param1 y?) 10) (return 0))
;;;		(= theCaller caller)
;;;		(= temp1 0)
;;;		(while (Memory 5 theCaller)
;;;			(if
;;;			(and (< (param1 x?) (Memory 5 (+ theCaller 4))) temp1)
;;;				(param1 type: 4 message: (Memory 5 (+ theCaller 2)))
;;;				(return 0)
;;;			)
;;;			(++ temp1)
;;;			(= theCaller (+ theCaller 6))
;;;		)
;;;		(return (param1 claimed: 1))
;;;	)
;;;)
;;;
;;;(instance editMenu of MCyc
;;;	(properties)
;;;	
;;;	(method (flags)
;;;		(super flags: @editMenuItems)
;;;	)
;;;)
;;;
;;;(instance addMenu of MCyc
;;;	(properties)
;;;	
;;;	(method (flags)
;;;		(super flags: @addMenuItems)
;;;	)
;;;)
;;;
;;;(instance dragMenu of MCyc
;;;	(properties)
;;;	
;;;	(method (flags)
;;;		(super flags: @dragMenuItems)
;;;	)
;;;)
;;;
;;;(class Class_500_4
;;;	(properties
;;;		client 0
;;;		caller 0
;;;	)
;;;	
;;;	(method (topString)
;;;		(Clone self)
;;;	)
;;;	
;;;	(method (showStr)
;;;		(return self)
;;;	)
;;;	
;;;	(method (quitGame)
;;;		(if caller (UnLoad 133 caller) (= caller 0))
;;;		(DisposeClone self)
;;;	)
;;;	
;;;	(method (draw param1)
;;;		(Graph
;;;			grDRAW_LINE
;;;			client
;;;			name
;;;			(param1 y?)
;;;			(param1 x?)
;;;			0
;;;			-1
;;;			4
;;;		)
;;;	)
;;;	
;;;	(method (save param1)
;;;		(localproc_0020 name client (param1 x?) (param1 y?))
;;;		(if caller (UnLoad 133 caller))
;;;		(= caller (Graph grSAVE_BOX rTop rLft rBot rRgt 5))
;;;	)
;;;	
;;;	(method (restore)
;;;		(if caller (Graph grRESTORE_BOX caller) (= caller 0))
;;;	)
;;;)
;;;
;;;(class _EditablePolygon of List
;;;	(properties
;;;		client 0
;;;		caller 0
;;;		cycleDir 0
;;;		cycleCnt 0
;;;		completed 0
;;;		howManyCycles 2
;;;	)
;;;	
;;;	(method (showSelf param1 param2 param3)
;;;		(super
;;;			showSelf:
;;;				(= cycleCnt
;;;					((RandCycle topString:) x: param1 y: param2 showStr:)
;;;				)
;;;		)
;;;		(self east: (FindKey client cycleCnt) param3)
;;;	)
;;;	
;;;	(method (next param1 &tmp temp0)
;;;		(= temp0 (super next: param1))
;;;		(if (and completed (not temp0))
;;;			(return (super eachElementDo:))
;;;		)
;;;		(return temp0)
;;;	)
;;;	
;;;	(method (contains param1 &tmp temp0)
;;;		(= temp0 (super contains: param1))
;;;		(if (and completed (not temp0))
;;;			(return (super allTrue:))
;;;		)
;;;		(return temp0)
;;;	)
;;;	
;;;	(method (draw)
;;;		(self roomToEdge: 83)
;;;	)
;;;	
;;;	(method (clean &tmp temp0)
;;;		(self east: (self next: cycleDir))
;;;	)
;;;	
;;;	(method (playBed &tmp temp0)
;;;		(self east: (self contains: cycleDir))
;;;	)
;;;	
;;;	(method (east theCycleDir param2)
;;;		(if (= cycleDir theCycleDir)
;;;			(= cycleCnt (NodeValue (= cycleDir theCycleDir)))
;;;			(if (or (< argc 2) param2)
;;;				(SetCursor normalCursor 1 (cycleCnt x?) (cycleCnt y?))
;;;			)
;;;		)
;;;	)
;;;	
;;;	(method (south param1)
;;;		(self
;;;			east: (FindKey client --UNKNOWN-PROP-NAME--) param1
;;;		)
;;;	)
;;;	
;;;	(method (west param1 param2 &tmp temp0)
;;;		(= temp0
;;;			((RandCycle topString:) x: param1 y: param2 showStr:)
;;;		)
;;;		(self release: --UNKNOWN-PROP-NAME-- temp0)
;;;		(self east: (FindKey client temp0))
;;;	)
;;;	
;;;	(method (picAngle &tmp temp0)
;;;		(if
;;;		(== cycleDir (= temp0 (self contains: cycleDir)))
;;;			(= temp0 0)
;;;		)
;;;		(self delete: cycleCnt)
;;;		(cycleCnt quitGame:)
;;;		(self east: temp0)
;;;	)
;;;	
;;;	(method (setRegions region param2)
;;;		(cycleCnt x: region y: param2)
;;;	)
;;;	
;;;	(method (setFeatures feature &tmp temp0 temp1 temp2 temp3)
;;;		(self isKindOf: 103 firstTrue:)
;;;		(= completed (Memory 5 feature))
;;;		(= feature (+ feature 2))
;;;		(= temp1 (Memory 5 feature))
;;;		(= feature (+ feature 2))
;;;		(= temp3 (Memory 5 feature))
;;;		(= feature (+ feature 2))
;;;		(= temp2 0)
;;;		(while (< temp2 temp1)
;;;			(self
;;;				showSelf: (Memory 5 feature) (Memory 5 (= feature (+ feature 2))) 0
;;;			)
;;;			(++ temp2)
;;;			(= feature (+ feature 2))
;;;		)
;;;		(self east: (FindKey client (self at: temp3)) 0)
;;;	)
;;;	
;;;	(method (setLocales &tmp temp0 temp1 _EditablePolygonEachElementDo temp3)
;;;		(= temp1
;;;			(= temp0 (Memory 2 (* 2 (+ (* 2 caller) 3))))
;;;		)
;;;		(Memory 6 temp1 completed)
;;;		(= temp1 (+ temp1 2))
;;;		(Memory 6 temp1 caller)
;;;		(= temp1 (+ temp1 2))
;;;		(Memory 6 temp1 (self last: cycleCnt))
;;;		(= temp1 (+ temp1 2))
;;;		(= _EditablePolygonEachElementDo (self eachElementDo:))
;;;		(while _EditablePolygonEachElementDo
;;;			(= temp3 (NodeValue _EditablePolygonEachElementDo))
;;;			(Memory 6 temp1 (temp3 x?))
;;;			(Memory 6 (= temp1 (+ temp1 2)) (temp3 y?))
;;;			(= _EditablePolygonEachElementDo
;;;				(NextNode _EditablePolygonEachElementDo)
;;;			)
;;;			(= temp1 (+ temp1 2))
;;;		)
;;;		(return temp0)
;;;	)
;;;	
;;;	(method (drawPic pic theStyle &tmp _EditablePolygonAllTrue _EditablePolygonEachElementDo the__UNKNOWN_PROP_NAME__ temp3 temp4 temp5 the_EditablePolygonEachElementDo)
;;;		(if (< caller 2)
;;;			(return (self overlay: pic theStyle &rest))
;;;		)
;;;		(= temp4 32767)
;;;		(= _EditablePolygonEachElementDo (self eachElementDo:))
;;;		(= _EditablePolygonAllTrue (self allTrue:))
;;;		(repeat
;;;			(= the__UNKNOWN_PROP_NAME__
;;;				(NodeValue _EditablePolygonEachElementDo)
;;;			)
;;;			(= the_EditablePolygonEachElementDo
;;;				(self next: _EditablePolygonEachElementDo)
;;;			)
;;;			(= temp5 (NodeValue the_EditablePolygonEachElementDo))
;;;			(if
;;;				(<
;;;					(= temp3
;;;						(localproc_00cb
;;;							(the__UNKNOWN_PROP_NAME__ x?)
;;;							(the__UNKNOWN_PROP_NAME__ y?)
;;;							(temp5 x?)
;;;							(temp5 y?)
;;;							pic
;;;							theStyle
;;;						)
;;;					)
;;;					temp4
;;;				)
;;;				(= temp4 temp3)
;;;				(= --UNKNOWN-PROP-NAME-- the__UNKNOWN_PROP_NAME__)
;;;			)
;;;			(breakif
;;;				(== _EditablePolygonEachElementDo _EditablePolygonAllTrue)
;;;			)
;;;			(= _EditablePolygonEachElementDo
;;;				the_EditablePolygonEachElementDo
;;;			)
;;;		)
;;;		(return temp4)
;;;	)
;;;	
;;;	(method (overlay pic theStyle &tmp _EditablePolygonAllTrue _EditablePolygonEachElementDo the__UNKNOWN_PROP_NAME__ temp3 temp4)
;;;		(= temp4 32767)
;;;		(= _EditablePolygonEachElementDo (self eachElementDo:))
;;;		(= _EditablePolygonAllTrue (self allTrue:))
;;;		(repeat
;;;			(= the__UNKNOWN_PROP_NAME__
;;;				(NodeValue _EditablePolygonEachElementDo)
;;;			)
;;;			(if
;;;				(<
;;;					(= temp3
;;;						(GetDistance
;;;							pic
;;;							theStyle
;;;							(the__UNKNOWN_PROP_NAME__ x?)
;;;							(the__UNKNOWN_PROP_NAME__ y?)
;;;						)
;;;					)
;;;					temp4
;;;				)
;;;				(= temp4 temp3)
;;;				(= --UNKNOWN-PROP-NAME-- the__UNKNOWN_PROP_NAME__)
;;;			)
;;;			(breakif
;;;				(== _EditablePolygonEachElementDo _EditablePolygonAllTrue)
;;;			)
;;;			(= _EditablePolygonEachElementDo
;;;				(self next: _EditablePolygonEachElementDo)
;;;			)
;;;		)
;;;		(return temp4)
;;;	)
;;;	
;;;	(method (reflectPosn &tmp temp0 temp1 temp2 theCycleCnt theCycleCnt_2)
;;;		(if (= temp0 (self next: cycleDir))
;;;			(= theCycleCnt (NodeValue temp0))
;;;		else
;;;			(= theCycleCnt cycleCnt)
;;;		)
;;;		(if (= temp1 (self contains: cycleDir))
;;;			(= theCycleCnt_2 (NodeValue temp1))
;;;		else
;;;			(= theCycleCnt_2 cycleCnt)
;;;		)
;;;		(localproc_0020
;;;			(theCycleCnt_2 x?)
;;;			(theCycleCnt_2 y?)
;;;			(cycleCnt x?)
;;;			(cycleCnt y?)
;;;			(theCycleCnt x?)
;;;			(theCycleCnt y?)
;;;		)
;;;		(= --UNKNOWN-PROP-NAME-- rTop)
;;;		(= --UNKNOWN-PROP-NAME-- rLft)
;;;		(= --UNKNOWN-PROP-NAME-- rBot)
;;;		(= --UNKNOWN-PROP-NAME-- rRgt)
;;;	)
;;;	
;;;	(method (edgeToRoom)
;;;		(localproc_0020
;;;			(cycleCnt x?)
;;;			(cycleCnt y?)
;;;			--UNKNOWN-PROP-NAME--
;;;			--UNKNOWN-PROP-NAME--
;;;			--UNKNOWN-PROP-NAME--
;;;			--UNKNOWN-PROP-NAME--
;;;		)
;;;		(Graph grUPDATE_BOX rTop rLft rBot rRgt local0)
;;;	)
;;;	
;;;	(method (restore)
;;;		(self roomToEdge: 79)
;;;	)
;;;	
;;;	(method (save)
;;;		(self roomToEdge: 78)
;;;	)
;;;	
;;;	(method (roomToEdge param1 &tmp _EditablePolygonEachElementDo the_EditablePolygonEachElementDo temp2 temp3 _EditablePolygonAllTrue)
;;;		(= _EditablePolygonEachElementDo (self eachElementDo:))
;;;		(= _EditablePolygonAllTrue (self allTrue:))
;;;		(while
;;;			(or
;;;				(!= _EditablePolygonEachElementDo _EditablePolygonAllTrue)
;;;				completed
;;;			)
;;;			(= the_EditablePolygonEachElementDo
;;;				(self next: _EditablePolygonEachElementDo)
;;;			)
;;;			(= temp2 (NodeValue _EditablePolygonEachElementDo))
;;;			(= temp3 (NodeValue the_EditablePolygonEachElementDo))
;;;			(temp2 param1: temp3 &rest)
;;;			(breakif
;;;				(== _EditablePolygonEachElementDo _EditablePolygonAllTrue)
;;;			)
;;;			(= _EditablePolygonEachElementDo
;;;				the_EditablePolygonEachElementDo
;;;			)
;;;		)
;;;	)
;;;	
;;;	(method (cycleType &tmp temp0 temp1 temp2 temp3 temp4)
;;;		(= temp1 (Memory 2 (* caller 4)))
;;;		(= temp2 (FirstNode client))
;;;		(= temp0 temp1)
;;;		(while temp2
;;;			(= temp3 (NodeValue temp2))
;;;			(Memory 6 temp0 (temp3 x?))
;;;			(Memory 6 (+ temp0 2) (temp3 y?))
;;;			(= temp2 (NextNode temp2))
;;;			(= temp0 (+ temp0 4))
;;;		)
;;;		(curRoom
;;;			setAvoider:
;;;				((Polygon topString:)
;;;					type: howManyCycles
;;;					points: temp1
;;;					size: caller
;;;					lowlightColor: 1
;;;					showStr:
;;;				)
;;;		)
;;;	)
;;;	
;;;	(method (hesitation param1 &tmp temp0 temp1 temp2 temp3 [temp4 10] temp14)
;;;		(param1
;;;			setCel:
;;;				{\t\t\t((Polygon new:)\0D\n}
;;;				{\t\t\t\ttype:_}
;;;				(switch howManyCycles
;;;					(0 {PTotalAccess})
;;;					(1 {PNearestAccess})
;;;					(2 {PBarredAccess})
;;;				)
;;;				{,\0D\n}
;;;		)
;;;		(param1 setCel: {\t\t\t\tinit:\t})
;;;		(= temp14 1)
;;;		(= temp0 17)
;;;		(= temp3 (FirstNode client))
;;;		(while temp3
;;;			(= temp2 (NodeValue temp3))
;;;			(Format @temp4 500 0 (temp2 x?) (temp2 y?))
;;;			(if
;;;				(>=
;;;					(= temp0 (+ temp0 (= temp1 (+ (StrLen @temp4) 1))))
;;;					80
;;;				)
;;;				(param1 setCel: {\0D\n\t\t\t\t\t\t})
;;;				(= temp14 1)
;;;				(= temp0 (+ 17 temp1))
;;;			)
;;;			(if (not temp14) (param1 setCel: {_}))
;;;			(param1 setCel: @temp4)
;;;			(= temp14 0)
;;;			(= temp3 (NextNode temp3))
;;;		)
;;;		(param1 setCel: {,\0D\n})
;;;		(param1 setCel: {\t\t\t\tyourself\0D\n} {\t\t\t)\0D\n})
;;;	)
;;;	
;;;	(method (pauseCel &tmp temp0)
;;;		(if
;;;			(= temp0
;;;				(PrintD
;;;					80
;;;					{Polygon access type}
;;;					81
;;;					{Total}
;;;					1
;;;					81
;;;					{ Near_}
;;;					2
;;;					81
;;;					{ Barred_}
;;;					3
;;;					116
;;;					howManyCycles
;;;				)
;;;			)
;;;			(= howManyCycles (- temp0 1))
;;;		)
;;;	)
;;;)
;;;
;;;(instance readObstacle of Code
;;;	(properties)
;;;	
;;;	(method (doit param1 &tmp temp0 temp1 polyEditShowSelf)
;;;		(= polyEditShowSelf (PolyEdit showSelf:))
;;;		(= temp0 0)
;;;		(= temp1 (param1 points?))
;;;		(while (< temp0 (param1 size?))
;;;			(polyEditShowSelf
;;;				showSelf: (Memory 5 temp1) (Memory 5 (+ temp1 2)) 0
;;;				type: (param1 type?)
;;;			)
;;;			(++ temp0)
;;;			(= temp1 (+ temp1 4))
;;;		)
;;;		(polyEditShowSelf picture: 1)
;;;	)
;;;)
;;;
;;;(class PolyEdit of List
;;;	(properties
;;;		client 0
;;;		caller 0
;;;		cycleDir 0
;;;		cycleCnt 0
;;;		completed 0
;;;		endCel 0
;;;		startPal 0
;;;		endPal 0
;;;		howMany 0
;;;	)
;;;	
;;;	(method (flags)
;;;		(= local0 1)
;;;		(IconI prevSignal: state: 0)
;;;		(self radius:)
;;;		(self seconds: (if caller 1 else 0))
;;;		(self draw:)
;;;	)
;;;	
;;;	(method (doit &tmp eventTopString)
;;;		(self flags:)
;;;		(repeat
;;;			(= eventTopString (Event topString:))
;;;			(if
;;;			(not (if endPal (endPal prev: eventTopString)))
;;;				(GlobalToLocal eventTopString)
;;;				(breakif (self prev: eventTopString))
;;;			)
;;;			(eventTopString quitGame:)
;;;		)
;;;		(eventTopString quitGame:)
;;;		(self quitGame:)
;;;	)
;;;	
;;;	(method (quitGame)
;;;		(self xTilt:)
;;;		(endPal quitGame:)
;;;		(if (and cycleDir (not (IsObject cycleDir)))
;;;			(Print 500 4)
;;;		)
;;;		(if (and cycleDir (IsObject cycleDir))
;;;			(cycleDir quitGame:)
;;;		)
;;;		(if --UNKNOWN-PROP-NAME--
;;;			(Memory 3 --UNKNOWN-PROP-NAME--)
;;;		)
;;;		(IconI draw:)
;;;		(super quitGame:)
;;;	)
;;;	
;;;	(method (showSelf)
;;;		(super showSelf: (= cycleDir (Osc topString:)))
;;;		(return cycleDir)
;;;	)
;;;	
;;;	(method (delete &tmp theCycleDir)
;;;		(= theCycleDir cycleDir)
;;;		(self centerObj: 119 68)
;;;		(if (== cycleDir theCycleDir) (= cycleDir 0))
;;;		(super delete: theCycleDir &rest)
;;;		(theCycleDir quitGame:)
;;;	)
;;;	
;;;	(method (prev param1 &tmp temp0 theCycleCnt theCompleted)
;;;		(= theCycleCnt cycleCnt)
;;;		(= theCompleted completed)
;;;		(= cycleCnt (param1 x?))
;;;		(= completed (param1 y?))
;;;		(switch (param1 type?)
;;;			(0
;;;				(if cycleDir
;;;					(if
;;;						(and
;;;							startPal
;;;							(not (OneOf endCel 0 2))
;;;							(>
;;;								(+
;;;									(Abs (- theCycleCnt cycleCnt))
;;;									(Abs (- theCompleted completed))
;;;								)
;;;								1
;;;							)
;;;						)
;;;						(if (!= endCel 3) (self setLocales:))
;;;						(self seconds: 2)
;;;					)
;;;					(if
;;;						(and
;;;							(OneOf endCel 2 0)
;;;							(or
;;;								(!= theCycleCnt cycleCnt)
;;;								(!= theCompleted completed)
;;;							)
;;;						)
;;;						(self setRegions: cycleCnt completed)
;;;					)
;;;				)
;;;			)
;;;			(1
;;;				(= temp0 (param1 modifiers?))
;;;				(= startPal 1)
;;;				(cond 
;;;					((& temp0 $0004)
;;;						(if (== endCel 0)
;;;							(self update:)
;;;							(= startPal 0)
;;;						else
;;;							(self west:)
;;;						)
;;;					)
;;;					((& temp0 $0003) (if (!= endCel 0) (self picAngle:)))
;;;					((== endCel 0) (self maximum:))
;;;					(else (self minimum:))
;;;				)
;;;			)
;;;			(2
;;;				(= startPal 0)
;;;				(if (OneOf endCel 2 3) (self seconds: 1))
;;;			)
;;;			(4
;;;				(switch (param1 message?)
;;;					(9
;;;						(if (and (== endCel 1) cycleDir)
;;;							(self centerObj: 68 119)
;;;						)
;;;					)
;;;					(3840
;;;						(if (and (== endCel 1) cycleDir)
;;;							(self centerObj: 119 68)
;;;						)
;;;					)
;;;					(32
;;;						(if (and (== endCel 1) cycleDir) (cycleDir clean:))
;;;					)
;;;					(8
;;;						(if (and (== endCel 1) cycleDir) (cycleDir playBed:))
;;;					)
;;;					(99
;;;						(if (== endCel 1) (self seconds: 0) (= cycleDir 0))
;;;					)
;;;					(116
;;;						(if (and cycleDir (== endCel 1)) (cycleDir pauseCel:))
;;;					)
;;;					(100
;;;						(cond 
;;;							((== endCel 1) (if cycleDir (self picAngle:)))
;;;							((== endCel 0) (self update:))
;;;						)
;;;					)
;;;					(8704
;;;						(if (== endCel 1) (Approach doit:))
;;;					)
;;;					(63
;;;						(switch endCel
;;;							(0 (localproc_0004 500 1))
;;;							(1 (localproc_0004 500 2))
;;;						)
;;;					)
;;;					(117 (self setFeatures:))
;;;					(109
;;;						(if (== local0 1)
;;;							(Show (= local0 4))
;;;						else
;;;							(Show (= local0 1))
;;;						)
;;;						(Graph grUPDATE_BOX 0 0 190 319 local0)
;;;					)
;;;					(97 (localproc_0004 500 3 30 1))
;;;					(114
;;;						(if (== endCel 1) (self draw:))
;;;					)
;;;					(120 (return (self yTilt:)))
;;;					(27
;;;						(if (== endCel 0) (self update:))
;;;					)
;;;				)
;;;			)
;;;		)
;;;		(return 0)
;;;	)
;;;	
;;;	(method (seconds theEndCel)
;;;		(if endPal (endPal quitGame:))
;;;		(= endPal
;;;			(switch (= endCel theEndCel)
;;;				(0 addMenu)
;;;				(1 editMenu)
;;;				(2 dragMenu)
;;;				(else  0)
;;;			)
;;;		)
;;;		(if endPal (endPal flags:))
;;;	)
;;;	
;;;	(method (draw)
;;;		(self isKindOf: 79)
;;;		(self isKindOf: 78)
;;;		(self isKindOf: 83)
;;;		(Graph grUPDATE_BOX 0 0 190 319 local0)
;;;	)
;;;	
;;;	(method (stop param1 param2 &tmp temp0 theCycleDir temp2 theTheCycleDir temp4)
;;;		(= temp0 32767)
;;;		(= theCycleDir 0)
;;;		(= temp4 (FirstNode client))
;;;		(while temp4
;;;			(if
;;;				(<
;;;					(= temp2
;;;						((= theTheCycleDir (NodeValue temp4))
;;;							param1: cycleCnt completed
;;;						)
;;;					)
;;;					temp0
;;;				)
;;;				(= temp0 temp2)
;;;				(= theCycleDir theTheCycleDir)
;;;			)
;;;			(= temp4 (NextNode temp4))
;;;		)
;;;		((= cycleDir theCycleDir) south: param2)
;;;	)
;;;	
;;;	(method (minimum &tmp eventTopString)
;;;		(self stop: 395 1)
;;;		(= eventTopString (Event topString:))
;;;		(GlobalToLocal eventTopString)
;;;		(= cycleCnt (eventTopString x?))
;;;		(= completed (eventTopString y?))
;;;		(eventTopString quitGame:)
;;;	)
;;;	
;;;	(method (maximum)
;;;		(self setLocales:)
;;;		(if (not cycleDir)
;;;			(self showSelf:)
;;;			(cycleDir showSelf: cycleCnt completed 0)
;;;		)
;;;		(cycleDir showSelf: cycleCnt completed)
;;;	)
;;;	
;;;	(method (update &tmp polyEditEachElementDo)
;;;		(cond 
;;;			(cycleDir
;;;				(cycleDir picture: 1)
;;;				(if (> (cycleDir size?) 1)
;;;					(cycleDir picAngle: (cycleDir allTrue:) clean:)
;;;				)
;;;				(self draw:)
;;;				(cycleDir pauseCel:)
;;;			)
;;;			(
;;;			(not (= polyEditEachElementDo (self eachElementDo:))) (= cycleDir 0))
;;;			(else
;;;				(= cycleDir (NodeValue polyEditEachElementDo))
;;;				(self draw:)
;;;			)
;;;		)
;;;		(if cycleDir (self seconds: 1))
;;;	)
;;;	
;;;	(method (setRegions region param2)
;;;		(cycleDir reflectPosn:)
;;;		(self isKindOf: 79)
;;;		(cycleDir setRegions: region param2)
;;;		(self isKindOf: 78)
;;;		(self isKindOf: 83)
;;;		(cycleDir edgeToRoom:)
;;;	)
;;;	
;;;	(method (west)
;;;		(self isKindOf: 79)
;;;		(self stop: 394 0)
;;;		(self setLocales:)
;;;		(cycleDir west: cycleCnt completed)
;;;		(self seconds: 3)
;;;		(self isKindOf: 78)
;;;		(self isKindOf: 83)
;;;		(Graph grUPDATE_BOX 0 0 190 319 local0)
;;;	)
;;;	
;;;	(method (picAngle &tmp temp0)
;;;		(self isKindOf: 79)
;;;		(self stop: 395 0)
;;;		(self setLocales:)
;;;		(cycleDir picAngle:)
;;;		(if (not (cycleDir size?))
;;;			(self delete: cycleDir)
;;;			(if (not caller) (self seconds: 0))
;;;		)
;;;		(self isKindOf: 78)
;;;		(self isKindOf: 83)
;;;		(Graph grUPDATE_BOX 0 0 190 319 local0)
;;;	)
;;;	
;;;	(method (setFeatures &tmp temp0 the__UNKNOWN_PROP_NAME__ theHowMany the__UNKNOWN_PROP_NAME___2 the__UNKNOWN_PROP_NAME___3 the__UNKNOWN_PROP_NAME___4 the__UNKNOWN_PROP_NAME___5)
;;;		(if (not --UNKNOWN-PROP-NAME--) (Print 500 5) (return))
;;;		(= the__UNKNOWN_PROP_NAME__ --UNKNOWN-PROP-NAME--)
;;;		(= theHowMany howMany)
;;;		(= the__UNKNOWN_PROP_NAME___2 --UNKNOWN-PROP-NAME--)
;;;		(= the__UNKNOWN_PROP_NAME___3 --UNKNOWN-PROP-NAME--)
;;;		(= the__UNKNOWN_PROP_NAME___4 --UNKNOWN-PROP-NAME--)
;;;		(= the__UNKNOWN_PROP_NAME___5 --UNKNOWN-PROP-NAME--)
;;;		(self setLocales:)
;;;		(self isKindOf: 79)
;;;		(if (= cycleDir the__UNKNOWN_PROP_NAME__)
;;;			(if (not (self yourself: cycleDir))
;;;				(= cycleDir (self showSelf:))
;;;				(if theHowMany
;;;					(self release: theHowMany cycleDir)
;;;				else
;;;					(self isEmpty: cycleDir)
;;;				)
;;;			)
;;;			(cycleDir setFeatures: the__UNKNOWN_PROP_NAME___2)
;;;		else
;;;			(= cycleDir (self showSelf:))
;;;		)
;;;		(Memory 3 the__UNKNOWN_PROP_NAME___2)
;;;		(= cycleCnt the__UNKNOWN_PROP_NAME___3)
;;;		(= completed the__UNKNOWN_PROP_NAME___4)
;;;		(self seconds: the__UNKNOWN_PROP_NAME___5)
;;;		(self isKindOf: 78)
;;;		(self isKindOf: 83)
;;;		(Graph grUPDATE_BOX 0 0 190 319 local0)
;;;		(SetCursor normalCursor 1 cycleCnt completed)
;;;	)
;;;	
;;;	(method (setLocales)
;;;		(if (= --UNKNOWN-PROP-NAME-- cycleDir)
;;;			(= howMany
;;;				(self contains: (= --UNKNOWN-PROP-NAME-- cycleDir))
;;;			)
;;;			(= --UNKNOWN-PROP-NAME-- (cycleDir setLocales:))
;;;		)
;;;		(= --UNKNOWN-PROP-NAME-- cycleCnt)
;;;		(= --UNKNOWN-PROP-NAME-- completed)
;;;		(= --UNKNOWN-PROP-NAME-- endCel)
;;;	)
;;;	
;;;	(method (centerObj param1 param2 &tmp temp0 temp1)
;;;		(= temp1 (FindKey client cycleDir))
;;;		(if
;;;			(and
;;;				(not (= temp0 (self param1: temp1)))
;;;				(not (= temp0 (self param2: temp1)))
;;;			)
;;;			(= temp0 temp1)
;;;		)
;;;		(= cycleDir (NodeValue temp0))
;;;		(cycleDir east: (cycleDir keep?))
;;;	)
;;;	
;;;	(method (radius)
;;;		(if (curRoom highlightColor?)
;;;			((curRoom highlightColor?) isKindOf: 99 readObstacle)
;;;		)
;;;	)
;;;	
;;;	(method (xTilt)
;;;		(if (curRoom highlightColor?)
;;;			((curRoom highlightColor?) isKindOf: 103 firstTrue:)
;;;			(self isKindOf: 399)
;;;		)
;;;	)
;;;	
;;;	(method (yTilt &tmp [temp0 100] temp100 userTopString temp102 temp103)
;;;		(if (== endCel 0) (self update:))
;;;		(if (not fileName) (Format @fileName 500 6 curRoomNum))
;;;		(if
;;;			(not
;;;				(= temp100
;;;					(PrintD
;;;						80
;;;						{Save Polygons}
;;;						{File:_}
;;;						41
;;;						@fileName
;;;						25
;;;						101
;;;						81
;;;						{ Save_}
;;;						1
;;;						4
;;;						1
;;;						81
;;;						{Abandon}
;;;						2
;;;						4
;;;						5
;;;						81
;;;						{Cancel}
;;;						0
;;;						4
;;;						5
;;;					)
;;;				)
;;;			)
;;;			(return 0)
;;;		)
;;;		(if (== temp100 2) (return 1))
;;;		(if
;;;			(and
;;;				(FileIO 10 @fileName)
;;;				(not
;;;					(= temp100
;;;						(PrintD
;;;							(Format @temp0 500 7 @fileName)
;;;							101
;;;							81
;;;							{Replace}
;;;							1
;;;							81
;;;							{Append}
;;;							2
;;;							81
;;;							{Cancel}
;;;							0
;;;						)
;;;					)
;;;				)
;;;			)
;;;			(return 0)
;;;		)
;;;		(= temp102 (if (== temp100 1) 2 else 0))
;;;		(if
;;;			(not
;;;				((= userTopString (User topString:))
;;;					name: @fileName
;;;					track: temp102
;;;				)
;;;			)
;;;			(Printf 500 8 (userTopString name?))
;;;			(return 0)
;;;		)
;;;		(= temp103 (Print 500 9 103))
;;;		(userTopString
;;;			setCel: (Format @temp0 500 10 {Polygon Editor 1.0})
;;;		)
;;;		(userTopString
;;;			setCel: (Format @temp0 500 11 {Dynamic Obstacles} curRoomNum)
;;;		)
;;;		(userTopString setCel: {\t\t(curRoom addObstacle:\0D\n})
;;;		(self isKindOf: 400 userTopString)
;;;		(userTopString setCel: {\t\t)\0D\n})
;;;		(userTopString quitGame:)
;;;		(temp103 quitGame:)
;;;		(return 1)
;;;	)
;;;)
;;;