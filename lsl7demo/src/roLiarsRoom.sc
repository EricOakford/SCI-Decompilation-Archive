;;; Sierra Script 1.0 - (do not remove this comment)
(script# 9000)
(include sci.sh)
(use Main)
(use DialogPlane)
(use PopMenu)
(use L7Room)
(use TiledBitmap)
(use TextView)
(use PushButton)
(use GenDialog)
(use Array)
(use Print)
(use Talker)
(use Polygon)
(use CueObj)
(use Timer)
(use Motion)
(use Actor)
(use System)

(public
	roLiarsRoom 0
)

(local
	[local0 5] = [40 65 29 54 79]
	[local5 5] = [40 40 64 64 64]
	[local10 5] = [7 31 55 79 103]
	local15 =  137
	[local16 4] = [134 134 134 158]
	[local20 4] = [47 71 95 95]
	[local24 4] = [161 161 161 137]
	[local28 4] = [68 44 20 20]
	local32 =  500
	local33 =  500
	local34
	theNSlotNum
	theTheNSlotNum
	theNLastValue_2 =  2
	theTheNLastValue_2 =  2
	theNNumDiceBet =  1
	theTheNNumDiceBet_2 =  1
	local41 =  1
	local42
	local43 =  1
	local44 =  100
	local45 =  100
	local46
	local47
	local48
	local49
	local50 =  2
	local51
	local52
	local53
	local54
	local55
	local56
	local57
	local58 =  1
	local59
	local60
)
(procedure (localproc_5c27 param1 param2 param3 param4 param5 param6 &tmp temp0 temp1 temp2 temp3 newTextItemNHeight_2 newDismissTextButtonNWidth newDismissTextButtonNHeight temp7 temp8 newDialogPlane newTiledView newDismissTextButton temp12 newDismissTextButton_2 newTiledViewNWidth newTextItemNHeight newTiledViewNOffsetX newTiledViewNOffsetY temp18 temp19 newTextItem temp21 temp22)
	(if
	(or (< argc 3) (not param1) (not param2) (not param3))
		(if param1 (proc64896_7 param1))
		(if param2 (proc64896_7 param2))
		(if param3 (proc64896_7 param3))
		(PrintDebug
			{Illegal call of MakeChoice procedure. liars.sc SRC}
		)
		(return 0)
	)
	(if (< argc 6) (= temp1 -2) else (= temp1 param4))
	(if (< argc 7) (= temp2 -2) else (= temp2 param5))
	(if (< argc 8) (= temp0 300) else (= temp0 param6))
	((= newDialogPlane (DialogPlane new:)) init: 0 0 1 1)
	(= temp3 (+ (GetTextWidth param1 global270 0) 20))
	(= temp21
		(+
			(= temp21
				(Max
					(GetTextWidth param2 global270 0)
					(GetTextWidth param3 global270 0)
				)
			)
			10
		)
	)
	(= newDismissTextButton (DismissTextButton new:))
	(newDismissTextButton
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param2))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp21
		value: 1
		bDefault: 1
		init: newDialogPlane
	)
	(= newDismissTextButton_2 (DismissTextButton new:))
	(newDismissTextButton_2
		font: global270
		nLeading: global271
		fore: gFore
		back: gBack
		text: (Array 8 (Array 9 param3))
		bTileBorder: 1
		vTileOff: global276
		vTileOn: global277
		nMinWidth: temp21
		value: 0
		init: newDialogPlane
	)
	(= newDismissTextButtonNWidth
		(newDismissTextButton nWidth?)
	)
	(= newDismissTextButtonNHeight
		(newDismissTextButton nHeight?)
	)
	(= temp7 (+ (Max temp3 newDismissTextButtonNWidth) 10))
	(if param1
		(= newTextItem (TextItem new:))
		(newTextItem
			font: global268
			nLeading: global269
			maxWidth: temp0
			nMinWidth: (+ temp7 40)
			fore: global274
			back: 255
			skip: 255
			text: (Array 8 (Array 9 param1))
			border: 5
			bTileBorder: 0
			vTile: -5516
			init: newDialogPlane
		)
		(= newTextItemNHeight (newTextItem nHeight?))
		(= newTextItemNHeight_2 (newTextItem nHeight?))
	else
		(= newTextItem 0)
	)
	(= temp8
		(+
			newTextItemNHeight_2
			(* newDismissTextButtonNHeight 2)
		)
	)
	(if param1 (= temp22 -5517) else (= temp22 global275))
	((= newTiledView (TiledView new:))
		view: temp22
		init: temp7 temp8 0 1 newDialogPlane
	)
	(= newTiledViewNWidth (newTiledView nWidth?))
	(= newTextItemNHeight
		(+ newTextItemNHeight (newTiledView nHeight?))
	)
	(switch temp1
		(-1
			(= temp1 (/ (- screenWidth newTiledViewNWidth) 2))
		)
		(-2
			(= temp1
				(+
					(thePlane left:)
					(/ (- (thePlane getWidth:) newTiledViewNWidth) 2)
				)
			)
		)
	)
	(switch temp2
		(-1
			(= temp2 (/ (- screenHeight newTextItemNHeight) 2))
		)
		(-2
			(= temp2
				(+
					(thePlane top?)
					(/ (- (thePlane getHeight:) newTextItemNHeight) 2)
				)
			)
		)
	)
	(newDialogPlane
		setRect:
			temp1
			temp2
			(- (+ temp1 newTiledViewNWidth) 1)
			(- (+ temp2 newTextItemNHeight) 1)
	)
	(UpdatePlane newDialogPlane)
	(= newTiledViewNOffsetX (newTiledView nOffsetX?))
	(= newTiledViewNOffsetY (newTiledView nOffsetY?))
	(if newTextItem
		(newTiledView
			posn: (newTiledView x?) (+ (newTiledView y?) (newTextItem nHeight?))
		)
		(= newTiledViewNOffsetY
			(+ newTiledViewNOffsetY (newTextItem nHeight?))
		)
	)
	(= temp18
		(/
			(- newTiledViewNWidth (+ newDismissTextButtonNWidth 5))
			2
		)
	)
	(= temp19 (+ newTiledViewNOffsetY 10))
	(newDismissTextButton posn: temp18 temp19)
	(newDismissTextButton_2
		posn: temp18 (+
			(newDismissTextButton y?)
			newDismissTextButtonNHeight
			5
		)
	)
	(if param1 (proc64896_7 param1))
	(if param2 (proc64896_7 param2))
	(if param3 (proc64896_7 param3))
	(return (newDialogPlane sitNSpin:))
)

(instance roLiarsRoom of L7Room
	(properties
		picture 13000
	)
	
	(method (init &tmp temp0)
		(super init: &rest)
		(Palette 2 0 255 100)
		(Load rsVIEW -5527)
		(Load rsSCRIPT -1520)
		(Load 145 -1520)
		(Load 140 -14318)
		(proc64038_2 0)
		(= gToLarry toLarry)
		(= gToDewmi toDewmi)
		(poLarry init:)
		(voLarryBust init:)
		(oBabe init:)
		(voLarryMoney init:)
		(voBabeMoney init:)
		(voPotMoney init:)
		(poFountain init:)
		(oBetDie init:)
		(oBetButton init:)
		(oChallengeButton init: disable:)
		(oHowToPlayButton init:)
		(oDie2Button init:)
		(oDie3Button init:)
		(oDie4Button init:)
		(oDie5Button init:)
		(oDie6Button init:)
		(oDiceRadioGroup init:)
		(foRollArea init:)
		(foShowArea init:)
		(= local42
			(IDArray
				newWith:
					15
					(foSlot1 init: yourself:)
					(foSlot1A init: yourself:)
					(foSlot2 init: yourself:)
					(foSlot3 init: yourself:)
					(foSlot2A init: yourself:)
					(foSlot4 init: yourself:)
					(foSlot5 init: yourself:)
					(foSlot3A init: yourself:)
					(foSlot6 init: yourself:)
					(foSlot7 init: yourself:)
					(foSlot4A init: yourself:)
					(foSlot8 init: yourself:)
					(foSlot9 init: yourself:)
					(foSlot5A init: yourself:)
					(foSlot10 init: yourself:)
			)
		)
		(cup init:)
		(oLarryDice init:)
		(oBabeDice init:)
		(= temp0 0)
		(while (< temp0 5)
			(oLarryDice
				add:
					((Die new:)
						nOwner: 0
						view: 9000
						loop: 4
						x: [local0 temp0]
						y: [local5 temp0]
						init:
						yourself:
					)
			)
			(oBabeDice
				add:
					((Die new:)
						nOwner: 1
						view: 9000
						loop: 4
						x: (+ [local0 temp0] 192)
						y: [local5 temp0]
						init:
						yourself:
					)
			)
			(++ temp0)
		)
		(proc64928_2 412 366 375)
		(gOMusic1 stop: setMusic: -14336)
		(self setScript: soIntro)
	)
	
	(method (dispose)
		(proc64928_2)
		(= gToLarry 0)
		(= gToDewmi 0)
		(if (timers contains: oDelayTimer)
			(oDelayTimer dispose: delete:)
		)
		(gOMusic1 fadeOut:)
		(oLarryDice release:)
		(oBabeDice release:)
		(local42 dispose:)
		(super dispose: &rest)
	)
)

(class Die of Prop
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
		priority 480
		fixPriority 1
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
		signal $5021
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
		cycleSpeed 7
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		code 0
		nOwner 0
		nValue 0
		bShowing 0
		bDiscarded 0
		bShowed 0
		bRolling 0
		bImAHog 0
		nCurX 0
		nCurY 0
		nClickTime 0
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (handleEvent event &tmp eventType temp1)
		(= eventType (event type?))
		(= temp1 (self onMe: event))
		(cond 
			(bImAHog
				(self x: (- mouseX 11) y: (- mouseY 11))
				(if (== eventType evMOUSERELEASE) (self stopHogging:))
				(event claimed: 1)
			)
			((and temp1 (== eventType evMOUSEBUTTON))
				(= local60 (= bImAHog 1))
				(gOEventHandler registerEventHog: self)
				(= nCurX x)
				(= nCurY y)
				(= nClickTime gameTime)
				(self setPri: 600)
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
		(event claimed?)
	)
	
	(method (doVerb)
		(if bShowing (self hideIt:) else (self showIt:))
		(gOSound1 playSound: -14318)
	)
	
	(method (onMe)
		(return
			(if
				(or
					bDiscarded
					bShowed
					bRolling
					(== nOwner 1)
					(and
						(not bShowing)
						(==
							(- (oLarryDice size:) ((oLarryDice oShowing?) size:))
							1
						)
					)
				)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
	
	(method (stopHogging)
		(= local60 (= bImAHog 0))
		(gOEventHandler unregisterEventHog: self)
		(if
			(or
				(<= (- gameTime nClickTime) 30)
				(and
					bShowing
					(foRollArea onMe: (+ (self x?) 11) (+ (self y?) 11))
				)
				(and
					(not bShowing)
					(foShowArea onMe: (+ (self x?) 11) (+ (self y?) 11))
				)
			)
			(self doVerb:)
		else
			(= x nCurX)
			(= y nCurY)
			(gOSound1 playSound: -14318)
		)
		(self setPri: 480)
	)
	
	(method (rollDie)
		(cond 
			((and (== nOwner 0) (not bShowing)) (self setScript: (soDieRoll new:)))
			((and (== nOwner 1) (not bShowing)) (= nValue (Random 1 6)) (= loop (+ nValue 3)))
		)
	)
	
	(method (showIt)
		(if bShowing (return 0))
		(return
			(if (== nOwner 0)
				(self bShowing: 1 giveShowPosn:)
				((oLarryDice oShowing?) addToFront: self)
				(oLarryDice delete: self add: self)
			else
				(self bShowing: 1 giveShowPosn:)
				((oBabeDice oShowing?) addToFront: self)
			)
		)
	)
	
	(method (hideIt)
		(if (not bShowing) (return 0))
		(self bShowing: 0 giveHidePosn:)
		((oLarryDice oShowing?) delete: self)
		(return (oLarryDice delete: self add: self))
	)
	
	(method (giveShowPosn &tmp temp0 temp1 temp2 temp3 oLarryDiceOShowing temp5 oLarryDiceOShowingFirst)
		(if (== nOwner 0)
			(= temp1 0)
			(= temp2 0)
			(if
			((= oLarryDiceOShowing (oLarryDice oShowing?)) size:)
				(while
				(and (not temp1) (< temp2 (oLarryDiceOShowing size:)))
					(= temp3 0)
					(= temp5
						(List
							8
							(= oLarryDiceOShowingFirst (oLarryDiceOShowing first:))
						)
					)
					(= temp0 0)
					(while (< temp0 (oLarryDiceOShowing size:))
						(if
							(and
								(== (temp5 x?) [local16 temp2])
								(== (temp5 y?) [local20 temp2])
							)
							(= temp3 1)
							(break)
						)
						(if
						(!= oLarryDiceOShowingFirst (oLarryDiceOShowing last:))
							(= temp5
								(List
									8
									(= oLarryDiceOShowingFirst
										(oLarryDiceOShowing next: oLarryDiceOShowingFirst)
									)
								)
							)
						else
							(= temp5 0)
						)
						(++ temp0)
					)
					(if (not temp3)
						(= temp1 1)
						(self x: [local16 temp2] y: [local20 temp1])
					else
						(++ temp2)
					)
				)
				(if (not temp1)
					(self
						x: [local16 (oLarryDiceOShowing size:)]
						y: [local20 (oLarryDiceOShowing size:)]
					)
				)
			else
				(self x: [local16 0] y: [local20 0])
			)
		else
			(self x: [local24 local46] y: [local28 local46])
			(++ local46)
		)
	)
	
	(method (giveHidePosn &tmp temp0 temp1 temp2 temp3 oLarryDiceFirstDie)
		(= temp1 0)
		(= temp2 0)
		(while
		(and (not temp1) (< temp2 (oLarryDice size:)))
			(= temp3 0)
			(= oLarryDiceFirstDie (oLarryDice firstDie:))
			(= temp0 0)
			(while (< temp0 (oLarryDice size:))
				(if
					(and
						(== (oLarryDiceFirstDie x?) [local0 temp2])
						(== (oLarryDiceFirstDie y?) [local5 temp2])
					)
					(= temp3 1)
					(break)
				)
				(= oLarryDiceFirstDie (oLarryDice nextDie:))
				(++ temp0)
			)
			(if (not temp3)
				(= temp1 1)
				(self x: [local0 temp2] y: [local5 temp1])
			else
				(++ temp2)
			)
		)
	)
)

(class Dice of Set
	(properties
		scratch 0
		elements 0
		size 0
		nextNode 0
		curNode 0
		oDiscards 0
		oShowing 0
	)
	
	(method (init)
		(= oDiscards (Set new:))
		(= oShowing (Set new:))
	)
	
	(method (dispose)
		(oDiscards dispose:)
		(oShowing dispose:)
		(super dispose: &rest)
	)
	
	(method (roll &tmp temp0 temp1)
		(cond 
			(
				(and
					(self isMemberOf: oLarryDice)
					(oLarryDice newShows:)
				)
				(= temp0
					(- (oLarryDice size:) ((oLarryDice oShowing?) size:))
				)
				(gOSound1 playSound: (+ -14335 (- 5 temp0)))
			)
			((self isMemberOf: oLarryDice)
				(gOSound1 playSound: -14335)
				(= temp1 (Random 1 100))
				(cond 
					((not (talkers isEmpty:)) 0)
					((and (not local53) (== (Random 1 10) 3))
						(= local53 1)
						(= local59 1)
						(poFountain setScript: soRollBit)
					)
					((and (< 5 temp1) (< temp1 21)) (messager say: 0 0 5 1 0 local50))
					((and (< 35 temp1) (< temp1 51)) (messager say: 0 0 5 2 0 local50))
					((and (< 70 temp1) (< temp1 86)) (messager say: 0 0 5 3 0 local50))
				)
			)
		)
		(self eachElementDo: #rollDie)
	)
	
	(method (giveOneUp &tmp diceLastDie temp1)
		(= diceLastDie (self lastDie:))
		(if (self isMemberOf: oLarryDice)
			(diceLastDie x: [local10 (- 5 size)])
		else
			(diceLastDie x: (+ [local10 (- 5 size)] 189))
		)
		(diceLastDie y: local15 bDiscarded: 1 bShowing: 0)
		(gOSound1 playSound: -14318)
		(oDiscards addToFront: diceLastDie)
		(self delete: diceLastDie)
	)
	
	(method (firstDie)
		(List 8 (= curNode (self first:)))
	)
	
	(method (nextDie)
		(return
			(if (!= curNode (self last:))
				(return (List 8 (= curNode (self next: curNode))))
			else
				(return 0)
			)
		)
	)
	
	(method (lastDie)
		(List 8 (self last:))
	)
	
	(method (stillRolling &tmp diceFirstDie temp1 temp2)
		(= temp2 0)
		(= temp1 0)
		(= diceFirstDie (self firstDie:))
		(while (and (< temp1 size) (not temp2))
			(if (diceFirstDie bRolling?) (= temp2 1))
			(++ temp1)
			(= diceFirstDie (self nextDie:))
		)
		(return temp2)
	)
	
	(method (newShows &tmp temp0 diceFirstDie temp2)
		(= temp0 0)
		(= diceFirstDie (self firstDie:))
		(= temp2 0)
		(while (< temp2 size)
			(if
				(and
					(diceFirstDie bShowing?)
					(not (diceFirstDie bShowed?))
				)
				(++ temp0)
			)
			(= diceFirstDie (self nextDie:))
			(++ temp2)
		)
		(return temp0)
	)
	
	(method (reposition &tmp diceFirstDie temp1 temp2 temp3)
		(= diceFirstDie (self firstDie:))
		(= temp1 0)
		(= temp2 0)
		(= temp3 0)
		(while (< temp1 size)
			(if (diceFirstDie bShowing?)
				(diceFirstDie
					x:
						(if (self isMemberOf: oLarryDice)
							[local16 temp3]
						else
							[local24 temp3]
						)
					y:
						(if (self isMemberOf: oLarryDice)
							[local20 temp3]
						else
							[local28 temp3]
						)
				)
				(++ temp3)
			else
				(diceFirstDie
					x:
						(if (self isMemberOf: oLarryDice)
							[local0 temp2]
						else
							(+ [local0 temp2] 192)
						)
					y: [local5 temp2]
				)
				(++ temp2)
			)
			(= diceFirstDie (self nextDie:))
			(++ temp1)
		)
		(if (self isMemberOf: oBabeDice) (= local47 temp2))
	)
	
	(method (reset)
		((self oShowing?) release:)
		(self
			eachElementDo: #bShowing 0
			eachElementDo: #bShowed 0
			reposition:
		)
	)
)

(class BetDie of View
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
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
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
		nValue 2
		bImAHog 0
	)
	
	(method (handleEvent event &tmp eventType temp1)
		(= eventType (event type?))
		(= temp1 (self onMe: event))
		(cond 
			(bImAHog
				(self x: (- mouseX 11) y: (- mouseY 11))
				(if (== eventType evMOUSERELEASE) (self stopHogging:))
				(event claimed: 1)
			)
			((and temp1 (== eventType evMOUSEBUTTON))
				(= local60 (= bImAHog 1))
				(gOEventHandler registerEventHog: self)
				(self setPri: 600)
				(oBetDieUp hide:)
				(oBetDieDown hide:)
				(event claimed: 1)
			)
			(else (super handleEvent: event))
		)
		(event claimed?)
	)
	
	(method (stopHogging &tmp temp0 temp1)
		(= local60 (= bImAHog 0))
		(gOEventHandler unregisterEventHog: self)
		(= temp0 0)
		(= temp1 0)
		(while (< temp1 (local42 size:))
			(if
				((local42 at: temp1)
					onMe: (+ (self x?) 11) (+ (self y?) 11)
				)
				(= temp0 (local42 at: temp1))
				(break)
			)
			(++ temp1)
		)
		(if temp0
			(temp0 doVerb:)
		else
			((local42 at: theNSlotNum) doVerb:)
		)
		(self setPri: 500)
		(oBetDieUp show:)
		(oBetDieDown show:)
	)
	
	(method (posn param1)
		(super posn: param1 416)
		(oBetDieUp posn: (+ x 5) (- y 7))
		(oBetDieDown posn: (+ x 5) (+ y 23))
		(self updateArrows:)
	)
	
	(method (update param1)
		(switch param1
			(1
				(self loop: 4 cel: 4 nValue: 1)
				(= theNLastValue_2 1)
				(oDiceRadioGroup eachElementDo: #setButton 0)
				(self updateArrows:)
				(gOSound1 playSound: -14318)
			)
			(2
				(self loop: 5 cel: 2 nValue: 2)
				(= theNLastValue_2 2)
				(oDie2Button internalDoSelect:)
			)
			(3
				(self loop: 6 cel: 2 nValue: 3)
				(= theNLastValue_2 3)
				(oDie3Button internalDoSelect:)
			)
			(4
				(self loop: 7 cel: 2 nValue: 4)
				(= theNLastValue_2 4)
				(oDie4Button internalDoSelect:)
			)
			(5
				(self loop: 8 cel: 2 nValue: 5)
				(= theNLastValue_2 5)
				(oDie5Button internalDoSelect:)
			)
			(6
				(self loop: 9 cel: 2 nValue: 6)
				(= theNLastValue_2 6)
				(oDie6Button internalDoSelect:)
			)
		)
	)
	
	(method (updateArrows)
		(if
		(or (> theNLastValue_2 5) (== theNLastValue_2 1))
			(oBetDieUp cel: 3)
		else
			(oBetDieUp cel: 1)
		)
		(foTopHalf
			nsLeft: ((local42 at: theNSlotNum) nsLeft?)
			nsRight: ((local42 at: theNSlotNum) nsRight?)
		)
		(if (> theNLastValue_2 2)
			(oBetDieDown cel: 0)
		else
			(oBetDieDown cel: 2)
		)
		(foBottomHalf
			nsLeft: ((local42 at: theNSlotNum) nsLeft?)
			nsRight: ((local42 at: theNSlotNum) nsRight?)
		)
	)
)

(class BetSlot of Feature
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
		nSlotNum 0
		nNumDiceBet 0
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (doVerb)
		(= theNSlotNum nSlotNum)
		(= theNNumDiceBet nNumDiceBet)
		(oBetDie posn: nsLeft)
		(cond 
			((OneOf theNSlotNum 1 4 7 10 13) (oBetDie update: 1))
			((== theNLastValue_2 1) (oBetDie update: 2))
			(else (gOSound1 playSound: -14318))
		)
	)
	
	(method (onMe)
		(return
			(if (foBottomHalf onMe: mouseX mouseY)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
)

(class AIBabe of Prop
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
		view -1
		loop 0
		cel 0
		bitmap 0
		yStep 2
		signal $5021
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
		cycleSpeed 7
		script 0
		cycler 0
		timer 0
		detailLevel 0
		scaler 0
		code 0
		oDieCounts 0
		nLastBetType 0
		nLastValue 0
		nLastQuant 0
	)
	
	(method (init)
		(= oDieCounts (IntArray new: 6))
		(super init: &rest)
	)
	
	(method (dispose)
		(oDieCounts dispose:)
		(super dispose:)
	)
	
	(method (takeTurn)
		(self countDice:)
		(cond 
			(local41 (self make1stBet:))
			(
				(or
					(and
						(== nLastBetType 1)
						(== nLastValue theNLastValue_2)
					)
					(self notPossible:)
					(and (== theNLastValue_2 6) (== theNNumDiceBet 10))
				)
				(self challenge:)
			)
			((self betterBet:))
			((self canIncrease:))
			((or (self goodBet:) (> (Random 1 10) 7)) (self increaseBet:))
			(else (self challenge:))
		)
	)
	
	(method (make1stBet &tmp temp0 temp1 temp2 temp3)
		(if (not (oDieCounts at: 0))
			(= temp1 35)
			(= temp2 33)
			(= temp3 33)
			(if 1 (++ temp2))
		else
			(= temp1 20)
			(= temp2 40)
			(= temp3 40)
		)
		(cond 
			((< (= temp0 (Random 1 100)) temp1) (self bluff:))
			((< temp0 (+ temp1 temp2)) (self playOdds:))
			(else (self playSafe:))
		)
	)
	
	(method (challenge param1)
		(self setScript: soChallenge 0 (if argc param1 else 0))
	)
	
	(method (bluff &tmp theNLastValue theNLastQuant temp2)
		(if (>= (oDieCounts at: 0) 2)
			(self playOdds:)
			(return)
		)
		(= theNLastValue 5)
		(while (oDieCounts at: theNLastValue)
			(-- theNLastValue)
		)
		(++ theNLastValue)
		(= theNLastQuant
			(+
				(/
					(-
						(= temp2 (+ (oBabeDice size:) (oLarryDice size:)))
						(oDieCounts at: 0)
					)
					3
				)
				(oDieCounts at: 0)
			)
		)
		(if (< (Random 1 10) 6) (-- theNLastQuant))
		(self updateBoard: theNLastValue theNLastQuant)
		(= local41 0)
		(oChallengeButton enable:)
		(= nLastBetType 1)
		(= nLastValue theNLastValue)
		(= nLastQuant theNLastQuant)
		(self firstBetTalk:)
	)
	
	(method (playOdds &tmp temp0 theNLastQuant theNLastValue theTheNLastQuant)
		(= theTheNLastQuant 0)
		(= temp0 0)
		(while (< temp0 6)
			(if (>= (oDieCounts at: temp0) theTheNLastQuant)
				(= theNLastValue (+ temp0 1))
				(= theTheNLastQuant (oDieCounts at: temp0))
			)
			(++ temp0)
		)
		(if (== theNLastValue 1)
			(if
				(>=
					(+
						(- (oLarryDice size:) theTheNLastQuant)
						(oBabeDice size:)
					)
					6
				)
				(= theNLastQuant (+ theTheNLastQuant 1))
			else
				(= theNLastQuant theTheNLastQuant)
			)
		else
			(= theNLastQuant
				(+
					theTheNLastQuant
					(oDieCounts at: 0)
					(/ (oLarryDice size:) 3)
				)
			)
		)
		(if
			(and
				(!= theNLastValue 1)
				(> (oLarryDice size:) 3)
				(>=
					(+
						(- (oLarryDice size:) theTheNLastQuant)
						(oBabeDice size:)
					)
					3
				)
				(> (Random 1 10) 8)
			)
			(++ theNLastQuant)
		)
		(if
			(and
				(!= theNLastValue 1)
				(>= (oDieCounts at: 0) 2)
				(<= theNLastQuant (* (oDieCounts at: 0) 2))
			)
			(= theNLastValue 1)
			(= theNLastQuant (oDieCounts at: 0))
		)
		(self updateBoard: theNLastValue theNLastQuant)
		(= local41 0)
		(oChallengeButton enable:)
		(= nLastBetType 2)
		(= nLastValue theNLastValue)
		(= nLastQuant theNLastQuant)
		(self firstBetTalk:)
	)
	
	(method (playSafe &tmp temp0 theNLastQuant theNLastValue theTheNLastQuant)
		(= theTheNLastQuant 0)
		(= temp0 0)
		(while (< temp0 6)
			(if (>= (oDieCounts at: temp0) theTheNLastQuant)
				(= theNLastValue (+ temp0 1))
				(= theTheNLastQuant (oDieCounts at: temp0))
			)
			(++ temp0)
		)
		(if (== theNLastValue 1)
			(= theNLastQuant theTheNLastQuant)
		else
			(= theNLastQuant
				(+ theTheNLastQuant (oDieCounts at: 0))
			)
		)
		(if
			(and
				(!= theNLastValue 1)
				(>= (oDieCounts at: 0) 2)
				(<= theNLastQuant (* (oDieCounts at: 0) 2))
			)
			(= theNLastValue 1)
			(= theNLastQuant (oDieCounts at: 0))
		)
		(self updateBoard: theNLastValue theNLastQuant)
		(= local41 0)
		(oChallengeButton enable:)
		(= nLastBetType 3)
		(= nLastValue theNLastValue)
		(= nLastQuant theNLastQuant)
		(self firstBetTalk:)
	)
	
	(method (firstBetTalk &tmp temp0)
		(= temp0 (Random 1 100))
		(cond 
			(local58 (messager say: 0 0 30 7 soPlayGame local50) (= local58 0))
			((or (not (talkers isEmpty:)) (< temp0 26)) (soPlayGame cue:))
			((< temp0 51) (messager say: 0 0 30 7 soPlayGame local50))
			((< temp0 76) (messager say: 0 0 27 3 soPlayGame local50))
			(else (messager say: 0 0 27 1 soPlayGame local50))
		)
	)
	
	(method (laterBetTalk &tmp temp0)
		(= temp0 (Random 1 100))
		(cond 
			((not (talkers isEmpty:)) (soPlayGame cue:))
			(
			(and (> local57 1) (not local54) (== (Random 1 10) 3))
				(= local54 1)
				(messager sayRange: 0 0 28 5 7 soPlayGame local50)
			)
			((and (> local57 1) (< temp0 76))
				(= local54 1)
				(messager say: 0 0 28 (Random 3 4) soPlayGame local50)
			)
			((< temp0 51) (messager say: 0 0 27 2 soPlayGame local50))
			((< temp0 76) (messager say: 0 0 27 4 soPlayGame local50))
			(else (messager say: 0 0 27 5 soPlayGame local50))
		)
	)
	
	(method (canIncrease &tmp theTheNNumDiceBet temp1 temp2 temp3 temp4)
		(if
			(not
				(if (oDieCounts at: 0)
				else
					(oDieCounts at: (- theNLastValue_2 1))
				)
			)
			(return 0)
		)
		(= theTheNNumDiceBet theNNumDiceBet)
		(++ theTheNNumDiceBet)
		(if (== theNLastValue_2 1)
			(= temp1 (oDieCounts at: 0))
		else
			(= temp1
				(+
					(oDieCounts at: 0)
					(oDieCounts at: (- theNLastValue_2 1))
				)
			)
		)
		(= temp3
			(+
				temp1
				(= temp2
					(-
						(= temp4 (+ (oLarryDice size:) (oBabeDice size:)))
						(+
							(oDieCounts at: 0)
							(oDieCounts at: 1)
							(oDieCounts at: 2)
							(oDieCounts at: 3)
							(oDieCounts at: 4)
							(oDieCounts at: 5)
						)
					)
				)
			)
		)
		(cond 
			((> temp2 3)
				(if (> theTheNNumDiceBet (Max 0 (- temp3 2)))
					(return 0)
				)
			)
			((> temp2 1)
				(if (> theTheNNumDiceBet (Max 0 (- temp3 1)))
					(return 0)
				)
			)
			((> theTheNNumDiceBet temp3) (return 0))
		)
		(= theNNumDiceBet theTheNNumDiceBet)
		(= theNSlotNum
			(self getSlot: theNLastValue_2 theNNumDiceBet)
		)
		(return
			(if (self isValidBet:)
				(self updateBoard: theNLastValue_2 theNNumDiceBet)
				(= local41 0)
				(oChallengeButton enable:)
				(= nLastBetType 2)
				(= nLastValue theNLastValue_2)
				(= nLastQuant theNNumDiceBet)
				(self laterBetTalk:)
				(return 1)
			else
				(= theNSlotNum theTheNSlotNum)
				(= theNLastValue_2 theTheNLastValue_2)
				(= theNNumDiceBet theTheNNumDiceBet_2)
				(return 0)
			)
		)
	)
	
	(method (betterBet &tmp temp0 theTheNNumDiceBet theTheNLastValue_2_2 temp3)
		(= temp3 0)
		(= temp0 0)
		(while (< temp0 6)
			(if (>= (oDieCounts at: temp0) temp3)
				(= theTheNLastValue_2_2 (+ temp0 1))
				(= temp3 (oDieCounts at: temp0))
			)
			(++ temp0)
		)
		(if (== theTheNLastValue_2_2 1)
			(= theTheNNumDiceBet (+ temp3 (/ (self numHidden:) 3)))
		else
			(= theTheNNumDiceBet
				(+ temp3 (oDieCounts at: 0) (/ (self numHidden:) 3))
			)
		)
		(if
		(and (> (self numHidden:) 3) (> (Random 1 10) 8))
			(++ theTheNNumDiceBet)
		)
		(= theNLastValue_2 theTheNLastValue_2_2)
		(= theNNumDiceBet theTheNNumDiceBet)
		(= theNSlotNum
			(self getSlot: theNLastValue_2 theNNumDiceBet)
		)
		(return
			(if (self isValidBet:)
				(self updateBoard: theNLastValue_2 theNNumDiceBet)
				(= local41 0)
				(oChallengeButton enable:)
				(= nLastBetType 2)
				(= nLastValue theNLastValue_2)
				(= nLastQuant theNNumDiceBet)
				(self laterBetTalk:)
				(return 1)
			else
				(= theNSlotNum theTheNSlotNum)
				(= theNLastValue_2 theTheNLastValue_2)
				(= theNNumDiceBet theTheNNumDiceBet_2)
				(return 0)
			)
		)
	)
	
	(method (increaseBet &tmp temp0 theNLastValue temp2 temp3 temp4)
		(= temp4 0)
		(= temp2 0)
		(= temp0 0)
		(while (< temp0 6)
			(if (>= (oDieCounts at: temp0) temp2)
				(= theNLastValue (+ temp0 1))
				(= temp2 (oDieCounts at: temp0))
			)
			(++ temp0)
		)
		(cond 
			(
				(and
					(!= theNLastValue_2 1)
					(> theNLastValue theNLastValue_2)
				)
				(= theNLastValue_2 theNLastValue)
			)
			(
			(and (== theNSlotNum 13) (not (self goodBet:))) (= temp4 1))
			((< theNSlotNum 14)
				(++ theNSlotNum)
				(cond 
					((OneOf theNSlotNum 1 4 7 10 13)
						(if (oDieCounts at: 0)
							(= theNLastValue_2 1)
						else
							(++ theNSlotNum)
							(= theNLastValue_2 theNLastValue)
						)
					)
					(
					(or (== theTheNLastValue_2 1) (== theNLastValue 1)) (= theNLastValue_2 6))
					(else (= theNLastValue_2 theNLastValue))
				)
				(= theNNumDiceBet
					((local42 at: theNSlotNum) nNumDiceBet?)
				)
			)
			(else (++ theNLastValue_2))
		)
		(if
			(or
				(>
					theNNumDiceBet
					(= temp3 (+ (oLarryDice size:) (oBabeDice size:)))
				)
				temp4
			)
			(= theNLastValue_2 theTheNLastValue_2)
			(= theNNumDiceBet theTheNNumDiceBet_2)
			(self challenge:)
		else
			(self updateBoard: theNLastValue_2 theNNumDiceBet)
			(= local41 0)
			(oChallengeButton enable:)
			(= nLastBetType 2)
			(= nLastValue theNLastValue)
			(= nLastQuant theNNumDiceBet)
			(self laterBetTalk:)
		)
	)
	
	(method (countDice param1 &tmp temp0 oBabeDiceFirstDie)
		(oDieCounts fill: 0 6 0)
		(= oBabeDiceFirstDie (oBabeDice firstDie:))
		(= temp0 0)
		(while (< temp0 (oBabeDice size:))
			(oDieCounts
				at:
					(- (oBabeDiceFirstDie nValue?) 1)
					(+
						(oDieCounts at: (- (oBabeDiceFirstDie nValue?) 1))
						1
					)
			)
			(= oBabeDiceFirstDie (oBabeDice nextDie:))
			(++ temp0)
		)
		(= oBabeDiceFirstDie (oLarryDice firstDie:))
		(= temp0 0)
		(while (< temp0 (oLarryDice size:))
			(if
			(or (oBabeDiceFirstDie bShowing?) (and argc param1))
				(oDieCounts
					at:
						(- (oBabeDiceFirstDie nValue?) 1)
						(+
							(oDieCounts at: (- (oBabeDiceFirstDie nValue?) 1))
							1
						)
				)
			)
			(= oBabeDiceFirstDie (oLarryDice nextDie:))
			(++ temp0)
		)
	)
	
	(method (updateBoard theTheNLastValue_2_2 theTheNNumDiceBet)
		(= theTheNLastValue_2
			(= theNLastValue_2 theTheNLastValue_2_2)
		)
		(= theTheNNumDiceBet_2
			(= theNNumDiceBet theTheNNumDiceBet)
		)
		(= theTheNSlotNum
			(= theNSlotNum
				(self getSlot: theTheNLastValue_2_2 theTheNNumDiceBet)
			)
		)
		(oBetDie
			posn: ((local42 at: theNSlotNum) nsLeft?)
			update: theNLastValue_2
		)
		(= local57 0)
		(if (self shouldShow: theNLastValue_2)
			(self showNRoll: theNLastValue_2)
		)
	)
	
	(method (reset)
		(= theNSlotNum 0)
		(= theTheNSlotNum 0)
		(= theNLastValue_2 2)
		(= theTheNLastValue_2 2)
		(= theNNumDiceBet 1)
		(= theTheNNumDiceBet_2 1)
		(= nLastQuant (= nLastValue (= nLastBetType 0)))
		(= local41 1)
		(oChallengeButton disable:)
		(oBetDie posn: ((local42 at: 0) nsLeft?) update: 2)
		(cup show:)
		(oLarryDice reset: roll:)
		(oBabeDice reset: roll:)
	)
	
	(method (shouldShow param1 &tmp temp0 oBabeDiceFirstDie temp2 temp3 temp4 temp5)
		(if local41 (return 0))
		(= temp2 0)
		(= temp3 1)
		(= temp5 0)
		(= oBabeDiceFirstDie (oBabeDice firstDie:))
		(= temp0 0)
		(while (< temp0 (oBabeDice size:))
			(if (not (oBabeDiceFirstDie bShowing?)) (++ temp2))
			(if (OneOf (oBabeDiceFirstDie nValue?) 1 param1)
				(= temp5 1)
			else
				(= temp3 0)
			)
			(= oBabeDiceFirstDie (oBabeDice nextDie:))
			(++ temp0)
		)
		(if (not temp5) (return 0))
		(if (< temp2 2) (return 0))
		(if temp3 (return 0))
		(if (== theNLastValue_2 1)
			(= temp4
				(+
					(oDieCounts at: (- param1 1))
					(/ (self numHidden:) 3)
				)
			)
		else
			(= temp4
				(+
					(oDieCounts at: (- param1 1))
					(oDieCounts at: 0)
					(/ (self numHidden:) 3)
				)
			)
		)
		(return
			(if
			(and (< temp4 theNNumDiceBet) (!= nLastBetType 1))
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (showNRoll param1 &tmp temp0 oBabeDiceFirstDie temp2)
		(= oBabeDiceFirstDie (oBabeDice firstDie:))
		(= temp0 0)
		(while (< temp0 (oBabeDice size:))
			(if
				(and
					(not (oBabeDiceFirstDie bShowing?))
					(not local41)
					(OneOf (oBabeDiceFirstDie nValue?) 1 param1)
					(<
						(+ ((oBabeDice oShowing?) size:) 1)
						(oBabeDice size:)
					)
				)
				(++ local57)
				(oBabeDiceFirstDie showIt:)
			)
			(= oBabeDiceFirstDie (oBabeDice nextDie:))
			(++ temp0)
		)
		(oBabeDice reposition:)
		(= temp2
			(- (oBabeDice size:) ((oBabeDice oShowing?) size:))
		)
		(gOSound1 playSound: (+ -14335 (- 5 temp2)))
		(oBabeDice roll:)
	)
	
	(method (numHidden &tmp temp0 temp1 temp2)
		(= temp0 (+ (oLarryDice size:) (oBabeDice size:)))
		(= temp2
			(-
				temp0
				(= temp1
					(+
						(oDieCounts at: 0)
						(oDieCounts at: 1)
						(oDieCounts at: 2)
						(oDieCounts at: 3)
						(oDieCounts at: 4)
						(oDieCounts at: 5)
					)
				)
			)
		)
	)
	
	(method (goodBet)
		(return
			(if
				(or
					(and
						(== theNLastValue_2 1)
						(>= (oDieCounts at: 0) theNNumDiceBet)
					)
					(and
						(!= theNLastValue_2 1)
						(>=
							(+
								(oDieCounts at: 0)
								(oDieCounts at: (- theNLastValue_2 1))
							)
							theNNumDiceBet
						)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (notPossible &tmp temp0)
		(if (== theNLastValue_2 1)
			(= temp0 (oDieCounts at: 0))
		else
			(= temp0
				(+
					(oDieCounts at: (- theNLastValue_2 1))
					(oDieCounts at: 0)
				)
			)
		)
		(return
			(if (> theNNumDiceBet (+ (self numHidden:) temp0))
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (isValidBet)
		(return
			(if
				(or
					(> theNSlotNum theTheNSlotNum)
					(and
						(== theNSlotNum theTheNSlotNum)
						(> theNLastValue_2 theTheNLastValue_2)
						(!= theTheNLastValue_2 1)
					)
				)
				(return 1)
			else
				(return 0)
			)
		)
	)
	
	(method (getSlot param1 param2)
		(return
			(switch param2
				(1
					(if (== param1 1) (return 1) else (return 0))
				)
				(2
					(if (== param1 1) (return 4) else (return 2))
				)
				(3
					(if (== param1 1) (return 7) else (return 3))
				)
				(4
					(if (== param1 1) (return 10) else (return 5))
				)
				(5
					(if (== param1 1) (return 13) else (return 6))
				)
				(6 (return 8))
				(7 (return 9))
				(8 (return 11))
				(9 (return 12))
				(10 (return 14))
			)
		)
	)
)

(instance soIntro of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 3))
			(1
				(messager say: 0 0 3 0 self 2)
			)
			(2
				(messager say: 0 0 5 (Random 1 3) self local50)
				(oLarryDice roll:)
				(oBabeDice roll:)
			)
			(3
				(curRoom setScript: soPlayGame)
				(self dispose:)
			)
		)
	)
)

(instance soPlayGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				0
				(theGame handsOff:)
				(= ticks (Random 50 110))
			)
			(1
				1
				(cond 
					((not (talkers isEmpty:)) (-- state) (= ticks 60))
					((oLarryDice stillRolling:) (-- state) (= ticks 10))
					(else (= cycles 1))
				)
			)
			(2 2 (oBabe takeTurn:))
			(3
				3
				(if (and local41 local43)
					(self changeState: 0)
				else
					(if (not local59) (theGame handsOn:))
					(oDelayTimer setReal: oDelayTimer 30)
				)
			)
			(4
				4
				(oDelayTimer dispose: delete:)
				(if (and local41 (not local43))
					(self changeState: 3)
				else
					(self changeState: 0)
				)
			)
		)
	)
)

(instance soDieRoll of Script
	(properties)
	
	(method (changeState newState &tmp temp0)
		(switch (= state newState)
			(0
				(client bRolling: 1)
				(= register 0)
				(= cycles 3)
			)
			(1
				(if
					(and
						(== (= temp0 (Random 4 9)) (client loop?))
						(== (++ temp0) 10)
					)
					(= temp0 4)
				)
				(client loop: temp0)
				(= ticks (= scratch (Random 15 35)))
			)
			(2
				(= register (+ register scratch))
				(if (< register 90)
					(self changeState: 1)
				else
					(= cycles 1)
				)
			)
			(3
				(client nValue: (Random 1 6))
				(client loop: (+ (client nValue?) 3) bRolling: 0)
				(if (not (oLarryDice stillRolling:)) (gOSound1 stop:))
				(self dispose:)
			)
		)
	)
)

(instance soChallenge of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= temp1 (Random 1 100))
				(cond 
					(register
						(cond 
							((<= temp1 20) (= temp2 7))
							((<= temp1 35) (= temp2 9))
							((<= temp1 45) (= temp2 10))
							((<= temp1 55) (= temp2 11))
							((<= temp1 65) (= temp2 12))
							((<= temp1 75) (= temp2 13))
							((<= temp1 85) (= temp2 14))
							(else (= temp2 8))
						)
					)
					((<= temp1 30) (= temp2 1))
					((<= temp1 40) (= temp2 3))
					((<= temp1 50) (= temp2 4))
					((<= temp1 60) (= temp2 5))
					((<= temp1 70) (= temp2 6))
					(else (= temp2 2))
				)
				(messager say: 0 0 7 temp2 self local50)
			)
			(1
				(cup hide:)
				(oBabe countDice: 1)
				(= ticks 70)
			)
			(2
				(if
					(or
						(and
							(== theNLastValue_2 1)
							(>= ((oBabe oDieCounts?) at: 0) theNNumDiceBet)
						)
						(and
							(!= theNLastValue_2 1)
							(>=
								(+
									((oBabe oDieCounts?) at: 0)
									((oBabe oDieCounts?) at: (- theNLastValue_2 1))
								)
								theNNumDiceBet
							)
						)
					)
					(= temp0 1)
				else
					(= temp0 0)
				)
				(cond 
					((and register temp0)
						(if
						(and (< 20 (Random 1 100)) (< (Random 1 100) 71))
							(messager say: 0 0 11 (Random 6 10) self local50)
						else
							(messager say: 0 0 10 (Random 1 3) self local50)
						)
						(= local43 1)
					)
					((and register (not temp0))
						(if
						(and (< 40 (Random 1 100)) (< (Random 1 100) 91))
							(messager say: 0 0 13 (Random 7 12) self local50)
						else
							(messager say: 0 0 11 (Random 1 5) self local50)
						)
						(= local43 0)
					)
					(temp0
						(if
						(and (< 10 (Random 1 100)) (< (Random 1 100) 61))
							(if
							(and (< 35 (Random 1 100)) (< (Random 1 100) 86))
								(messager say: 0 0 10 (Random 4 6) self local50)
							else
								(messager say: 0 0 8 (Random 4 6) self local50)
							)
						else
							(messager say: 0 0 11 (Random 1 5) self local50)
						)
						(= local43 0)
					)
					(else
						(if
						(and (< 0 (Random 1 100)) (< (Random 1 100) 51))
							(messager say: 0 0 11 (Random 6 10) self local50)
						else
							(messager say: 0 0 13 (Random 1 6) self local50)
						)
						(= local43 1)
					)
				)
			)
			(3
				(if local43
					(oLarryDice giveOneUp:)
				else
					(oBabeDice giveOneUp:)
				)
				(if
					(and
						(== local48 5)
						(< local32 100)
						(not (oLarryDice size:))
					)
					(curRoom setScript: soLarryLoses)
				else
					(= cycles 1)
				)
			)
			(4
				(client setScript: soChoiceDialog 0 register)
			)
		)
	)
)

(instance soBabeSellsClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local49
					(0
						(messager say: 0 0 19 0 self local50)
					)
					(1
						(Load rsVIEW 13001)
						(messager say: 0 0 21 0 self local50)
					)
					(2
						(Load rsVIEW 13002)
						(messager say: 0 0 22 0 self local50)
					)
					(3
						(Load rsVIEW 13003)
						(messager say: 0 0 23 0 self local50)
					)
					(4
						(Load rsVIEW 13004)
						(messager say: 0 0 24 0 self local50)
					)
					(5
						(Load rsVIEW 13005)
						(messager sayRange: 0 0 12 1 4 self local50)
					)
				)
			)
			(1
				(switch local49
					(0
						(voBabeBottom show:)
						(oBabe setCycle: CT 14 1 self)
					)
					(1
						(oBabe view: 13001 cel: 0 setCycle: CT 6 1 self)
					)
					(2
						(oBabe view: 13002 cel: 0 setCycle: CT 7 1 self)
						(voBabeBottom hide:)
					)
					(3
						(voBabeBottom view: 13007 show:)
						(oBabe view: 13003 cel: 0 setCycle: CT 12 1 self)
					)
					(4
						(oBabe view: 13004 cel: 0 setCycle: CT 12 1 self)
					)
					(5
						(oBabe view: 13005 cel: 0 setCycle: CT 37 1 self)
						(voBabeBottom dispose:)
						(voDrink dispose:)
					)
				)
				(poDewmiEyes view: (oBabe view?) hide:)
			)
			(2
				(switch local49
					(0
						(gOSound1 playSound: -14323)
						(oBabe setCycle: End self)
					)
					(1
						(gOSound1 playSound: -14325)
						(oBabe setCycle: End self)
					)
					(2
						(gOSound1 playSound: -14324)
						(oBabe setCycle: End self)
					)
					(3
						(gOSound1 playSound: -14321)
						(oBabe setCycle: End self)
					)
					(4
						(gOSound1 playSound: -14321)
						(oBabe setCycle: End self)
					)
					(5
						(gOSound1 playSound: -14324)
						(voDrink posn: 328 451)
						(oBabe setCycle: End self)
					)
				)
			)
			(3
				(poDewmiEyes show:)
				(if (== local49 5)
					(messager sayRange: 0 0 12 5 99 self local50)
				else
					(= cycles 1)
				)
			)
			(4
				(++ local49)
				(= local33 (+ local33 local45))
				(if (< local34 local45)
					(Prints {WARNING: There's not enough money in the pot.})
				else
					(= local34 (- local34 local45))
				)
				(= local45 (+ local45 50))
				(voBabeMoney draw: {$%d} local33)
				(voPotMoney draw: {Pot: $%d} local34)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soLarrySellsClothes of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(switch local48
					(0
						(messager say: 0 0 14 0 self local50)
					)
					(1
						(messager say: 0 0 15 0 self local50)
					)
					(2
						(messager say: 0 0 16 0 self local50)
					)
					(3
						(messager say: 0 0 17 0 self local50)
					)
					(4
						(messager sayRange: 0 0 18 1 2 self local50)
					)
				)
			)
			(1
				(switch local48
					(0 (poLarry loop: 0 cel: 0))
					(1 (poLarry loop: 0 cel: 0))
					(2 (poLarry loop: 0 cel: 0))
					(3 (poLarry loop: 0 cel: 0))
					(4 (poLarry loop: 0 cel: 0))
				)
				(= cycles 1)
			)
			(2
				(voLarryBust hide:)
				(switch local48
					(0
						(poLarry setCycle: CT 5 1 self)
					)
					(1
						(poLarry setCycle: CT 15 1 self)
					)
					(2
						(poLarry setCycle: CT 6 1 self)
					)
					(3
						(poLarry setCycle: CT 5 1 self)
					)
					(4
						(poLarry setCycle: CT 7 1 self)
					)
				)
			)
			(3
				(switch local48
					(0
						(gOSound1 playSound: -14325)
						(poLarry setCycle: End self)
					)
					(1
						(gOSound1 playSound: -14325)
						(poLarry setCycle: End self)
					)
					(2
						(gOSound1 playSound: -14324)
						(poLarry setCycle: End self)
					)
					(3
						(gOSound1 playSound: -14322)
						(poLarry setCycle: End self)
					)
					(4
						(gOSound1 playSound: -14324)
						(poLarry setCycle: End self)
					)
				)
			)
			(4
				(switch local48
					(0
						(poLarry view: 13101 loop: 2 cel: 0)
					)
					(1
						(poLarry view: 13102 loop: 2 cel: 0)
					)
					(2
						(poLarry view: 13103 loop: 2 cel: 0)
					)
					(3
						(poLarry view: 13104 loop: 2 cel: 0)
					)
					(4
						(poLarry view: 13105 loop: 2 cel: 0)
					)
				)
				(voLarryBust view: (poLarry view?) show:)
				(if (== local48 4)
					(FrameOut)
					(messager sayRange: 0 0 18 3 4 self local50)
				else
					(= cycles 1)
				)
			)
			(5
				(++ local48)
				(= local32 (+ local32 local44))
				(if (< local34 local44)
					(Prints {WARNING: There's not enough money in the pot.})
				else
					(= local34 (- local34 local44))
				)
				(= local44 (+ local44 50))
				(voLarryMoney draw: {$%d} local32)
				(voPotMoney draw: {Pot: $%d} local34)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soBuyDie of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(cond 
					(register
						(cond 
							(local55 (= cycles 1))
							((and (not local52) (== (Random 1 10) 3)) (= local52 1) (messager say: 0 0 26 0 self local50))
							((== (oLarryDice size:) 4) (messager say: 0 0 25 1 self local50))
							(else (messager say: 0 0 25 2 self local50))
						)
					)
					(local55 (= cycles 1))
					((== (oBabeDice size:) 4) (messager say: 0 0 25 3 self local50))
					(else (messager say: 0 0 25 4 self local50))
				)
			)
			(1
				(= local55 1)
				(if register
					(((oLarryDice oDiscards?) at: 0)
						bDiscarded: 0
						x: [local0 (oLarryDice size:)]
						y: [local5 (oLarryDice size:)]
					)
					(oLarryDice add: ((oLarryDice oDiscards?) at: 0))
					((oLarryDice oDiscards?)
						delete: ((oLarryDice oDiscards?) at: 0)
					)
					(= local34 (+ local34 100))
					(= local32 (- local32 100))
					(voLarryMoney draw: {$%d} local32)
					(voPotMoney draw: {Pot: $%d} local34)
					(= cycles 6)
				else
					(((oBabeDice oDiscards?) at: 0)
						bDiscarded: 0
						x: (+ [local0 (oBabeDice size:)] 192)
						y: [local5 (oBabeDice size:)]
					)
					(oBabeDice add: ((oBabeDice oDiscards?) at: 0))
					((oBabeDice oDiscards?)
						delete: ((oBabeDice oDiscards?) at: 0)
					)
					(= local34 (+ local34 100))
					(= local33 (- local33 100))
					(voBabeMoney draw: {$%d} local33)
					(voPotMoney draw: {Pot: $%d} local34)
					(= cycles 6)
				)
				(gOSound1 playSound: -14318)
			)
			(2
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance soChoiceDialog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(= local55 0)
				(= scratch 1)
				(= cycles 3)
			)
			(1
				(if
					(and
						(or
							(== local48 5)
							(< local34 local44)
							(> local32 99)
							(== (oLarryDice size:) 5)
						)
						(or (== (oLarryDice size:) 5) (< local32 100))
					)
					(self changeState: 4)
				else
					(switch
						(= scratch
							(localproc_5c27
								(MakeMessageText 1 0 2 1 2)
								(MakeMessageText 1 0 25 1 2)
								(MakeMessageText 1 0 35 1 2)
							)
						)
						(1
							(if (< local32 100)
								(= local51 1)
								(self setScript: (soLarrySellsClothes new:) self)
							else
								(self setScript: (soBuyDie new:) self 1)
							)
						)
						(else 
							(= scratch 0)
							(= cycles 1)
						)
					)
				)
			)
			(2
				(if local51
					(= local51 0)
					(self setScript: (soBuyDie new:) self 1)
				else
					(= cycles 1)
				)
			)
			(3
				(if scratch (self changeState: 1) else (= cycles 1))
			)
			(4
				(= local55 0)
				(self setScript: soBabeActions self)
			)
			(5
				(if (not (oLarryDice size:))
					(messager say: 0 0 30 2 self local50)
				else
					(oBabe reset:)
					(soPlayGame cue:)
					(self dispose:)
				)
			)
			(6 (self changeState: 1))
		)
	)
)

(instance soBabeActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if
					(or
						(< (oBabeDice size:) 5)
						(and
							(< local32 100)
							(== local48 5)
							(< (oBabeDice size:) 5)
						)
					)
					(cond 
						((>= local33 100) (self setScript: (soBuyDie new:) self))
						(
						(and (== (+ local49 1) 7) (== (oBabeDice size:) 0)) (= global329 1) (curRoom newRoom: 200))
						((== (+ local49 1) 7) (self dispose:))
						((< local34 local45)
							(Prints
								{There's not enough money in the pot. You're going to have to sell some clothes so I can buy some dice. Either that or forfeit.}
							)
							(oBabe setScript: soChoiceDialog)
						)
						(else (self setScript: soBabeSellsClothes self))
					)
				else
					(self dispose:)
				)
			)
			(1 (self changeState: 0))
		)
	)
)

(instance soLarryLoses of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= cycles 30)
			)
			(1
				(messager say: 0 0 29 0 self local50)
			)
			(2
				(= global329 0)
				(curRoom newRoom: 200)
				(self dispose:)
			)
		)
	)
)

(instance soRollBit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(messager say: 0 0 6 0 self local50)
			)
			(1
				(theGame handsOn:)
				(= local59 0)
				(self dispose:)
			)
		)
	)
)

(instance soLookAtUser of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= register (poLarry loop?))
				(poLarry loop: 3 cel: 0)
				(= cycles 1)
			)
			(1
				(voLarryBust hide:)
				(poLarry setCycle: End self)
			)
			(2 (= ticks 70))
			(3 (poLarry setCycle: Beg self))
			(4
				(voLarryBust show:)
				(poLarry loop: register)
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance oLarryDice of Dice
	(properties)
)

(instance oBabeDice of Dice
	(properties)
)

(instance oBetDie of BetDie
	(properties
		x 210
		y 416
		priority 500
		fixPriority 1
		view 9000
		loop 5
		cel 2
	)
	
	(method (init)
		(super init: &rest)
		(oBetDieUp init:)
		(foTopHalf init:)
		(oBetDieDown init:)
		(foBottomHalf init:)
	)
)

(instance oBetDieUp of View
	(properties
		x 215
		y 409
		priority 500
		fixPriority 1
		view 9000
		loop 12
		cel 1
	)
)

(instance oBetDieDown of View
	(properties
		x 215
		y 439
		priority 500
		fixPriority 1
		view 9000
		loop 12
	)
)

(instance cup of View
	(properties
		x 196
		y 125
		priority 500
		fixPriority 1
		view 9000
		loop 10
	)
)

(instance oBetButton of PushButton
	(properties
		x 207
		y 379
		priority 500
		fixPriority 1
		view 9000
		loop 1
	)
	
	(method (doSelect &tmp temp0 oLarryDiceNewShows)
		(if (or local41 (oBabe isValidBet:))
			(= theTheNSlotNum theNSlotNum)
			(= theTheNLastValue_2 theNLastValue_2)
			(= theTheNNumDiceBet_2 theNNumDiceBet)
			(if (= oLarryDiceNewShows (oLarryDice newShows:))
				(oLarryDice reposition: roll:)
				((oLarryDice oShowing?) eachElementDo: #bShowed 1)
			)
			(= temp0 (Random 1 100))
			(cond 
				((not (talkers isEmpty:)) (= local41 0) (soPlayGame cue:))
				(local41
					(= local41 0)
					(oChallengeButton enable:)
					(cond 
						((and (< 34 temp0) (< temp0 67)) (messager say: 0 0 27 6 soPlayGame local50))
						((and (< 66 temp0) (< temp0 101)) (messager say: 0 0 27 7 soPlayGame local50))
						(else (soPlayGame cue:))
					)
				)
				((and (> oLarryDiceNewShows 1) (< temp0 51))
					(if (< (Random 1 100) 70)
						(messager say: 0 0 28 1 soPlayGame local50)
					else
						(messager say: 0 0 28 2 soPlayGame local50)
					)
				)
				((and (== oLarryDiceNewShows 1) (< temp0 51)) (messager say: 0 0 28 2 soPlayGame local50))
				(else (messager say: 0 0 27 (Random 6 10) soPlayGame local50))
			)
		else
			(messager say: 0 0 30 1 0 local50)
			(oBetDie
				posn: ((local42 at: theTheNSlotNum) nsLeft?)
				update: theTheNLastValue_2
			)
			(= theNSlotNum theTheNSlotNum)
			(= theNLastValue_2 theTheNLastValue_2)
			(= theNNumDiceBet theTheNNumDiceBet_2)
		)
	)
)

(instance oChallengeButton of PushButton
	(properties
		x 268
		y 379
		priority 500
		fixPriority 1
		view 9000
		loop 2
	)
	
	(method (doSelect)
		(= theNSlotNum theTheNSlotNum)
		(= theNLastValue_2 theTheNLastValue_2)
		(= theNNumDiceBet theTheNNumDiceBet_2)
		(oBabe challenge: 1)
	)
)

(instance oHowToPlayButton of PushButton
	(properties
		x 380
		y 379
		priority 500
		fixPriority 1
		view 9000
		loop 11
	)
	
	(method (doSelect &tmp [temp0 3])
		((ScriptID 64000 5) doSelect:)
	)
)

(instance oDie2Button of RadioButton
	(properties
		x 597
		y 386
		priority 500
		fixPriority 1
		view 9000
		loop 5
		bButtonDown 1
	)
	
	(method (onMe)
		(return
			(if (== (oBetDie nValue?) 1)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
	
	(method (doSelect)
		(= theNLastValue_2 2)
		(oBetDie loop: 5 cel: 2 nValue: 2 updateArrows:)
		(gOSound1 playSound: -14318)
	)
)

(instance oDie3Button of RadioButton
	(properties
		x 567
		y 416
		priority 500
		fixPriority 1
		view 9000
		loop 6
	)
	
	(method (onMe)
		(return
			(if (== (oBetDie nValue?) 1)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
	
	(method (doSelect)
		(= theNLastValue_2 3)
		(oBetDie loop: 6 cel: 2 nValue: 3 updateArrows:)
		(gOSound1 playSound: -14318)
	)
)

(instance oDie4Button of RadioButton
	(properties
		x 597
		y 416
		priority 500
		fixPriority 1
		view 9000
		loop 7
	)
	
	(method (onMe)
		(return
			(if (== (oBetDie nValue?) 1)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
	
	(method (doSelect)
		(= theNLastValue_2 4)
		(oBetDie loop: 7 cel: 2 nValue: 4 updateArrows:)
		(gOSound1 playSound: -14318)
	)
)

(instance oDie5Button of RadioButton
	(properties
		x 567
		y 446
		priority 500
		fixPriority 1
		view 9000
		loop 8
	)
	
	(method (onMe)
		(return
			(if (== (oBetDie nValue?) 1)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
	
	(method (doSelect)
		(= theNLastValue_2 5)
		(oBetDie loop: 8 cel: 2 nValue: 5 updateArrows:)
		(gOSound1 playSound: -14318)
	)
)

(instance oDie6Button of RadioButton
	(properties
		x 597
		y 446
		priority 500
		fixPriority 1
		view 9000
		loop 9
	)
	
	(method (onMe)
		(return
			(if (== (oBetDie nValue?) 1)
				(return 0)
			else
				(super onMe: &rest)
			)
		)
	)
	
	(method (doSelect)
		(= theNLastValue_2 6)
		(oBetDie loop: 9 cel: 2 nValue: 6 updateArrows:)
		(gOSound1 playSound: -14318)
	)
)

(instance oDiceRadioGroup of RadioGroup
	(properties)
	
	(method (init)
		(super init: &rest)
		(self
			add: oDie2Button oDie3Button oDie4Button oDie5Button oDie6Button
		)
	)
)

(instance voLarryMoney of TextView
	(properties
		x 46
		y 179
		priority 480
		fixPriority 1
		fore 0
		width 50
	)
	
	(method (init)
		(= back skip)
		(super init: &rest)
		(self draw: {$%d} local32)
	)
)

(instance voBabeMoney of TextView
	(properties
		x 238
		y 179
		priority 480
		fixPriority 1
		fore 0
		width 50
	)
	
	(method (init)
		(= back skip)
		(super init: &rest)
		(self draw: {$%d} local33)
	)
)

(instance voPotMoney of TextView
	(properties
		x 518
		y 392
		fore 0
		width 90
	)
	
	(method (init)
		(= back skip)
		(super init: &rest)
		(self draw: {Pot: $%d} local34)
	)
)

(instance oBabe of AIBabe
	(properties
		x 369
		y 469
		priority 190
		fixPriority 1
		view 13000
	)
	
	(method (init)
		(super init: &rest)
		(voBabeBottom init: hide:)
		(poDewmiEyes init:)
		(voDrink init:)
		(foDewmiBreasts init:)
	)
)

(instance voBabeBottom of View
	(properties
		view 13006
	)
	
	(method (init)
		(= x (oBabe x?))
		(= y (oBabe y?))
		(super init: &rest)
		(self setPri: (- (oBabe priority?) 1))
	)
)

(instance voDrink of View
	(properties
		x 403
		y 334
		view 13008
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: (+ (oBabe priority?) 5))
	)
)

(instance poLarry of Prop
	(properties
		x 369
		y 469
		priority 300
		fixPriority 1
		view 13100
		loop 2
	)
)

(instance poFountain of Prop
	(properties
		x 507
		y 118
		priority 75
		fixPriority 1
		view 13050
	)
	
	(method (init)
		(super init: &rest)
		(self setCycle: Fwd)
	)
)

(instance foShowArea of Feature
	(properties
		x 159
		y 67
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 132 119 132 15 186 15 186 119
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance foRollArea of Feature
	(properties
		x 64
		y 69
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init:
						17
						39
						36
						20
						55
						14
						76
						14
						94
						20
						109
						35
						119
						57
						118
						82
						110
						99
						90
						117
						70
						124
						46
						123
						26
						108
						10
						83
						13
						42
						35
						23
						36
						21
					yourself:
				)
		)
		(super init: &rest)
	)
)

(instance foTopHalf of Feature
	(properties
		nsLeft 210
		nsTop 408
		nsRight 232
		nsBottom 415
		x 404
		y 505
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (handleEvent event)
		(return
			(if
			(or (> theNLastValue_2 5) (== theNLastValue_2 1))
				(event claimed: 0)
				(return 0)
			else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb)
		(if (< theNLastValue_2 6)
			(oBetDie update: (+ (oBetDie nValue?) 1))
		)
	)
)

(instance foBottomHalf of Feature
	(properties
		nsLeft 700
		nsTop 438
		nsRight 710
		nsBottom 445
		x 404
		y 505
	)
	
	(method (init)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (handleEvent event)
		(return
			(if (< theNLastValue_2 3)
				(event claimed: 0)
				(return 0)
			else
				(super handleEvent: event)
			)
		)
	)
	
	(method (doVerb)
		(if (> theNLastValue_2 2)
			(oBetDie update: (- (oBetDie nValue?) 1))
		)
	)
)

(instance foSlot1 of BetSlot
	(properties
		nsLeft 210
		nsTop 414
		nsRight 232
		nsBottom 468
		nNumDiceBet 1
	)
)

(instance foSlot1A of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 1
		nNumDiceBet 1
	)
	
	(method (init)
		(= nsLeft (+ (foSlot1 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot2 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 2
		nNumDiceBet 2
	)
	
	(method (init)
		(= nsLeft (+ (foSlot1A nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot3 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 3
		nNumDiceBet 3
	)
	
	(method (init)
		(= nsLeft (+ (foSlot2 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot2A of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 4
		nNumDiceBet 2
	)
	
	(method (init)
		(= nsLeft (+ (foSlot3 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot4 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 5
		nNumDiceBet 4
	)
	
	(method (init)
		(= nsLeft (+ (foSlot2A nsLeft?) 23))
		(= nsRight (+ (foSlot2A nsRight?) 23))
		(super init: &rest)
	)
)

(instance foSlot5 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 6
		nNumDiceBet 5
	)
	
	(method (init)
		(= nsLeft (+ (foSlot4 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot3A of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 7
		nNumDiceBet 3
	)
	
	(method (init)
		(= nsLeft (+ (foSlot5 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot6 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 8
		nNumDiceBet 6
	)
	
	(method (init)
		(= nsLeft (+ (foSlot3A nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot7 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 9
		nNumDiceBet 7
	)
	
	(method (init)
		(= nsLeft (+ (foSlot6 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot4A of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 10
		nNumDiceBet 4
	)
	
	(method (init)
		(= nsLeft (+ (foSlot7 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot8 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 11
		nNumDiceBet 8
	)
	
	(method (init)
		(= nsLeft (+ (foSlot4A nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot9 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 12
		nNumDiceBet 9
	)
	
	(method (init)
		(= nsLeft (+ (foSlot8 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot5A of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 13
		nNumDiceBet 5
	)
	
	(method (init)
		(= nsLeft (+ (foSlot9 nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foSlot10 of BetSlot
	(properties
		nsTop 414
		nsBottom 468
		nSlotNum 14
		nNumDiceBet 10
	)
	
	(method (init)
		(= nsLeft (+ (foSlot5A nsRight?) 1))
		(= nsRight (+ nsLeft 22))
		(super init: &rest)
	)
)

(instance foDewmiBreasts of Feature
	(properties
		x 375
		y 480
	)
	
	(method (init)
		(self
			setPolygon:
				((Polygon new:)
					type: 0
					init: 329 216 344 196 420 196 422 224 398 249 339 238
					yourself:
				)
		)
		(super init: &rest)
		(self forceCursor: (ScriptID 64006 0))
	)
	
	(method (doVerb)
		(poFountain setScript: soLookAtUser)
	)
)

(instance toLarry of Talker
	(properties
		priority 300
	)
	
	(method (init)
		(= view (poLarry view?))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(poLarry hide:)
		(super init: poLarryMouth)
	)
	
	(method (dispose)
		(poLarry show:)
		(super dispose: &rest)
	)
)

(instance poLarryMouth of Prop
	(properties
		loop 2
	)
	
	(method (init)
		(= view (toLarry view?))
		(= x (toLarry x?))
		(= y (toLarry y?))
		(super init: &rest)
		(self setPri: 301)
	)
)

(instance voLarryBust of View
	(properties
		loop 1
	)
	
	(method (init)
		(= view (poLarry view?))
		(= x (poLarry x?))
		(= y (poLarry y?))
		(super init: &rest)
		(self setPri: 302)
	)
)

(instance toDewmi of Talker
	(properties)
	
	(method (init)
		(= view (oBabe view?))
		(= x (oBabe x?))
		(= y (oBabe y?))
		(= priority (+ (oBabe priority?) 1))
		(oBabe hide:)
		(super init: oBabeMouth voDewmiBust)
	)
	
	(method (dispose)
		(oBabe show:)
		(super dispose: &rest)
	)
)

(instance oBabeMouth of Prop
	(properties
		loop 2
	)
	
	(method (init)
		(= view (toDewmi view?))
		(= x (toDewmi x?))
		(= y (toDewmi y?))
		(= priority (+ (voDewmiBust priority?) 1))
		(super init: &rest)
	)
)

(instance voDewmiBust of View
	(properties
		loop 1
	)
	
	(method (init)
		(= view (toDewmi view?))
		(= x (toDewmi x?))
		(= y (toDewmi y?))
		(= priority (+ (toDewmi priority?) 1))
		(super init: &rest)
	)
)

(instance poDewmiEyes of Prop
	(properties
		loop 3
		cycleSpeed 4
	)
	
	(method (init)
		(= view (oBabe view?))
		(= x (oBabe x?))
		(= y (oBabe y?))
		(super init: &rest)
		(self
			setPri: (+ (oBabe priority?) 5)
			setCycle: Blink 100
		)
	)
)

(instance oDelayTimer of Timer
	(properties)
	
	(method (dispose)
		(= local56 0)
		(super dispose: &rest)
	)
	
	(method (cue)
		(cond 
			(local56 (= local56 0) (self setReal: self 30))
			(
			(or (oBabe script?) (not (talkers isEmpty:)) local60) (self setReal: self 10))
			(else
				(= local56 1)
				(if local41
					(if (< (Random 1 100) 51)
						(messager say: 0 0 4 (Random 1 2) self local50)
					else
						(messager say: 0 0 4 (Random 4 5) self local50)
					)
				else
					(messager say: 0 0 4 (Random 1 5) self local50)
				)
			)
		)
	)
)
