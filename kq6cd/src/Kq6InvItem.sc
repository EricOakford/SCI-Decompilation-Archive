;;; Sierra Script 1.0 - (do not remove this comment)
(script# 907)
(include sci.sh)
(use Main)
(use KQ6Print)
(use Kq6Window)
(use Kq6Procs)
(use Print)
(use IconBar)
(use Tutorial)
(use Window)
(use Game)
(use Invent)
(use User)
(use System)

(public
	KqInv 0
	pageCode 1
)

(local
	local0
	local1
	local2
)
(class Kq6InvItem of InvItem
	(properties
		view 970
		loop 0
		cel 0
		nsLeft 0
		nsTop 0
		nsRight 0
		nsBottom 0
		state $0000
		cursor 999
		type $4000
		message 0
		modifiers $0000
		signal $0000
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 907
		helpVerb 0
		owner 0
		script 0
		value 0
		realOwner 0
		cursorView 0
		cursorLoop 0
		cursorCel 0
		hideInv 0
	)
	
	(method (show)
		(DrawCel
			view
			loop
			cel
			nsLeft
			nsTop
			-1
			0
			(if
			(and global169 (Platform 6) (not (Platform 5)))
				(inventory empty?)
			else
				0
			)
		)
	)
	
	(method (select)
		(genericCursor
			view: cursorView
			loop: cursorLoop
			cel: cursorCel
		)
		(theGame setCursor: genericCursor)
		(super select: &rest)
	)
	
	(method (highlight)
		(if (or (not global169) (Platform 5))
			(super highlight: &rest)
		)
	)
	
	(method (onMe event &tmp temp0)
		(if (& signal $0004) (return 0))
		(= temp0
			(if (and global169 (Platform 6))
				(not (Platform 5))
			else
				0
			)
		)
		(return
			(if
			(>= (event x?) (if temp0 (/ nsLeft 2) else nsLeft))
				(if
				(>= (event y?) (if temp0 (/ nsTop 2) else nsTop))
					(if
					(<= (event x?) (if temp0 (/ nsRight 2) else nsRight))
						(<= (event y?) (if temp0 (/ nsBottom 2) else nsBottom))
					)
				)
			else
				0
			)
		)
	)
	
	(method (doVerb theVerb &tmp temp0 [temp1 100])
		(if (OneOf theVerb 28 13 12)
			(messager say: 0 theVerb 0 0 0 modNum)
		else
			(if hideInv (inventory hide: 1))
			(if (not modNum) (= modNum curRoomNum))
			(cond 
				(
					(and
						msgType
						(Message msgGET modNum noun theVerb 0 1)
						(!= (messager findTalker: (Message msgNEXT 0)) -1)
					)
					(if hideInv
						(messager say: noun theVerb 0 0 self modNum)
					else
						(messager say: noun theVerb 0 0 0 modNum)
					)
				)
				(
					(and
						(inventory curIcon?)
						(!= (inventory curIcon?) self)
						((inventory curIcon?) isKindOf: InvItem)
						(= temp0 (Message msgGET modNum noun theVerb 0 1))
						(!= (messager findTalker: temp0) -1)
					)
					((inventory curIcon?) doVerb: message)
				)
				(else
					(switch theVerb
						(5
							(messager say: 64 5 0 0 0 modNum)
						)
						(2
							(messager say: 64 2 0 0 0 modNum)
						)
						(else 
							(messager say: noun 0 0 0 0 modNum)
						)
					)
				)
			)
		)
	)
	
	(method (checkPage &tmp temp0 temp1)
		(= temp1 0)
		(= temp0 0)
		(while (< temp0 52)
			(if (== ((inventory at: temp0) owner?) local1)
				(= temp1 1)
				(= temp0 53)
			)
			(++ temp0)
		)
		(inventory hide:)
		(if temp1
			(if (> temp0 52)
				(inventory show: local1 selectIcon: invSelect)
			else
				(invPrevious select:)
			)
		)
	)
	
	(method (setCursor form showIt theX)
		(= cursorView form)
		(= cursorLoop showIt)
		(= cursorCel theX)
		(= cursor genericCursor)
	)
	
	(method (cue param1)
		(if (and argc param1)
			(pageCode init: local1)
		else
			(if (not cuees) (= cuees (Set new:)))
			(cuees
				add: ((Cue new:) cuee: self cuer: self register: 1 yourself:)
			)
		)
	)
)

(instance pageCode of Code
	(properties)
	
	(method (init param1 &tmp temp0 temp1)
		(= local1 param1)
		(inventory selectIcon: invSelect window: invWin)
		(invSelect loop: 4 message: -1)
		(= local0 0)
		(= temp0 0)
		(while (< temp0 52)
			(if
			(== ((= temp1 (inventory at: temp0)) owner?) local1)
				(temp1 realOwner: (temp1 owner?) owner: 0)
				(if (< (++ local0) 13) (temp1 owner: local1))
			)
			(++ temp0)
		)
		(if local0 (inventory addAfter: invTalk invMore))
		(inventory delete: invPrevious showSelf: local1)
	)
)

(instance KqInv of Inventory
	(properties
		normalHeading {Alexander is carrying}
		empty {nothing.}
	)
	
	(method (init)
		(= inventory self)
		(self
			add:
				(map setCursor: 990 1 7 yourself:)
				(boringBook setCursor: 990 0 0 yourself:)
				(brick setCursor: 990 0 1 yourself:)
				(brush setCursor: 990 0 2 yourself:)
				(hair setCursor: 990 0 15 yourself:)
				(clothes setCursor: 990 0 4 yourself:)
				(coal setCursor: 990 0 5 yourself:)
				(deadMansCoin setCursor: 990 0 6 yourself:)
				(dagger setCursor: 990 0 7 yourself:)
				(coin setCursor: 990 0 8 yourself:)
				(egg setCursor: 990 0 10 yourself:)
				(skull setCursor: 990 3 4 yourself:)
				(feather setCursor: 990 0 11 yourself:)
				(flower setCursor: 990 0 12 yourself:)
				(flute setCursor: 990 0 13 yourself:)
				(gauntlet setCursor: 990 0 14 yourself:)
				(cassimaHair setCursor: 990 0 3 yourself:)
				(handkerchief setCursor: 990 1 0 yourself:)
				(holeInTheWall setCursor: 990 1 1 yourself:)
				(huntersLamp setCursor: 990 1 2 yourself:)
				(letter setCursor: 990 1 3 yourself:)
				(lettuce setCursor: 990 1 4 yourself:)
				(milk setCursor: 990 1 8 yourself:)
				(mint setCursor: 990 1 9 yourself:)
				(mirror setCursor: 990 1 10 yourself:)
				(newLamp setCursor: 990 1 11 yourself:)
				(nail setCursor: 990 2 0 yourself:)
				(nightingale setCursor: 990 2 1 yourself:)
				(ticket setCursor: 990 3 7 yourself:)
				(participle setCursor: 990 2 3 yourself:)
				(pearl setCursor: 990 2 4 yourself:)
				(peppermint setCursor: 990 2 5 yourself:)
				(note setCursor: 990 2 6 yourself:)
				(potion setCursor: 990 2 7 yourself:)
				(rabbitFoot setCursor: 990 2 8 yourself:)
				(ribbon setCursor: 990 2 10 yourself:)
				(riddleBook setCursor: 990 2 11 yourself:)
				(ring setCursor: 990 2 12 yourself:)
				(rose setCursor: 990 2 13 yourself:)
				(royalRing setCursor: 990 2 14 yourself:)
				(sacredWater setCursor: 990 2 15 yourself:)
				(scarf setCursor: 990 3 0 yourself:)
				(scythe setCursor: 990 3 1 yourself:)
				(shield setCursor: 990 3 2 yourself:)
				(skeletonKey setCursor: 990 3 3 yourself:)
				(spellBook setCursor: 990 3 5 yourself:)
				(teaCup setCursor: 990 3 6 yourself:)
				(poem setCursor: 990 2 2 yourself:)
				(tinderBox setCursor: 990 3 8 yourself:)
				(tomato setCursor: 990 3 9 yourself:)
				(sentence setCursor: 990 3 10 yourself:)
				(ink setCursor: 990 3 12 yourself:)
				(invLook cursor: cInvLook yourself:)
				(invHand cursor: cInvHand yourself:)
				(invSelect cursor: normalCursor yourself:)
				(invTalk cursor: cInvTalk yourself:)
				ok
			eachElementDo: #highlightColor 0
			eachElementDo: #lowlightColor (invWin back?)
			eachElementDo: #init
			window: invWin
			selectIcon: invSelect
			okButton: ok
		)
		(super init: &rest)
	)
	
	(method (showSelf param1)
		(sounds pause:)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
		(if (theIconBar height?) (theIconBar hide:))
		(if (not window) (= window (SysWindow new:)))
		(if (window window?) (window dispose:) (= window 0))
		(if (not okButton)
			(= okButton (NodeValue (self first:)))
		)
		(if (not (& state $2000)) (= curIcon 0))
		(= state (& state $dfff))
		(if (self show: (if argc param1 else ego))
			(self doit:)
		else
			(theGame setCursor: ((theIconBar curIcon?) cursor?))
		)
	)
	
	(method (hide param1 &tmp temp0 temp1 temp2 theIconBarCurInvIcon)
		(if (& state $0020)
			(sounds pause: 0)
			(= state (& state $ffdf))
		)
		(if
		(and global169 (Platform 6) (not (Platform 5)) empty)
			(Graph grRESTORE_BOX empty)
		)
		(if window (window dispose:))
		(if
		(and (IsObject curIcon) (curIcon isKindOf: InvItem))
			(if (not (theIconBar curInvIcon?))
				(theIconBar enable: (theIconBar useIconItem?))
			)
			(theIconBar
				curIcon:
					((theIconBar useIconItem?)
						cursor: (curIcon cursor?)
						yourself:
					)
				curInvIcon: curIcon
			)
			(if
				(and
					(theGame isHandsOn?)
					(= temp2 ((theIconBar curIcon?) cursor?))
				)
				(theGame setCursor: temp2)
			)
		else
			(if
				(IsObject
					(= theIconBarCurInvIcon (theIconBar curInvIcon?))
				)
				(genericCursor
					view: (theIconBarCurInvIcon cursorView?)
					loop: (theIconBarCurInvIcon cursorLoop?)
					cel: (theIconBarCurInvIcon cursorCel?)
				)
			)
			(if
				(and
					(theGame isHandsOn?)
					(= temp2 ((theIconBar curIcon?) cursor?))
				)
				(theGame setCursor: temp2)
			)
		)
		(if (or (not argc) (not param1))
			(= temp0 0)
			(while (< temp0 52)
				(if ((= temp1 (self at: temp0)) realOwner?)
					(temp1 owner: (temp1 realOwner?))
					(temp1 realOwner: 0)
				)
				(++ temp0)
			)
			(self delete: invMore invPrevious)
		)
	)
	
	(method (highlight theHighlightedIcon param2 &tmp temp0 temp1)
		(if (not (& (theHighlightedIcon signal?) $0004))
			(if (IsObject highlightedIcon)
				(highlightedIcon highlight: 0)
			)
			((= highlightedIcon theHighlightedIcon) highlight: 1)
		)
		(= temp1
			(if (and global169 (Platform 6))
				(not (Platform 5))
			else
				0
			)
		)
		(if (and (>= argc 2) param2)
			(theGame
				setCursor:
					theCursor
					1
					(if temp1
						(+
							(/ (theHighlightedIcon nsLeft?) 2)
							(/
								(-
									(theHighlightedIcon nsRight?)
									(theHighlightedIcon nsLeft?)
								)
								4
							)
						)
					else
						(+
							(theHighlightedIcon nsLeft?)
							(/
								(-
									(theHighlightedIcon nsRight?)
									(theHighlightedIcon nsLeft?)
								)
								2
							)
						)
					)
					(if temp1
						(- (+ 0 (/ (theHighlightedIcon nsBottom?) 2)) 8)
					else
						(- (theHighlightedIcon nsBottom?) 3)
					)
			)
		)
	)
	
	(method (advanceCurIcon &tmp theCurIcon)
		(= theCurIcon curIcon)
		(repeat
			(while
				((= theCurIcon
					(self at: (mod (+ (self indexOf: theCurIcon) 1) size))
				)
					isKindOf: InvItem
				)
			)
			(breakif (!= (theCurIcon cursor?) -1))
		)
		(= curIcon theCurIcon)
		(if (== curIcon helpIconItem)
			(curIcon signal: (| (curIcon signal?) $0010))
		else
			(curIcon signal: (& (curIcon signal?) $ffef))
		)
		(theGame setCursor: (curIcon cursor?))
	)
	
	(method (drawInvWindow param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 invFirst temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 invWindow [temp22 50] temp72)
		(= temp0
			(= temp1 (= temp2 (= temp3 (= temp4 (= temp5 0)))))
		)
		(= temp72
			(if (and global169 (Platform 6))
				(not (Platform 5))
			else
				0
			)
		)
		(= invFirst (self first:))
		(while invFirst
			(if
			((= temp9 (NodeValue invFirst)) isKindOf: InvItem)
				(if (temp9 ownedBy: param1)
					(if temp72 (temp9 view: 972) else (temp9 view: 970))
					(temp9 signal: (& (temp9 signal?) $fffb))
					(++ temp0)
					(= temp6
						(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
					)
					(= temp7
						(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
					)
					(if (> temp6 temp2) (= temp2 temp6))
					(if (> temp7 temp1) (= temp1 temp7))
				else
					(temp9 signal: (| (temp9 signal?) $0004))
				)
			else
				(if temp72 (temp9 view: 912) else (temp9 view: 901))
				(++ temp3)
				(= temp5
					(+
						temp5
						(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
					)
				)
				(if
					(>
						(= temp7
							(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
						)
						temp4
					)
					(= temp4 temp7)
				)
			)
			(= invFirst (self next: invFirst))
		)
		(if (not temp0)
			(if (& msgType $0002)
				(theMusic number: 12 loop: 1 play:)
			else
				(Print addTextF: {%s %s} normalHeading empty init:)
			)
			(return 0)
		)
		(if temp72
			(if
			(> (* (= local2 (/ temp5 temp2)) temp2) temp5)
				(-- local2)
			)
			(cond 
				((<= temp0 local2) (= temp16 1) (= local2 temp0))
				(
				(< (* local2 (= temp16 (/ temp0 local2))) temp0) (++ temp16))
			)
			(= temp2 (/ temp2 2))
			(= temp1 (/ temp1 2))
			(= temp10 (+ 4 (= temp5 (/ temp5 2))))
		else
			(if (> (* (= temp16 (Sqrt temp0)) temp16) temp0)
				(-- temp16)
			)
			(if (> temp16 3) (= temp16 3))
			(if
			(< (* temp16 (= local2 (/ temp0 temp16))) temp0)
				(++ local2)
			)
			(= temp10 (Max (+ 4 temp5) (* local2 (+ 4 temp2))))
		)
		(= temp12
			(/ (- 190 (= temp11 (+ (* temp16 temp1) 4))) 2)
		)
		(= temp13 (/ (- 320 temp10) 2))
		(= temp14 (+ temp12 temp11))
		(= temp15 (+ temp13 temp10))
		(if (= invWindow (self window?))
			(invWindow
				top: temp12
				left: temp13
				right: temp15
				bottom: temp14
				open:
			)
			(if temp72
				(= empty
					(Graph
						15
						(+ (invWindow top?) 10)
						(invWindow left?)
						(+ (invWindow bottom?) 10)
						(invWindow right?)
					)
				)
			else
				(= empty 0)
			)
		)
		(if temp72
			(= temp2 (* temp2 2))
			(= temp1 (* temp1 2))
			(= temp5 (* temp5 2))
		)
		(= temp20 local2)
		(if temp0
			(= temp18
				(+
					2
					(if (invWindow respondsTo: #yOffset)
						(invWindow yOffset?)
					else
						0
					)
				)
			)
			(= temp19
				(= temp17
					(+
						4
						(if (invWindow respondsTo: #xOffset)
							(invWindow xOffset?)
						else
							0
						)
					)
				)
			)
			(= invFirst (self first:))
			(while invFirst
				(if
					(and
						(not
							(& ((= temp9 (NodeValue invFirst)) signal?) $0004)
						)
						(temp9 isKindOf: InvItem)
					)
					(if (not (& (temp9 signal?) $0080))
						(temp9
							nsLeft:
								(+
									temp17
									(/
										(-
											temp2
											(= temp6
												(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
											)
										)
										2
									)
								)
							nsTop:
								(+
									temp18
									(/
										(-
											temp1
											(= temp7
												(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
											)
										)
										2
									)
								)
						)
						(temp9
							nsRight: (+ (temp9 nsLeft?) temp6)
							nsBottom: (+ (temp9 nsTop?) temp7)
						)
						(if (-- temp20)
							(= temp17 (+ temp17 temp2))
						else
							(= temp20 local2)
							(= temp18 (+ temp18 temp1))
							(= temp17 temp19)
						)
					else
						(= temp17 (temp9 nsLeft?))
						(= temp18 (temp9 nsTop?))
					)
					(temp9 show:)
					(if (== temp9 param2) (temp9 highlight:))
				)
				(= invFirst (self next: invFirst))
			)
		)
		(if temp72
			(= temp17
				(/
					(-
						(* (- (invWindow right?) (invWindow left?)) 2)
						temp5
					)
					2
				)
			)
			(= temp11
				(* (- (invWindow bottom?) (invWindow top?)) 2)
			)
		else
			(= temp17
				(/
					(- (- (invWindow right?) (invWindow left?)) temp5)
					2
				)
			)
			(= temp11 (- (invWindow bottom?) (invWindow top?)))
		)
		(= temp18 32767)
		(= invFirst (self first:))
		(while invFirst
			(if
			(not ((= temp9 (NodeValue invFirst)) isKindOf: InvItem))
				(temp9 nsTop: 0)
				(= temp6
					(CelWide (temp9 view?) (temp9 loop?) (temp9 cel?))
				)
				(= temp7
					(CelHigh (temp9 view?) (temp9 loop?) (temp9 cel?))
				)
				(if (not (& (temp9 signal?) $0080))
					(if (== temp18 32767)
						(= temp18 (- temp11 temp7))
						(if temp72 (= temp18 (+ temp18 10)))
					)
					(temp9
						nsLeft: temp17
						nsTop: temp18
						nsBottom: (+ temp18 temp7)
						nsRight: (+ temp17 temp6)
					)
				)
				(= temp17 (+ (temp9 nsLeft?) temp6))
				(= temp18 (temp9 nsTop?))
				(temp9 signal: (& (temp9 signal?) $fffb) show:)
			)
			(= invFirst (self next: invFirst))
		)
		(return 1)
	)
)

(instance invWin of Kq6Window
	(properties)
	
	(method (open &tmp temp0)
		(= top (- top (= temp0 (/ (CelHigh 901 3 0) 2))))
		(= bottom (+ bottom temp0))
		(super open: &rest)
	)
)

(instance boringBook of Kq6InvItem
	(properties
		message 42
		noun 1
		owner 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(inventory hide:)
				(curRoom setScript: 88)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance brick of Kq6InvItem
	(properties
		cel 1
		message 39
		noun 4
		owner 510
	)
)

(instance brush of Kq6InvItem
	(properties
		cel 2
		message 29
		noun 5
		owner 280
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if state
					(messager say: noun theVerb 2 0 0 modNum)
				else
					(messager say: noun theVerb 1 0 0 modNum)
				)
			)
			(5
				(if state
					(messager say: noun theVerb 2 0 0 modNum)
				else
					(messager say: noun theVerb 1 0 0 modNum)
				)
			)
			(28
				(if state
					(messager say: noun theVerb 2 0 0 modNum)
				else
					(messager say: noun theVerb 1 0 0 modNum)
				)
			)
			(29
				(messager say: noun theVerb 0 0 0 modNum)
			)
			(44 (teaCup doVerb: message))
			(else 
				(if state
					(messager say: noun 0 2 0 0 modNum)
				else
					(messager say: noun 0 1 0 0 modNum)
				)
			)
		)
	)
	
	(method (cue)
		(= loop 3)
		(= cel 13)
		(++ state)
	)
)

(instance hair of Kq6InvItem
	(properties
		cel 15
		message 15
		noun 60
		owner 530
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(51 (skull doVerb: message))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance clothes of Kq6InvItem
	(properties
		cel 4
		message 45
		noun 6
		owner 540
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (and (not (ego has: 4)) (not (Btst 93)))
					(KQ6Print
						font: userFont
						say: 0 noun theVerb 0 0 0 0 modNum
						posn: -1 10
						init:
					)
					(KQ6Print
						font: userFont
						say: 0 noun theVerb 36 0 0 0 modNum
						posn: -1 10
						init:
					)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(5
				(if
				(or (Btst 143) (ego has: 4) (ego has: 16) (Btst 93))
					(messager say: noun theVerb 34 0 0 modNum)
				else
					(inventory hide:)
					(Bset 143)
					(ego get: 4)
					(theGame givePoints: 1)
					(KQ6Print
						font: userFont
						say: 0 noun theVerb 36 0 0 0 modNum
						posn: -1 10
						init:
					)
					(pageCode init: local1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance coal of Kq6InvItem
	(properties
		cel 5
		message 46
		noun 7
		owner 560
	)
)

(instance deadMansCoin of Kq6InvItem
	(properties
		cel 6
		message 7
		noun 9
		owner 430
	)
)

(instance dagger of Kq6InvItem
	(properties
		cel 7
		message 8
		noun 10
		owner 440
	)
)

(instance coin of Kq6InvItem
	(properties
		cel 8
		message 40
		noun 8
		owner 200
	)
)

(instance egg of Kq6InvItem
	(properties
		cel 9
		message 19
		noun 52
		owner 490
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(51 (skull doVerb: message))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skull of Kq6InvItem
	(properties
		loop 3
		cel 4
		message 51
		noun 63
		owner 415
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 18) (= theVerb 15))
		(switch theVerb
			(20
				(if (& state $0004)
					(if (& state $0008)
						(messager say: noun 0 0 0 0 modNum)
					else
						(messager say: noun theVerb 37 0 0 modNum)
					)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(28
				(cond 
					((not state) (messager say: noun theVerb 38 0 0 modNum))
					(
					(and (& state $0004) (& state $0001) (& state $0002))
						(if
						(and (not (curRoom script?)) (== (ego view?) 900))
							(inventory hide:)
							(curRoom setScript: 190)
						else
							(messager say: 7 0 16 0 0 0)
						)
					)
					((and (& state $0004) (& state $fffe)) (messager say: noun theVerb 39 0 0 modNum))
					(
						(or
							(and (& state $0004) (& state $0001))
							(and (& state $0004) (& state $0002))
						)
						(messager say: noun theVerb 41 0 0 modNum)
					)
				)
			)
			(15
				(cond 
					((not state) (messager say: noun 15 38 0 0 modNum))
					((& state $0004)
						(inventory hide:)
						(if (ego has: 4) (ego put: 4) else (ego put: 16))
						(theGame givePoints: 1)
						(if (& state $0001)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 41 0 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 0 0 0 modNum
								posn: -1 10
								init:
							)
						)
						(= state (| state $0002))
						(pageCode init: local1)
					)
				)
			)
			(19
				(cond 
					((not state) (messager say: noun theVerb 38 0 0 modNum))
					((and (& state $0004) (& state $0002))
						(inventory hide:)
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 40 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
						(ego put: 10)
						(theGame givePoints: 1)
						(= state (| state $0001))
						(pageCode init: local1)
					)
					((& state $0004)
						(inventory hide:)
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 39 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
						(ego put: 10)
						(theGame givePoints: 1)
						(= state (| state $0001))
						(pageCode init: local1)
					)
				)
			)
			(1
				(cond 
					((not state) (messager say: noun theVerb 38 0 0 modNum))
					(
					(and (& state $0004) (& state $0001) (& state $0002))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 42 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 41 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 41 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
					((and (& state $0004) (& state $0002))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 40 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
					((and (& state $0004) (& state $0001))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 41 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 41 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 41 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
					((& state $0004)
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 39 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(KQ6Print
								font: userFont
								say: 0 noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (cue)
		(self
			loop: 3
			cel: 4
			setCursor: 990 3 4
			state: (& (self state?) $fff7)
		)
		(if
			(and
				(== (theIconBar curIcon?) (theIconBar useIconItem?))
				(== (theIconBar curInvIcon?) self)
			)
			(cursor loop: 3 cel: 4)
			(theGame setCursor: cursor)
		)
	)
)

(instance feather of Kq6InvItem
	(properties
		cel 11
		message 30
		noun 13
		owner 300
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(44 (teaCup doVerb: message))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flower of Kq6InvItem
	(properties
		cel 12
		message 47
		noun 14
		owner 300
	)
)

(instance flute of Kq6InvItem
	(properties
		cel 13
		message 31
		noun 15
		owner 280
	)
)

(instance gauntlet of Kq6InvItem
	(properties
		cel 14
		message 48
		noun 16
		owner 650
	)
)

(instance cassimaHair of Kq6InvItem
	(properties
		cel 3
		message 18
		noun 59
		owner 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(51
				(skull doVerb: (hair message?))
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance handkerchief of Kq6InvItem
	(properties
		loop 1
		message 50
		noun 18
		owner 680
	)
)

(instance holeInTheWall of Kq6InvItem
	(properties
		loop 1
		cel 1
		message 25
		noun 19
		owner 480
	)
)

(instance huntersLamp of Kq6InvItem
	(properties
		loop 1
		cel 2
		message 43
		noun 23
		owner 520
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(KQ6Print
					font: userFont
					posn: -1 10
					say: 0 noun theVerb 0 0 0 0 modNum
					init:
				)
				(cond 
					((== global161 0) (messager say: noun theVerb 4 0 0 modNum))
					((== global161 1) (messager say: noun theVerb 7 0 0 modNum))
					((== global161 4) (messager say: noun theVerb 6 0 0 modNum))
					((== global161 6) (messager say: noun theVerb 8 0 0 modNum))
					((== global161 5) (messager say: noun theVerb 12 0 0 modNum))
					((== global161 15) (messager say: noun theVerb 10 0 0 modNum))
					((== global161 7) (messager say: noun theVerb 9 0 0 modNum))
					((== global161 2) (messager say: noun theVerb 5 0 0 modNum))
					((== global161 3) (messager say: noun theVerb 11 0 0 modNum))
				)
			)
			(28
				(cond 
					((not (Btst 77)) (messager say: noun theVerb 15 0 0 modNum))
					((== global161 15) (messager say: noun theVerb 10 0 0 modNum))
					((== global161 0) (messager say: noun theVerb 4 0 0 modNum))
					((== global161 7)
						(if
						(and (not (curRoom script?)) (== (ego view?) 900))
							(inventory hide:)
							(curRoom setScript: 190)
						else
							(messager say: 7 0 16 0 0 0)
						)
					)
					(
						(or
							(== global161 (| global161 $0001))
							(== global161 (| global161 $0004))
							(== global161 (| global161 $0002))
						)
						(messager say: noun theVerb 14 0 0 modNum)
					)
				)
			)
			(24
				(cond 
					((not (Btst 77)) (messager say: noun theVerb 15 0 0 modNum))
					((and (Btst 77) (== global161 1)) (messager say: noun theVerb 18 0 0 modNum))
					((and (Btst 77) (== global161 5)) (messager say: noun theVerb 19 0 0 modNum))
					((and (Btst 77) (== global161 0))
						(inventory hide:)
						(ego put: 40)
						(theGame givePoints: 1)
						(= global161 (| global161 $0002))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 16 0 0 0 modNum
							posn: -1 10
							init:
						)
						(inventory curIcon: (inventory selectIcon?))
						(theIconBar disable: 4)
						(pageCode init: local1)
					)
					((and (Btst 77) (== global161 4))
						(inventory hide:)
						(ego put: 40)
						(theGame givePoints: 1)
						(= global161 (| global161 $0002))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 17 0 0 0 modNum
							posn: -1 10
							init:
						)
						(pageCode init: local1)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance letter of Kq6InvItem
	(properties
		loop 1
		cel 3
		message 61
		noun 29
		owner 780
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(inventory hide:)
				(curRoom setScript: 101 self)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lettuce of Kq6InvItem
	(properties
		loop 1
		cel 4
		message 52
		noun 20
		owner 480
	)
	
	(method (cue)
		(switch state
			(0
				(= noun 21)
				(= message 53)
				(self setCursor: 990 1 (= cel 5))
				(if
					(and
						(== (theIconBar curIcon?) (theIconBar useIconItem?))
						(== (theIconBar curInvIcon?) self)
					)
					(genericCursor view: 990 loop: cursorLoop cel: cursorCel)
					(theGame setCursor: genericCursor)
				)
				((ScriptID 0 7) setReal: (inventory at: 21) 30 2)
				(++ state)
			)
			(1
				(= noun 22)
				(= message 54)
				(self setCursor: 990 1 (= cel 6))
				(if
					(and
						(== (theIconBar curIcon?) (theIconBar useIconItem?))
						(== (theIconBar curInvIcon?) self)
					)
					(genericCursor view: 990 loop: cursorLoop cel: cursorCel)
					(theGame setCursor: genericCursor)
				)
				((ScriptID 0 7) setReal: (inventory at: 21) 30 2)
				(++ state)
			)
			(2
				((ScriptID 0 7) dispose:)
				(= noun 20)
				(= message 52)
				(self setCursor: 990 1 (= cel 4))
				(= state 0)
				(ego put: 21 480)
			)
		)
	)
)

(instance map of Kq6InvItem
	(properties
		loop 1
		cel 7
		message 12
		noun 31
		owner 280
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(if (OneOf curRoomNum 200 300 260 500 550 450)
				(inventory hide:)
				(self cue:)
			else
				(messager say: 31 5 43 0 0 907)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(if (== curRoomNum 450)
			(curRoom notify: 1)
		else
			(if (ego looper?) ((ego looper?) dispose:))
			(curRoom setScript: 130)
		)
	)
)

(instance milk of Kq6InvItem
	(properties
		loop 1
		cel 8
		message 62
		noun 32
		owner 470
	)
)

(instance mint of Kq6InvItem
	(properties
		loop 1
		cel 9
		message 63
		noun 33
		owner 280
	)
)

(instance mirror of Kq6InvItem
	(properties
		loop 1
		cel 10
		message 13
		noun 34
		owner 540
	)
)

(instance newLamp of Kq6InvItem
	(properties
		loop 1
		cel 11
		message 57
		noun 25
		owner 240
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(2
				(if (not (== noun 66)) (self hideInv: 1))
				(super doVerb: theVerb &rest)
			)
			(24
				(self hideInv: 0)
				(if (Btst 77)
					(messager say: noun theVerb 21 0 0 modNum)
				else
					(messager say: 57 theVerb 15 0 0 modNum)
				)
			)
			(else 
				(self hideInv: 0)
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance nail of Kq6InvItem
	(properties
		loop 2
		message 64
		noun 35
	)
)

(instance nightingale of Kq6InvItem
	(properties
		loop 2
		cel 1
		message 37
		noun 55
		owner 280
	)
)

(instance ticket of Kq6InvItem
	(properties
		loop 3
		cel 7
		message 49
		noun 17
		owner 600
	)
)

(instance participle of Kq6InvItem
	(properties
		loop 6
		cel 1
		message 94
		noun 57
		owner 500
	)
	
	(method (doVerb theVerb &tmp temp0 temp1 temp2)
		(super doVerb: theVerb &rest)
		(= temp2 (== view 972))
		(if (not (OneOf theVerb 1 94 28 13 12))
			(if (== msgType 1)
				(= temp0 0)
				(while (< temp0 20)
					(= temp1 0)
					(while (< temp1 7000)
						(++ temp1)
					)
					(DrawCel
						view
						5
						(Random 0 (- (if (not temp2) 14 else 9) 1))
						nsLeft
						nsTop
						15
						0
						(if temp2 (inventory empty?) else 0)
					)
					(++ temp0)
				)
			else
				(= temp0 0)
				(while (< temp0 500)
					(= temp1 0)
					(while (< temp1 7000)
						(++ temp1)
					)
					(DrawCel
						view
						5
						(Random 0 (- (if (not temp2) 14 else 9) 1))
						nsLeft
						nsTop
						15
						0
						(if temp2 (inventory empty?) else 0)
					)
					(breakif (== (DoAudio 6) -1))
					(++ temp0)
				)
			)
		)
		(DrawCel
			view
			5
			0
			nsLeft
			nsTop
			15
			0
			(if temp2 (inventory empty?) else 0)
		)
	)
)

(instance pearl of Kq6InvItem
	(properties
		loop 2
		cel 4
		message 66
		noun 37
		owner 450
	)
)

(instance peppermint of Kq6InvItem
	(properties
		loop 2
		cel 5
		message 67
		noun 38
		owner 390
	)
)

(instance note of Kq6InvItem
	(properties
		loop 2
		cel 6
		message 65
		noun 36
		owner 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(inventory hide:)
				(curRoom setScript: 96 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance potion of Kq6InvItem
	(properties
		loop 2
		cel 7
		message 14
		noun 12
		owner 480
	)
)

(instance rabbitFoot of Kq6InvItem
	(properties
		loop 2
		cel 8
		message 68
		noun 39
		owner 290
	)
)

(instance ribbon of Kq6InvItem
	(properties
		loop 2
		cel 10
		message 33
		noun 40
		owner 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(if (or (ego has: 4) (ego has: 16) (Btst 112))
					(messager say: noun theVerb 0 0 0 modNum)
				else
					(KQ6Print
						font: userFont
						posn: -1 10
						say: 0 noun theVerb 0 0 0 0 907
						init:
					)
					(KQ6Print
						font: userFont
						posn: -1 10
						say: 0 noun theVerb 33 0 0 0 907
						init:
					)
				)
			)
			(5
				(if
				(or (Btst 143) (ego has: 4) (ego has: 16) (Btst 112))
					(messager say: noun theVerb 34 0 0 modNum)
				else
					(inventory hide:)
					(Bset 143)
					(ego get: 16)
					(theGame givePoints: 1)
					(KQ6Print
						font: userFont
						say: 0 noun theVerb 33 0 0 0 modNum
						posn: -1 10
						init:
					)
					(pageCode init: local1)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance riddleBook of Kq6InvItem
	(properties
		loop 2
		cel 11
		message 27
		noun 2
		owner 460
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 5)
			(inventory hide:)
			(curRoom setScript: 90)
		else
			(self hideInv: 0)
			(super doVerb: theVerb &rest)
		)
	)
)

(instance ring of Kq6InvItem
	(properties
		loop 2
		cel 12
		message 69
		noun 41
		owner 540
	)
	
	(method (cue)
		(if
			(and
				(== (ego view?) 900)
				(User canControl:)
				(not (OneOf curRoomNum 580 390 320 300 270 490))
				(not (curRoom script?))
			)
			(curRoom setScript: (ScriptID 84 0))
		else
			((ScriptID 0 5) setReal: self 10 0 0)
		)
	)
)

(instance rose of Kq6InvItem
	(properties
		loop 2
		cel 13
		message 71
		noun 43
		owner 530
	)
)

(instance royalRing of Kq6InvItem
	(properties
		loop 2
		cel 14
		message 70
		noun 42
		owner 200
	)
)

(instance sacredWater of Kq6InvItem
	(properties
		loop 2
		cel 15
		message 24
		noun 56
		owner 380
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb 43) (huntersLamp doVerb: message))
			((OneOf 57 58 59 60 96 56) (newLamp doVerb: message))
			(else (super doVerb: theVerb))
		)
	)
)

(instance scarf of Kq6InvItem
	(properties
		loop 3
		message 72
		noun 45
		owner 490
	)
)

(instance scythe of Kq6InvItem
	(properties
		loop 3
		cel 1
		message 16
		noun 46
		owner 560
	)
)

(instance shield of Kq6InvItem
	(properties
		loop 3
		cel 2
		message 17
		noun 47
		owner 408
	)
)

(instance skeletonKey of Kq6InvItem
	(properties
		loop 3
		cel 3
		message 35
		noun 48
		owner 670
	)
)

(instance spellBook of Kq6InvItem
	(properties
		loop 3
		cel 5
		message 28
		noun 3
		owner 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(if
				(and (== (ego view?) 900) (not (curRoom script?)))
					(inventory hide:)
					(curRoom setScript: 190)
				else
					(messager say: 7 0 16 0 0 0)
				)
			)
			(43
				(huntersLamp doVerb: message)
			)
			(51 (skull doVerb: message))
			(44 (teaCup doVerb: message))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance teaCup of Kq6InvItem
	(properties
		loop 3
		cel 6
		message 44
		noun 53
		owner 480
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(KQ6Print
					font: userFont
					say: 0 noun theVerb 0 0 0 0 modNum
					posn: -1 10
					init:
				)
				(cond 
					((Btst 23)
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 32 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					((Btst 22)
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 31 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					(
						(and
							(not (Btst 68))
							(not (Btst 58))
							(not (Btst 22))
						)
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 27 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					(
					(and (Btst 68) (not (Btst 58)) (not (Btst 22)))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 28 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					((and (Btst 68) (Btst 58) (not (Btst 22)))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 30 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					(
					(and (not (Btst 68)) (Btst 58) (not (Btst 22)))
						(KQ6Print
							font: userFont
							say: 0 noun theVerb 29 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
				)
			)
			(28
				(cond 
					((Btst 23) (messager say: noun theVerb 32 0 0 modNum))
					((Btst 22) (messager say: noun theVerb 31 0 0 modNum))
					(
						(or
							(and
								(not (Btst 68))
								(not (Btst 58))
								(not (Btst 22))
							)
							(and (Btst 68) (not (Btst 58)) (not (Btst 22)))
							(and (Btst 68) (Btst 58) (not (Btst 22)))
							(and (not (Btst 68)) (Btst 58) (not (Btst 22)))
						)
						(messager say: noun theVerb 28 0 0 modNum)
					)
				)
			)
			(29
				(cond 
					((Btst 23) (messager say: noun theVerb 32 0 0 modNum))
					((Btst 22) (messager say: noun theVerb 31 0 0 modNum))
					(
						(and
							(not (Btst 68))
							(not (Btst 58))
							(not (Btst 22))
						)
						(messager say: noun theVerb 27 0 0 modNum)
					)
					(
						(or
							(and (Btst 68) (not (Btst 58)) (not (Btst 22)))
							(and (Btst 68) (Btst 58) (not (Btst 22)))
							(and (not (Btst 68)) (Btst 58) (not (Btst 22)))
						)
						(messager say: noun theVerb 28 0 0 modNum)
					)
				)
			)
			(30
				(cond 
					(
						(and
							(not (Btst 68))
							(not (Btst 58))
							(not (Btst 22))
						)
						(messager say: noun theVerb 27 0 0 modNum)
					)
					(
						(or
							(and (Btst 68) (not (Btst 58)) (not (Btst 22)))
							(and (not (Btst 68)) (Btst 58) (not (Btst 22)))
						)
						(messager say: noun theVerb 28 0 0 modNum)
					)
					((and (Btst 68) (Btst 58) (not (Btst 22)))
						(if
						(and (== (ego view?) 900) (not (curRoom script?)))
							(curRoom setScript: 915)
						else
							(messager say: 7 0 16 0 0 0)
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance poem of Kq6InvItem
	(properties
		loop 2
		cel 2
		message 32
		noun 30
		owner 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(5
				(inventory hide:)
				(curRoom setScript: 97 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tinderBox of Kq6InvItem
	(properties
		loop 3
		cel 8
		message 20
		noun 54
		owner 280
	)
)

(instance tomato of Kq6InvItem
	(properties
		loop 6
		message 34
		noun 44
		owner 480
	)
	
	(method (doVerb theVerb &tmp temp0 temp1 temp2)
		(if (not (OneOf theVerb 1 34))
			(= temp2 (== view 972))
			(if (== theVerb 2)
				(messager say: noun theVerb (Random 45 48) 0 0 907)
			else
				(super doVerb: theVerb &rest)
			)
			(if (not (OneOf theVerb 28 13 12))
				(if (== msgType 1)
					(= temp0 0)
					(while (< temp0 25)
						(= temp1 0)
						(while (< temp1 7000)
							(++ temp1)
						)
						(DrawCel
							view
							4
							(Random 0 (- (if (not temp2) 14 else 9) 1))
							nsLeft
							nsTop
							15
							0
							(if temp2 (inventory empty?) else 0)
						)
						(++ temp0)
					)
				else
					(= temp0 0)
					(while (< temp0 80)
						(= temp1 0)
						(while (< temp1 7000)
							(++ temp1)
						)
						(DrawCel
							view
							4
							(Random 0 (- (if (not temp2) 14 else 9) 1))
							nsLeft
							nsTop
							15
							0
							(if temp2 (inventory empty?) else 0)
						)
						(breakif (== (DoAudio 6) -1))
						(++ temp0)
					)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
		(DrawCel
			view
			4
			0
			nsLeft
			nsTop
			15
			0
			(if temp2 (inventory empty?) else 0)
		)
	)
)

(instance sentence of Kq6InvItem
	(properties
		loop 3
		cel 10
		message 85
		noun 11
		owner 450
	)
)

(instance ink of Kq6InvItem
	(properties
		loop 3
		cel 12
		message 83
		noun 68
		owner 240
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 1 5)
			(if (Btst 116)
				(messager say: noun theVerb 50 0 0 907)
			else
				(messager say: noun theVerb 0 0 0 907)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(class InvIconItem of IconItem
	(properties
		view -1
		loop -1
		cel -1
		nsLeft 0
		nsTop -1
		nsRight 0
		nsBottom 0
		state $0000
		cursor -1
		type $4000
		message -1
		modifiers $0000
		signal $0001
		maskView 0
		maskLoop 0
		maskCel 0
		highlightColor 0
		lowlightColor 0
		noun 0
		modNum 0
		helpVerb 0
	)
	
	(method (show theNsLeft theNsTop &tmp [temp0 7])
		(= signal (| signal $0020))
		(if argc
			(= nsRight
				(+ (= nsLeft theNsLeft) (CelWide view loop cel))
			)
			(= nsBottom
				(+ (= nsTop theNsTop) (CelHigh view loop cel))
			)
		else
			(= nsRight (+ nsLeft (CelWide view loop cel)))
			(= nsBottom (+ nsTop (CelHigh view loop cel)))
		)
		(DrawCel
			view
			loop
			cel
			nsLeft
			nsTop
			-1
			0
			(if
			(and global169 (Platform 6) (not (Platform 5)))
				(inventory empty?)
			else
				0
			)
		)
		(if (and pMouse (pMouse respondsTo: #stop))
			(pMouse stop:)
		)
	)
	
	(method (select param1 &tmp newEvent temp1 theGameScript inventoryEmpty)
		(if
			(and
				global169
				(Platform 6)
				(not (Platform 5))
				(inventory empty?)
			)
			(= inventoryEmpty (inventory empty?))
		else
			(= inventoryEmpty 0)
		)
		(return
			(cond 
				((& signal $0004) 0)
				((and argc param1 (& signal $0001))
					(DrawCel
						view
						loop
						(= temp1 1)
						nsLeft
						nsTop
						-1
						0
						inventoryEmpty
					)
					(Graph
						grUPDATE_BOX
						nsTop
						nsLeft
						nsBottom
						nsRight
						1
						inventoryEmpty
					)
					(while (!= ((= newEvent (Event new:)) type?) 2)
						(newEvent localize:)
						(cond 
							((self onMe: newEvent)
								(if (not temp1)
									(DrawCel
										view
										loop
										(= temp1 1)
										nsLeft
										nsTop
										-1
										0
										inventoryEmpty
									)
									(Graph
										grUPDATE_BOX
										nsTop
										nsLeft
										nsBottom
										nsRight
										1
										inventoryEmpty
									)
								)
							)
							(temp1
								(DrawCel
									view
									loop
									(= temp1 0)
									nsLeft
									nsTop
									-1
									0
									inventoryEmpty
								)
								(Graph
									grUPDATE_BOX
									nsTop
									nsLeft
									nsBottom
									nsRight
									1
									inventoryEmpty
								)
							)
						)
						(newEvent dispose:)
					)
					(newEvent dispose:)
					(if (== temp1 1)
						(DrawCel view loop 0 nsLeft nsTop -1 0 inventoryEmpty)
						(Graph
							grUPDATE_BOX
							nsTop
							nsLeft
							nsBottom
							nsRight
							1
							inventoryEmpty
						)
					)
					(if
						(and
							(= theGameScript (theGame script?))
							(theGameScript isKindOf: Tutorial)
						)
						(cond 
							(
								(and
									(== (theGameScript nextItem?) self)
									(!=
										(theGameScript nextAction?)
										((theIconBar helpIconItem?) message?)
									)
								)
								(theGameScript cue:)
							)
							((not temp1) (return 0))
							(else (theGameScript report:) (return 0))
						)
					)
					temp1
				)
				(else
					(if
						(and
							(= theGameScript (theGame script?))
							(theGameScript isKindOf: Tutorial)
						)
						(if
							(and
								(== (theGameScript nextItem?) self)
								(!=
									(theGameScript nextAction?)
									((theIconBar helpIconItem?) message?)
								)
							)
							(theGameScript cue:)
						else
							(theGameScript report:)
							(return 0)
						)
					)
					1
				)
			)
		)
	)
	
	(method (highlight)
		(if (or (not global169) (Platform 5))
			(-- nsLeft)
			(-- nsTop)
			(= nsRight (+ nsRight 2))
			(super highlight: &rest)
			(= nsRight (- nsRight 2))
			(++ nsTop)
			(++ nsLeft)
		)
	)
	
	(method (onMe event &tmp temp0)
		(= temp0
			(if (and global169 (Platform 6))
				(not (Platform 5))
			else
				0
			)
		)
		(return
			(if
			(>= (event x?) (if temp0 (/ nsLeft 2) else nsLeft))
				(if
				(>= (event y?) (if temp0 (/ nsTop 2) else nsTop))
					(if
					(<= (event x?) (if temp0 (/ nsRight 2) else nsRight))
						(<= (event y?) (if temp0 (/ nsBottom 2) else nsBottom))
					)
				)
			else
				0
			)
		)
	)
)

(instance ok of InvIconItem
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		signal $0043
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 19)
		(super init: &rest)
	)
)

(instance invLook of InvIconItem
	(properties
		view 901
		loop 2
		cel 0
		message 1
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 19)
		(super init: &rest)
	)
)

(instance invHand of InvIconItem
	(properties
		view 901
		loop 0
		cel 0
		message 5
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 19)
		(super init: &rest)
	)
)

(instance invSelect of InvIconItem
	(properties
		view 901
		loop 4
		cel 0
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 19)
		(super init: &rest)
	)
)

(instance invTalk of InvIconItem
	(properties
		view 901
		loop 5
		cel 0
		message 2
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 19)
		(super init: &rest)
	)
)

(instance invMore of InvIconItem
	(properties
		view 901
		loop 6
		cel 0
		lowlightColor 19
	)
	
	(method (show)
		(if (< local0 13) (= loop 8) else (= loop 6))
		(super show: &rest)
	)
	
	(method (select &tmp temp0 temp1)
		(return
			(if (super select: &rest)
				(if (>= local0 13)
					(= temp0 0)
					(while (< temp0 52)
						(if
							(==
								((= temp1 (inventory at: temp0)) realOwner?)
								local1
							)
							(if (== (temp1 owner?) local1)
								(temp1 owner: 1)
							else
								(temp1 owner: local1)
							)
						)
						(++ temp0)
					)
					(inventory state: (| (inventory state?) $2000))
					(inventory
						hide: 1
						highlightedIcon: ok
						addAfter: invTalk invPrevious
						delete: invMore
						showSelf: local1
					)
					(return 0)
				else
					(messager say: 67 0 49 0 0 907)
					(return 0)
				)
			else
				0
			)
		)
	)
)

(instance invPrevious of InvIconItem
	(properties
		view 901
		loop 7
		cel 0
		lowlightColor 19
	)
	
	(method (select &tmp temp0 temp1)
		(return
			(if (super select: &rest)
				(= temp0 0)
				(while (< temp0 52)
					(if
						(==
							((= temp1 (inventory at: temp0)) realOwner?)
							local1
						)
						(if (== (temp1 owner?) 1)
							(temp1 owner: local1)
						else
							(temp1 owner: 0)
						)
					)
					(++ temp0)
				)
				(inventory state: (| (inventory state?) $2000))
				(inventory
					hide: 1
					highlightedIcon: ok
					addAfter: invTalk invMore
					delete: invPrevious
					showSelf: local1
				)
				(return 0)
			else
				0
			)
		)
	)
)

(instance cInvLook of Cursor
	(properties
		view 998
		loop 1
		cel 1
	)
)

(instance cInvHand of Cursor
	(properties
		view 998
		loop 1
		cel 2
	)
)

(instance cInvTalk of Cursor
	(properties
		view 998
		loop 1
		cel 3
	)
)

(instance genericCursor of Cursor
	(properties)
)
