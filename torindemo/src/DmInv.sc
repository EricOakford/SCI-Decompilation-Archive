;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64895)
(include sci.sh)
(use Main)
(use List)
(use Set)
(use PArray)
(use Array)
(use Print)
(use Feature)
(use Actor)
(use System)


(class InventItem of Obj
	(properties
		scratch 0
		owner -1
		verb 0
		noun 0
		bHilited 0
		bChecked 0
		oChildren 2
		oParent 0
		oHandler 0
		nLevel 0
	)
	
	(method (dispose)
		(self moveTo: -1)
		(if oHandler (oHandler dispose:))
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(if
		(and noun msgType (Message msgGET 0 noun theVerb 0 1))
			(messager say: noun theVerb 0 0 0 0)
		)
	)
	
	(method (ownedBy param1)
		(return (== owner param1))
	)
	
	(method (moveTo theOwner theNLevelORightString &tmp nLevelORightString temp1)
		(if (== owner theOwner) (return (== owner theOwner)))
		(if
			(and
				(> argc 1)
				(< theNLevelORightString (nLevel nMinTextWidth?))
			)
			(= nLevelORightString theNLevelORightString)
		else
			(= nLevelORightString (nLevel oRightString:))
		)
		(if (== owner -2) (= gVerb 1) (= gInventItem 0))
		(if (== theOwner -2)
			(if gInventItem (gInventItem moveTo: -3))
			(= gVerb verb)
			(= gInventItem self)
		)
		(if (== owner -3) (nLevel oLeftString: self))
		(if (== theOwner -3)
			(nLevel nTextHeight: self nLevelORightString)
		)
		(= owner theOwner)
		(return self)
	)
	
	(method (setVisibleRange)
		(if (not oHandler) (= oHandler (Set new:)))
		(oHandler add: &rest)
	)
	
	(method (setTotalWidth)
		(if oHandler (oHandler delete: &rest))
	)
	
	(method (voCheck param1)
		(param1 add: verb bHilited)
	)
	
	(method (setSpeedDirect param1)
		(return
			(if oHandler
				(return (oHandler contains: param1))
			else
				(return 0)
			)
		)
	)
)

(class InvSlot of View
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
		sightAngle 26505
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
		view -5531
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
		nRightWidth 0
		nLeftWidth 0
		nLevel 0
	)
	
	(method (doVerb theVerb &tmp temp0)
		(cond 
			(nRightWidth
				(if (== theVerb 1)
					(nRightWidth moveTo: -2)
				else
					(nRightWidth doVerb: theVerb &rest)
				)
			)
			(gInventItem (gInventItem moveTo: -3 nLeftWidth))
		)
	)
	
	(method (setSpeedDirect param1)
		(if (and (!= nRightWidth 0) (== param1 1)) (return 1))
		(if
		(and (not nRightWidth) nLevel (nLevel addItems: param1))
			(return 1)
		)
		(return
			(if nRightWidth
				(return (nRightWidth setSpeedDirect: param1 &rest))
			else
				(return 0)
			)
		)
	)
	
	(method (nGapWidth theNRightWidth theNLeftWidth)
		(if (> argc 0)
			(= nRightWidth theNRightWidth)
			(if nRightWidth
				(= view (theNRightWidth bHilited?))
				(= loop (theNRightWidth bChecked?))
				(= cel (theNRightWidth oChildren?))
				(= noun (theNRightWidth noun?))
				(= signal (| signal $1000))
			else
				(= view (nLevel checkStatus?))
				(= noun (= cel (= loop 0)))
				(= signal (& signal $efff))
			)
			(if (> argc 1) (= nLeftWidth theNLeftWidth))
		)
	)
)

(class InvHandler of Obj
	(properties
		scratch 0
		unhilite 2
		uncheck 2
		setChildPos 2
		setMinTextWidth 2
		calcTextWidths 0
		setup 0
		getCheckWidth 15
		nStyle 15
		checkStatus -1
		nHBorder 0
		nVBorder 0
		nUnhilitedBack 0
		nHilitedBack 0
		nMinTextWidth 0
		vUnhilited 0
		vHilited 0
		nUnhilitedFore 1
	)
	
	(method (init theTheCast param2 &tmp theCast temp1 temp2 theCalcTextWidths theSetup temp5 nHBorderSize temp7)
		(if (not argc)
			(= theCast cast)
		else
			(= theCast theTheCast)
		)
		(if (> argc 1) (= temp7 param2) else (= temp7 0))
		(if temp7
			(= nMinTextWidth (/ (temp7 size:) 2))
			(= nHilitedBack nMinTextWidth)
		else
			(= nMinTextWidth (* setChildPos setMinTextWidth))
			(= nHilitedBack (* unhilite uncheck))
		)
		(if (== nMinTextWidth nHilitedBack)
			(= nUnhilitedFore 0)
		else
			(= nUnhilitedFore 1)
		)
		(= nVBorder (IntArray new: nMinTextWidth))
		(= nUnhilitedBack (List new:))
		(= nHBorder (PArray new:))
		(if temp7
			(= temp1 0)
			(while (< temp1 nHilitedBack)
				(nUnhilitedBack
					addToEnd:
						((InvSlot new:)
							view: checkStatus
							nLevel: self
							posn: (temp7 at: (* temp1 2)) (temp7 at: (+ (* temp1 2) 1))
							init: theCast
							nLeftWidth: temp1
							yourself:
						)
				)
				(++ temp1)
			)
		else
			(= theSetup setup)
			(= temp1 0)
			(while (< temp1 uncheck)
				(= theCalcTextWidths calcTextWidths)
				(= temp2 0)
				(while (< temp2 unhilite)
					(nUnhilitedBack
						addToEnd:
							((InvSlot new:)
								view: checkStatus
								nLevel: self
								posn: theCalcTextWidths theSetup
								init: theCast
								nLeftWidth: (+ temp2 (* temp1 setChildPos))
								yourself:
							)
					)
					(= theCalcTextWidths (+ theCalcTextWidths getCheckWidth))
					(++ temp2)
				)
				(= theSetup (+ theSetup nStyle))
				(++ temp1)
			)
		)
		(self nScreenOrgX:)
		(if nHBorder
			(= nHBorderSize (nHBorder size:))
			(= temp1 0)
			(while (< temp1 nHBorderSize)
				(if (= temp5 (nHBorder at: temp1))
					(temp5 nLevel: self init:)
				)
				(++ temp1)
			)
		)
	)
	
	(method (dispose)
		(if nVBorder (nVBorder dispose:))
		(if nUnhilitedBack (nUnhilitedBack dispose:))
		(if nHBorder (nHBorder dispose:))
		(super dispose: &rest)
	)
	
	(method (nScreenOrgX)
	)
	
	(method (addItems param1 &tmp temp0 nHBorderSize temp2)
		(if nHBorder
			(= nHBorderSize (nHBorder size:))
			(= temp0 0)
			(while (< temp0 nHBorderSize)
				(if
					(and
						(= temp2 (nHBorder at: temp0))
						(== param1 (temp2 verb?))
					)
					(return temp2)
				)
				(++ temp0)
			)
		)
		(return 0)
	)
	
	(method (oRightString &tmp temp0)
		(if nVBorder
			(= temp0 0)
			(while (< temp0 nMinTextWidth)
				(if (not (nVBorder at: temp0)) (return temp0))
				(++ temp0)
			)
		)
		(Prints
			{error -- can't find open inventory slot. djm, dminv.sc}
		)
		(return -1)
	)
	
	(method (nInitCursorX param1 param2)
		(self
			fadeRel: (+ vUnhilited param1) (+ vHilited param2)
		)
	)
	
	(method (fadeRel theVUnhilited_2 theVHilited_2 &tmp theVUnhilited theVHilited)
		(if (not nUnhilitedFore) (return))
		(= theVUnhilited vUnhilited)
		(= theVHilited vHilited)
		(= vUnhilited theVUnhilited_2)
		(= vHilited theVHilited_2)
		(cond 
			((> (+ vUnhilited unhilite) setChildPos) (= vUnhilited (- setChildPos unhilite)))
			((< vUnhilited 0) (= vUnhilited 0))
			((> (+ vHilited uncheck) setMinTextWidth) (= vHilited (- setMinTextWidth uncheck)))
			((< vHilited 0) (= vHilited 0))
		)
		(if
			(or
				(!= theVUnhilited vUnhilited)
				(!= theVHilited vHilited)
			)
			(self update:)
		)
	)
	
	(method (nFont &tmp temp0 temp1 temp2)
		(= temp1 0)
		(while (< temp1 nMinTextWidth)
			(if (= temp0 (nVBorder at: temp1))
				(= temp2 0)
				(while (< temp2 temp1)
					(if (not (nVBorder at: temp2))
						(nVBorder at: temp2 temp0)
						(nVBorder at: temp1 0)
					)
					(++ temp2)
				)
			)
			(++ temp1)
		)
	)
	
	(method (nTextHeight param1 param2)
		(if (and (>= param2 0) (< param2 nMinTextWidth))
			(nVBorder at: param2 param1)
			(self getCheckView: param2)
		)
	)
	
	(method (oLeftString param1 &tmp temp0)
		(= temp0 0)
		(while (< temp0 nMinTextWidth)
			(if (== param1 (nVBorder at: temp0))
				(nVBorder at: temp0 0)
				(self getCheckView: temp0)
				(break)
			)
			(++ temp0)
		)
	)
	
	(method (get what)
		(nHBorder at: what)
	)
	
	(method (add)
		(if nHBorder (nHBorder add: &rest))
	)
	
	(method (nHilitedFore param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(= temp2 30000)
		(= temp4 0)
		(if nUnhilitedBack
			(= temp0 0)
			(while (< temp0 nHilitedBack)
				(if
					(or
						(not (= temp5 (nUnhilitedBack at: temp0)))
						(not (temp5 nRightWidth?))
					)
					(= temp4 1)
					(if
						(<
							(= temp1
								(GetDistance param1 param2 (temp5 x?) (temp5 y?))
							)
							temp2
						)
						(= temp2 temp1)
						(= temp3 temp0)
					)
				)
				(++ temp0)
			)
		)
		(return
			(if temp4
				(return (nUnhilitedBack at: temp3))
			else
				(Prints
					{error -- find nearest open can't find open inventory slot. djm, dminv.sc}
				)
				(return 0)
			)
		)
	)
	
	(method (update &tmp temp0 temp1 temp2 temp3 temp4 temp5)
		(if nUnhilitedFore
			(= temp0 0)
			(while (< temp0 uncheck)
				(= temp1 0)
				(while (< temp1 unhilite)
					(= temp2 (+ temp1 (* temp0 unhilite)))
					(= temp3 (nUnhilitedBack at: temp2))
					(= temp4
						(+ temp1 vUnhilited (* (+ temp0 vHilited) setChildPos))
					)
					(= temp5 (nVBorder at: temp4))
					(temp3 nGapWidth: temp5 temp4)
					(++ temp1)
				)
				(++ temp0)
			)
		else
			(= temp0 0)
			(while (< temp0 nHilitedBack)
				(= temp3 (nUnhilitedBack at: temp0))
				(= temp5 (nVBorder at: temp0))
				(temp3 nGapWidth: temp5 temp4)
				(++ temp0)
			)
		)
	)
	
	(method (getCheckView param1 &tmp temp0 temp1 temp2)
		(if nUnhilitedFore
			(= temp1 (- (/ param1 setChildPos) vHilited))
			(= temp2
				(+
					(= temp0
						(- (- param1 (* temp1 setChildPos)) vUnhilited)
					)
					(* temp1 unhilite)
				)
			)
		else
			(= temp2 param1)
		)
		((nUnhilitedBack at: temp2)
			nGapWidth: (nVBorder at: param1)
		)
	)
)

(class InventWellFeature of Feature
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
		sightAngle 26505
		actions 0
		onMeCheck $0000
		state $0000
		approachX 0
		approachY 0
		approachDist 0
		_approachVerbs 0
		plane 0
		x 0
		y -1
		z 0
		nLevel 0
	)
	
	(method (doVerb theVerb &tmp temp0)
		(if (not nLevel)
			(Prints {no handler for InventWellFeature. djm, inv.sc})
			(return)
		)
		(if
			(not
				(= temp0
					(nLevel
						nHilitedFore: (- mouseX (plane left:)) (- mouseY (plane top?))
					)
				)
			)
			(return)
		)
		(temp0 doVerb: theVerb &rest)
	)
	
	(method (setSpeedDirect param1)
		(return
			(if (and nLevel (nLevel addItems: param1))
				(return 1)
			else
				(return 0)
			)
		)
	)
)
