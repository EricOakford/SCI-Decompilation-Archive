;;; Sierra Script 1.0 - (do not remove this comment)
(script# 907)
(include game.sh) (include "907.shm")
(use Main)
(use Kq6Window)
(use Kq6Procs)
(use Print)
(use IconBar)
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
	curPage
)
(class Kq6InvItem of InvItem
	(properties
		modNum 907
		realOwner 0
		cursorView 0
		cursorLoop 0
		cursorCel 0
		hideInv 0
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
	
	(method (doVerb theVerb &tmp temp0 [temp1 100])
		(if (OneOf theVerb V_SPELLBOOK V_MIRROR V_MAP)
			(messager say: 0 theVerb 0 0 0 modNum)
		else
			(if hideInv (inventory hide: TRUE))
			(if (not modNum) (= modNum curRoomNum))
			(cond 
				(
					(and
						msgType
						(Message MsgGet modNum noun theVerb 0 1)
						(!= (messager findTalker: (Message MsgNext 0)) -1)
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
						(= temp0 (Message MsgGet modNum noun theVerb 0 1))
						(!= (messager findTalker: temp0) -1)
					)
					((inventory curIcon?) doVerb: message)
				)
				(else
					(switch theVerb
						(V_DO
							(messager say: N_GENERIC V_DO 0 0 0 modNum)
						)
						(V_TALK
							(messager say: N_GENERIC V_TALK 0 0 0 modNum)
						)
						(else 
							(messager say: noun 0 0 0 0 modNum)
						)
					)
				)
			)
		)
	)
	
	(method (checkPage &tmp item temp1)
		(= temp1 0)
		(= item 0)
		(while (< item 52)
			(if (== ((inventory at: item) owner?) curPage)
				(= temp1 1)
				(= item 53)
			)
			(++ item)
		)
		(inventory hide:)
		(if temp1
			(if (> item 52)
				(inventory show: curPage selectIcon: invSelect)
			else
				(invPrevious select:)
			)
		)
	)
	
	(method (setCursor theView theLoop theCel)
		(= cursorView theView)
		(= cursorLoop theLoop)
		(= cursorCel theCel)
		(= cursor genericCursor)
	)
	
	(method (cue param1)
		(if (and argc param1)
			(pageCode init: curPage)
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
		(= curPage param1)
		(inventory selectIcon: invSelect window: invWin)
		(invSelect loop: 4 message: -1)
		(= local0 0)
		(= temp0 0)
		(while (< temp0 iEndItems)
			(if
			(== ((= temp1 (inventory at: temp0)) owner?) curPage)
				(temp1 realOwner: (temp1 owner?) owner: 0)
				(if (< (++ local0) 13) (temp1 owner: curPage))
			)
			(++ temp0)
		)
		(if local0 (inventory addAfter: invTalk invMore))
		(inventory delete: invPrevious showSelf: curPage)
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
			(sounds pause: FALSE)
			(= state (& state $ffdf))
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
		view 970
		message V_BORINGBOOK
		noun N_BORINGBOOK
		owner 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
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
		view 970
		cel 1
		message V_BRICK
		noun N_BRICK
		owner 510
	)
)

(instance brush of Kq6InvItem
	(properties
		view 970
		cel 2
		message V_BRUSH
		noun N_BRUSH
		owner 280
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if state
					(messager say: noun theVerb 2 0 0 modNum)
				else
					(messager say: noun theVerb 1 0 0 modNum)
				)
			)
			(V_DO
				(if state
					(messager say: noun theVerb 2 0 0 modNum)
				else
					(messager say: noun theVerb 1 0 0 modNum)
				)
			)
			(V_SPELLBOOK
				(if state
					(messager say: noun theVerb 2 0 0 modNum)
				else
					(messager say: noun theVerb 1 0 0 modNum)
				)
			)
			(V_BRUSH
				(messager say: noun theVerb 0 0 0 modNum)
			)
			(V_TEACUP
				(teaCup doVerb: message)
			)
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
		view 970
		cel 15
		message V_HAIR
		noun N_BEAUTY_HAIR
		owner 530
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SKULL
				(skull doVerb: message)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance clothes of Kq6InvItem
	(properties
		view 970
		cel 4
		message V_CLOTHES
		noun N_CLOTHES
		owner 540
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (and (not (ego has: iHair)) (not (Btst 93)))
					(Print
						font: userFont
						addText: noun theVerb 0 0 0 0 modNum
						posn: -1 10
						init:
					)
					(Print
						font: userFont
						addText: noun theVerb 36 0 0 0 modNum
						posn: -1 10
						init:
					)
				else
					(super doVerb: theVerb &rest)
				)
			)
			(V_DO
				(if
				(or (Btst fFoundHair) (ego has: iHair) (ego has: iCassimaHair) (Btst 93))
					(messager say: noun theVerb 34 0 0 modNum)
				else
					(inventory hide:)
					(Bset fFoundHair)
					(ego get: iHair)
					(theGame givePoints: 1)
					(Print
						font: userFont
						addText: noun theVerb 36 0 0 0 modNum
						posn: -1 10
						init:
					)
					(pageCode init: curPage)
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
		view 970
		cel 5
		message V_COAL
		noun N_COAL
		owner 560
	)
)

(instance deadMansCoin of Kq6InvItem
	(properties
		view 970
		cel 6
		message V_DEAD_COIN
		noun N_DEAD_COIN
		owner 430
	)
)

(instance dagger of Kq6InvItem
	(properties
		view 970
		cel 7
		message V_DAGGER
		noun N_DAGGER
		owner 440
	)
)

(instance coin of Kq6InvItem
	(properties
		view 970
		cel 8
		message V_COIN
		noun N_COIN
		owner 200
	)
)

(instance egg of Kq6InvItem
	(properties
		view 970
		cel 9
		message V_EGG
		noun N_EGG
		owner 490
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SKULL
				(skull doVerb: message)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance skull of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 4
		message V_SKULL
		noun N_SKULL
		owner 415
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_CASSIMA_HAIR) (= theVerb V_HAIR))
		(switch theVerb
			(V_TINBERBOX
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
			(V_SPELLBOOK
				(cond 
					((not state) (messager say: noun theVerb 38 0 0 modNum))
					(
					(and (& state $0004) (& state $0001) (& state $0002))
						(if
						(and (not (curRoom script?)) (== (ego view?) 900))
							(inventory hide:)
							(curRoom setScript: 190)
						else
							(messager say: N_COAL 0 16 0 0 0)
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
			(V_HAIR
				(cond 
					((not state) (messager say: noun V_HAIR 38 0 0 modNum))
					((& state $0004)
						(inventory hide:)
						(if (ego has: iHair) (ego put: iHair) else (ego put: iCassimaHair))
						(theGame givePoints: 1)
						(if (& state $0001)
							(Print
								font: userFont
								addText: noun theVerb 41 0 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 39 0 0 0 modNum
								posn: -1 10
								init:
							)
						)
						(= state (| state $0002))
						(pageCode init: curPage)
					)
				)
			)
			(V_EGG
				(cond 
					((not state) (messager say: noun theVerb 38 0 0 modNum))
					((and (& state $0004) (& state $0002))
						(inventory hide:)
						(Print
							font: userFont
							addText: noun theVerb 40 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(Print
								font: userFont
								addText: noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
						(ego put: iEgg)
						(theGame givePoints: 1)
						(= state (| state $0001))
						(pageCode init: curPage)
					)
					((& state $0004)
						(inventory hide:)
						(Print
							font: userFont
							addText: noun theVerb 39 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(Print
								font: userFont
								addText: noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
						(ego put: iEgg)
						(theGame givePoints: 1)
						(= state (| state $0001))
						(pageCode init: curPage)
					)
				)
			)
			(V_LOOK
				(cond 
					((not state) (messager say: noun theVerb 38 0 0 modNum))
					(
					(and (& state $0004) (& state $0001) (& state $0002))
						(Print
							font: userFont
							addText: noun theVerb 42 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(Print
								font: userFont
								addText: noun theVerb 41 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 41 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
					((and (& state $0004) (& state $0002))
						(Print
							font: userFont
							addText: noun theVerb 40 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(Print
								font: userFont
								addText: noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 39 2 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
					((and (& state $0004) (& state $0001))
						(Print
							font: userFont
							addText: noun theVerb 41 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(Print
								font: userFont
								addText: noun theVerb 41 2 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 41 3 0 0 modNum
								posn: -1 10
								init:
							)
						)
					)
					((& state $0004)
						(Print
							font: userFont
							addText: noun theVerb 39 1 0 0 modNum
							posn: -1 10
							init:
						)
						(if (& state $0008)
							(Print
								font: userFont
								addText: noun theVerb 39 3 0 0 modNum
								posn: -1 10
								init:
							)
						else
							(Print
								font: userFont
								addText: noun theVerb 39 2 0 0 modNum
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
		view 970
		cel 11
		message V_FEATHER
		noun N_FEATHER
		owner 300
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TEACUP
				(teaCup doVerb: message)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance flower of Kq6InvItem
	(properties
		view 970
		cel 12
		message V_FLOWER
		noun N_FLOWER
		owner 300
	)
)

(instance flute of Kq6InvItem
	(properties
		view 970
		cel 13
		message V_FLUTE
		noun N_FLUTE
		owner 280
	)
)

(instance gauntlet of Kq6InvItem
	(properties
		view 970
		cel 14
		message V_GAUNTLET
		noun N_GAUNTLET
		owner 650
	)
)

(instance cassimaHair of Kq6InvItem
	(properties
		view 970
		cel 3
		message V_CASSIMA_HAIR
		noun N_CASSIMA_HAIR
		owner 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_SKULL
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
		view 970
		loop 1
		message V_HANDKERCHIEF
		noun N_HANDKERCHIEF
		owner 680
	)
)

(instance holeInTheWall of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 1
		message V_HOLE_IN_WALL
		noun N_HOLE_IN_WALL
		owner 480
	)
)

(instance huntersLamp of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 2
		message V_HUNTER_LAMP
		noun N_HUNTER_LAMP
		owner 520
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Print
					font: userFont
					posn: -1 10
					addText: noun theVerb 0 0 0 0 modNum
					init:
				)
				(cond 
					((== huntersLampState 0) (messager say: noun theVerb 4 0 0 modNum))
					((== huntersLampState 1) (messager say: noun theVerb 7 0 0 modNum))
					((== huntersLampState 4) (messager say: noun theVerb 6 0 0 modNum))
					((== huntersLampState 6) (messager say: noun theVerb 8 0 0 modNum))
					((== huntersLampState 5) (messager say: noun theVerb 12 0 0 modNum))
					((== huntersLampState 15) (messager say: noun theVerb 10 0 0 modNum))
					((== huntersLampState 7) (messager say: noun theVerb 9 0 0 modNum))
					((== huntersLampState 2) (messager say: noun theVerb 5 0 0 modNum))
					((== huntersLampState 3) (messager say: noun theVerb 11 0 0 modNum))
				)
			)
			(28
				(cond 
					((not (Btst 77)) (messager say: noun theVerb 15 0 0 modNum))
					((== huntersLampState 15) (messager say: noun theVerb 10 0 0 modNum))
					((== huntersLampState 0) (messager say: noun theVerb 4 0 0 modNum))
					((== huntersLampState 7)
						(if
						(and (not (curRoom script?)) (== (ego view?) 900))
							(inventory hide:)
							(curRoom setScript: 190)
						else
							(messager say: N_COAL 0 16 0 0 0)
						)
					)
					(
						(or
							(== huntersLampState (| huntersLampState $0001))
							(== huntersLampState (| huntersLampState $0004))
							(== huntersLampState (| huntersLampState $0002))
						)
						(messager say: noun theVerb 14 0 0 modNum)
					)
				)
			)
			(V_SACRED_WATER
				(cond 
					((not (Btst 77)) (messager say: noun theVerb 15 0 0 modNum))
					((and (Btst 77) (== huntersLampState 1)) (messager say: noun theVerb 18 0 0 modNum))
					((and (Btst 77) (== huntersLampState 5)) (messager say: noun theVerb 19 0 0 modNum))
					((and (Btst 77) (== huntersLampState 0))
						(inventory hide:)
						(ego put: iSacredWater)
						(theGame givePoints: 1)
						(= huntersLampState (| huntersLampState $0002))
						(Print
							font: userFont
							addText: noun theVerb 16 0 0 0 modNum
							posn: -1 10
							init:
						)
						(inventory curIcon: (inventory selectIcon?))
						(theIconBar disable: ICON_ITEM)
						(pageCode init: curPage)
					)
					((and (Btst 77) (== huntersLampState 4))
						(inventory hide:)
						(ego put: iSacredWater)
						(theGame givePoints: 1)
						(= huntersLampState (| huntersLampState $0002))
						(Print
							font: userFont
							addText: noun theVerb 17 0 0 0 modNum
							posn: -1 10
							init:
						)
						(pageCode init: curPage)
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
		view 970
		loop 1
		cel 3
		message V_LETTER
		noun N_LETTER
		owner 780
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
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
		view 970
		loop 1
		cel 4
		message V_LETTUCE
		noun N_LETTUCE
		owner 480
	)
	
	(method (cue)
		(switch state
			(0
				(= noun N_LETTUCE_MELTING)
				(= message V_LETTUCE_MELTING)
				(self setCursor: 990 1 (= cel 5))
				(if
					(and
						(== (theIconBar curIcon?) (theIconBar useIconItem?))
						(== (theIconBar curInvIcon?) self)
					)
					(genericCursor view: 990 loop: cursorLoop cel: cursorCel)
					(theGame setCursor: genericCursor)
				)
				((ScriptID 0 7) setReal: (inventory at: iLettuce) 30 2)
				(++ state)
			)
			(1
				(= noun N_LETTUCE_WATER)
				(= message V_LETTUCE_WATER)
				(self setCursor: 990 1 (= cel 6))
				(if
					(and
						(== (theIconBar curIcon?) (theIconBar useIconItem?))
						(== (theIconBar curInvIcon?) self)
					)
					(genericCursor view: 990 loop: cursorLoop cel: cursorCel)
					(theGame setCursor: genericCursor)
				)
				((ScriptID 0 7) setReal: (inventory at: iLettuce) 30 2)
				(++ state)
			)
			(2
				((ScriptID 0 7) dispose:)
				(= noun N_LETTUCE)
				(= message V_LETTUCE)
				(self setCursor: 990 1 (= cel 4))
				(= state 0)
				(ego put: iLettuce 480)
			)
		)
	)
)

(instance map of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 7
		message V_MAP
		noun N_MAP
		owner 280
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
			(if (OneOf curRoomNum 200 300 260 500 550 450)
				(inventory hide:)
				(self cue:)
			else
				(messager say: N_MAP V_DO 43 0 0 907)
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
	
	(method (cue)
		(if (== curRoomNum 450)
			(curRoom notify: 1)
		else
			((ego looper?) dispose:)
			(ego setMotion: 0 setCycle: 0)
			(curRoom setScript: 130)
		)
	)
)

(instance milk of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 8
		message V_MILK
		noun N_MILK
		owner 470
	)
)

(instance mint of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 9
		message V_MINT
		noun N_MINT
		owner 280
	)
)

(instance mirror of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 10
		message V_MIRROR
		noun N_MIRROR
		owner 540
	)
)

(instance newLamp of Kq6InvItem
	(properties
		view 970
		loop 1
		cel 11
		message V_WRONG_LAMP1
		noun N_WRONG_LAMP1
		owner 240
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TALK
				(if (not (== noun N_GENIE_LAMP)) (self hideInv: TRUE))
				(super doVerb: theVerb &rest)
			)
			(V_SACRED_WATER
				(self hideInv: FALSE)
				(if (Btst 77)
					(messager say: noun theVerb 21 0 0 modNum)
				else
					(messager say: N_PARTICIPLE theVerb 15 0 0 modNum)
				)
			)
			(else 
				(self hideInv: FALSE)
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance nail of Kq6InvItem
	(properties
		view 970
		loop 2
		message V_NAIL
		noun N_NAIL
	)
)

(instance nightingale of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 1
		message V_NIGHTINGALE
		noun N_NIGHTINGALE
		owner 280
	)
)

(instance ticket of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 7
		message V_TICKET
		noun N_TICKET
		owner 600
	)
)

(instance participle of Kq6InvItem
	(properties
		view 970
		loop 5
		message V_PARTICIPLE
		noun N_PARTICIPLE
		owner 500
	)
	
	(method (doVerb theVerb &tmp temp0 temp1)
		(super doVerb: theVerb &rest)
		(if (not (OneOf theVerb V_LOOK V_PARTICIPLE V_SPELLBOOK V_MIRROR V_MAP))
			(= temp0 0)
			(while (< temp0 20)
				(= temp1 0)
				(while (< temp1 7000)
					(++ temp1)
				)
				(DrawCel 970 5 (Random 0 (- (NumCels self) 1)) nsLeft nsTop 15)
				(++ temp0)
			)
		)
		(DrawCel 970 5 0 nsLeft nsTop 15)
	)
)

(instance pearl of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 4
		message V_PEARL
		noun N_PEARL
		owner 450
	)
)

(instance peppermint of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 5
		message V_PEPPERMINT
		noun N_PEPPERMINT
		owner 390
	)
)

(instance note of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 6
		message V_NOTE
		noun N_NOTE
		owner 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(inventory hide:)
				(curRoom setScript: 96 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance potion of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 7
		message V_POTION
		noun N_POTION
		owner 480
	)
)

(instance rabbitFoot of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 8
		message V_RABBIT_FOOT
		noun N_RABBIT_FOOT
		owner 290
	)
)

(instance ribbon of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 10
		message V_RIBBON
		noun N_RIBBON
		owner 210
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(if (or (ego has: iHair) (ego has: iCassimaHair) (Btst 112))
					(messager say: noun theVerb 0 0 0 modNum)
				else
					(Print
						font: userFont
						posn: -1 10
						addText: noun theVerb 0 0 0 0 907
						init:
					)
					(Print
						font: userFont
						posn: -1 10
						addText: noun theVerb 33 0 0 0 907
						init:
					)
				)
			)
			(V_DO
				(if
				(or (Btst fFoundHair) (ego has: iHair) (ego has: iCassimaHair) (Btst 112))
					(messager say: noun theVerb 34 0 0 modNum)
				else
					(inventory hide:)
					(Bset fFoundHair)
					(ego get: iCassimaHair)
					(theGame givePoints: 1)
					(Print
						font: userFont
						addText: noun theVerb 33 0 0 0 modNum
						posn: -1 10
						init:
					)
					(pageCode init: curPage)
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
		view 970
		loop 2
		cel 11
		message V_RIDDLE_BOOK
		noun N_RIDDLE_BOOK
		owner 460
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_DO)
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
		view 970
		loop 2
		cel 12
		message V_RING
		noun N_RING
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
		view 970
		loop 2
		cel 13
		message V_ROSE
		noun N_ROSE
		owner 530
	)
)

(instance royalRing of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 14
		message V_ROYAL_RING
		noun N_ROYAL_RING
		owner 200
	)
)

(instance sacredWater of Kq6InvItem
	(properties
		view 970
		loop 2
		cel 15
		message V_SACRED_WATER
		noun N_SACRED_WATER
		owner 380
	)
	
	(method (doVerb theVerb)
		(cond 
			((== theVerb V_HUNTER_LAMP) (huntersLamp doVerb: message))
			((OneOf V_WRONG_LAMP1 V_WRONG_LAMP2 V_WRONG_LAMP3 V_WRONG_LAMP4 96 V_CORRECT_LAMP) (newLamp doVerb: message))
			(else (super doVerb: theVerb))
		)
	)
)

(instance scarf of Kq6InvItem
	(properties
		view 970
		loop 3
		message V_SCARF
		noun N_SCARF
		owner 490
	)
)

(instance scythe of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 1
		message V_SCYTHE
		noun N_SCYTHE
		owner 560
	)
)

(instance shield of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 2
		message V_SHIELD
		noun N_SHIELD
		owner 408
	)
)

(instance skeletonKey of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 3
		message V_SKELETON_KEY
		noun N_SKELETON_KEY
		owner 670
	)
)

(instance spellBook of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 5
		message V_SPELLBOOK
		noun N_SPELLBOOK
		owner 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(if
				(and (== (ego view?) 900) (not (curRoom script?)))
					(inventory hide:)
					(curRoom setScript: 190)
				else
					(messager say: N_COAL 0 16 0 0 0)
				)
			)
			(V_HUNTER_LAMP
				(huntersLamp doVerb: message)
			)
			(V_SKULL (skull doVerb: message))
			(V_TEACUP (teaCup doVerb: message))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance teaCup of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 6
		message V_TEACUP
		noun N_TEACUP
		owner 480
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK
				(Print
					font: userFont
					addText: noun theVerb 0 0 0 0 modNum
					posn: -1 10
					init:
				)
				(cond 
					((Btst 23)
						(Print
							font: userFont
							addText: noun theVerb 32 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					((Btst 22)
						(Print
							font: userFont
							addText: noun theVerb 31 0 0 0 modNum
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
						(Print
							font: userFont
							addText: noun theVerb 27 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					(
					(and (Btst 68) (not (Btst 58)) (not (Btst 22)))
						(Print
							font: userFont
							addText: noun theVerb 28 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					((and (Btst 68) (Btst 58) (not (Btst 22)))
						(Print
							font: userFont
							addText: noun theVerb 30 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
					(
					(and (not (Btst 68)) (Btst 58) (not (Btst 22)))
						(Print
							font: userFont
							addText: noun theVerb 29 0 0 0 modNum
							posn: -1 10
							init:
						)
					)
				)
			)
			(V_SPELLBOOK
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
			(V_BRUSH
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
			(V_FEATHER
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
							(messager say: N_COAL 0 16 0 0 0)
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
		view 970
		loop 2
		cel 2
		message V_POEM
		noun N_POEM
		owner 270
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO
				(inventory hide:)
				(curRoom setScript: 97 self)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance tinderBox of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 8
		message V_TINBERBOX
		noun N_TINDERBOX
		owner 280
	)
)

(instance tomato of Kq6InvItem
	(properties
		view 970
		loop 4
		message V_TOMATO
		noun N_TOMATO
		owner 480
	)
	
	(method (doVerb theVerb &tmp temp0 temp1)
		(if (not (OneOf theVerb V_LOOK V_TOMATO))
			(if (== theVerb V_TALK)
				(messager say: noun theVerb (Random 45 48) 0 0 907)
			else
				(super doVerb: theVerb &rest)
			)
			(if (not (OneOf theVerb V_SPELLBOOK V_MIRROR V_MAP))
				(= temp0 0)
				(while (< temp0 25)
					(= temp1 0)
					(while (< temp1 7000)
						(++ temp1)
					)
					(DrawCel
						970
						4
						(Random 0 (- (NumCels self) 1))
						nsLeft
						nsTop
						15
					)
					(++ temp0)
				)
			)
		else
			(super doVerb: theVerb &rest)
		)
		(DrawCel 970 4 0 nsLeft nsTop 15)
	)
)

(instance sentence of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 10
		message V_SENTENCE
		noun N_SENTENCE
		owner 450
	)
)

(instance ink of Kq6InvItem
	(properties
		view 970
		loop 3
		cel 12
		message V_INK
		noun N_INK
		owner 240
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb V_LOOK V_DO)
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

(instance ok of IconItem
	(properties
		view 901
		loop 3
		cel 0
		nsLeft 40
		signal (| RELVERIFY IMMEDIATE HIDEBAR)
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 44)
		(super init: &rest)
	)
)

(instance invLook of IconItem
	(properties
		view 901
		loop 2
		cel 0
		message V_LOOK
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 44)
		(super init: &rest)
	)
)

(instance invHand of IconItem
	(properties
		view 901
		loop 0
		cel 0
		message V_DO
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 44)
		(super init: &rest)
	)
)

(instance invSelect of IconItem
	(properties
		view 901
		loop 4
		cel 0
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 44)
		(super init: &rest)
	)
)

(instance invTalk of IconItem
	(properties
		view 901
		loop 5
		cel 0
		message V_TALK
	)
	
	(method (init)
		(self highlightColor: 0 lowlightColor: 44)
		(super init: &rest)
	)
)

(instance invMore of IconItem
	(properties
		view 901
		loop 6
		cel 0
		lowlightColor 44
	)
	
	(method (select &tmp item temp1)
		(return
			(if (super select: &rest)
				(if (>= local0 13)
					(= item 0)
					(while (< item iEndItems)
						(if
							(==
								((= temp1 (inventory at: item)) realOwner?)
								curPage
							)
							(if (== (temp1 owner?) curPage)
								(temp1 owner: 1)
							else
								(temp1 owner: curPage)
							)
						)
						(++ item)
					)
					(inventory state: (| (inventory state?) $2000))
					(inventory
						hide: 1
						highlightedIcon: ok
						addAfter: invTalk invPrevious
						delete: invMore
						showSelf: curPage
					)
					(return FALSE)
				else
					(messager say: N_ONLY_PAGE 0 49 0 0 907)
					(return FALSE)
				)
			else
				FALSE
			)
		)
	)
)

(instance invPrevious of IconItem
	(properties
		view 901
		loop 7
		cel 0
		lowlightColor 44
	)
	
	(method (select &tmp item temp1)
		(return
			(if (super select: &rest)
				(= item 0)
				(while (< item iEndItems)
					(if
						(==
							((= temp1 (inventory at: item)) realOwner?)
							curPage
						)
						(if (== (temp1 owner?) 1)
							(temp1 owner: curPage)
						else
							(temp1 owner: 0)
						)
					)
					(++ item)
				)
				(inventory state: (| (inventory state?) $2000))
				(inventory
					hide: 1
					highlightedIcon: ok
					addAfter: invTalk invMore
					delete: invPrevious
					showSelf: curPage
				)
				(return FALSE)
			else
				FALSE
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
