;;; Sierra Script 1.0 - (do not remove this comment)
(script# 387)
(include game.sh)
(use Main)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Narrator)
(use Sq4Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use System)

(public
	rm387 0
)

(local
	totalFails
	oldCur =  68
	local2 =  3
	totalPay
	saveBits
	saveBits2
	saveBits3
	local7
	[local8 7] = [41 42 43 44 45 46 47]
	local15 =  3
	earnedBuckazoid =  1
	consecutiveFails
	thePMouse
)
(procedure (GrabCondiment theCondiment)
	(if
		(or
			(== (lettuce followWho?) 1)
			(== (pickle followWho?) 1)
			(== (bun followWho?) 1)
			(== (mayo followWho?) 1)
			(== (must followWho?) 1)
			(== (cats followWho?) 1)
			(theCondiment followWho?)
		)
		0
	else
		(theCondiment follow: TRUE)
	)
)

(procedure (localproc_040c param1 param2 param3 param4 &tmp port temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 [temp11 5])
	(= temp2 (VGAOrEGA myBackColor myInsideColor))
	(= temp8 14)
	(= temp4 (VGAOrEGA myTopBordColor myTopBordColor))
	(= temp7 (VGAOrEGA myLftBordColor myTopBordColor))
	(= temp6 (VGAOrEGA myRgtBordColor myBotBordColor))
	(= temp5 (VGAOrEGA myBotBordColor myBotBordColor))
	(= temp1 3)
	(= temp9 2)
	(= temp3 3)
	(= port (GetPort))
	(SetPort 0)
	(Graph
		GFillRect
		param1
		param2
		(+ param3 1)
		(+ param4 1)
		temp3
		temp2
		temp8
	)
	(= param1 (- param1 temp1))
	(= param2 (- param2 temp1))
	(= param4 (+ param4 temp1))
	(= param3 (+ param3 temp1))
	(Graph
		GFillRect
		param1
		param2
		(+ param1 temp1)
		param4
		temp3
		temp4
		temp8
	)
	(Graph
		GFillRect
		(- param3 temp1)
		param2
		param3
		param4
		temp3
		temp5
		temp8
	)
	(= temp10 0)
	(while (< temp10 temp1)
		(Graph
			GDrawLine
			(+ param1 temp10)
			(+ param2 temp10)
			(- param3 (+ temp10 1))
			(+ param2 temp10)
			temp7
			temp8
		)
		(Graph
			GDrawLine
			(+ param1 temp10)
			(- param4 (+ temp10 1))
			(- param3 (+ temp10 1))
			(- param4 (+ temp10 1))
			temp6
			temp8
		)
		(++ temp10)
	)
	(Graph
		GFillRect
		(+ param1 temp9)
		param4
		(+ param3 temp9)
		(+ param4 temp9)
		temp3
		0
		temp8
	)
	(Graph
		GFillRect
		param3
		(+ param2 temp9)
		(+ param3 temp9)
		param4
		temp3
		0
		temp8
	)
	(Graph
		GShowBits
		param1
		param2
		(+ param3 2)
		(+ param4 2)
		1
	)
	(SetPort port)
)

(instance rm387 of SQRoom
	(properties
		picture 387
	)
	
	(method (init)
		(self setRegions: MALL)
		(= thePMouse pMouse)
		(LoadMany SOUND 41 101 122)
		(HandsOnCode doit:)
		(super init:)
		(ego setCycle: 0)
		(burger init:)
		(lettuce init: rePosn: condoList: cList)
		(pickle init: rePosn: condoList: cList)
		(bun init: rePosn: condoList: cList)
		(mayo init: rePosn: mySound: squirtSound condoList: cList)
		(cats init: rePosn: mySound: squirtSound condoList: cList)
		(must init: rePosn: mySound: squirtSound condoList: cList)
		(keyHandler
			add: lettuceBay pickleBay mayoBay mustBay catsBay bunBay
			target: burger
			init:
		)
		(dr init:)
		(rejectSign init: stopUpd:)
		(if (> (theGame detailLevel:) 1)
			(conveyor init: setCycle: Forward)
		)
		(grabber init: stopUpd:)
		(lettuceBay init:)
		(pickleBay init:)
		(bunBay init:)
		(belt init:)
		(topCounter init:)
		(bottomCounter init:)
		(upperPipes init:)
		(redTube init:)
		(lowerPipes init:)
		(hood init:)
		(readSignFeat init:)
		(self setScript: instructScript)
	)
	
	(method (doit)
		(if (GameIsRestarting) (restoreCode doit:))
		(super doit:)
	)
	
	(method (dispose)
		(if modelessDialog (modelessDialog dispose:))
		(cList delete: dispose:)
		(keyHandler delete: dispose:)
		(= pMouse thePMouse)
		(super dispose:)
	)
)

(instance HandsOnCode of Code
	(properties)
	
	(method (doit)
		(User canControl: TRUE canInput: TRUE)
		(theIconBar enable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_SMELL
			ICON_TASTE
			ICON_ITEM
			ICON_INVENTORY
		)
		(if (not (theIconBar curInvIcon?))
			(theIconBar disable: ICON_ITEM)
		)
		(theGame setCursor: ((theIconBar curIcon?) cursor?))
		(theIconBar disable: ICON_WALK)
	)
)

(instance HandsOffCode of Code
	(properties)
	
	(method (doit)
		(User canControl: FALSE canInput: FALSE)
		(theIconBar disable:
			ICON_WALK
			ICON_LOOK
			ICON_DO
			ICON_TALK
			ICON_SMELL
			ICON_TASTE
			ICON_ITEM
			ICON_INVENTORY
		)
	)
)

(instance restoreCode of Code
	(properties)
	
	(method (doit &tmp [temp0 15] [temp15 5])
		(localproc_040c 170 7 184 87)
		(= saveBits2
			(DoDisplay {Pay:}
				#at 9 173
				#color myTextColor2
				#back myLowlightColor
				#mode teJustLeft
			)
		)
		(= saveBits
			(DoDisplay (Format @temp15 387 0 totalPay)
				#width 43 173
				#color myTextColor7
				#back myLowlightColor
				#mode teJustLeft
			)
		)
		(localproc_040c 170 222 184 312)
		(= saveBits3
			(DoDisplay {'R' to Read Sign}
				#at 225 173
				#color myTextColor5
				#back myLowlightColor
				#mode teJustLeft
			)
		)
	)
)

(class Condiment of Sq4View
	(properties
		myX 0
		myY 0
		myPri 0
		mySound 0
		staticLoop 0
		staticCel 0
		cursorLoop 0
		cursorCel 0
		targetLoop 0
		targetCel 0
		followWho 0
		condoList 0
	)
	
	(method (doit &tmp userCurEvent)
		(cond 
			((not followWho) 0)
			((IsObject followWho) (self x: (followWho x?) y: (- (followWho y?) 14)))
			((!= theCursor 68) (self rePosn:))
			(
				(not
					(& ((= userCurEvent (User curEvent?)) type?) $0007)
				)
				(GlobalToLocal userCurEvent)
				(self x: (userCurEvent x?) y: (+ (userCurEvent y?) 10))
			)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb theIcon)
		(switch theVerb
			(V_DO (GrabCondiment self))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (rePosn)
		(self
			x: myX
			y: myY
			loop: staticLoop
			cel: staticCel
			setPri: myPri
			followWho: 0
		)
	)
	
	(method (follow theFollowWho)
		(= followWho theFollowWho)
		(if (IsObject followWho)
			(condoList addToFront: self)
			(if mySound (mySound play:))
			(self
				setPri: (+ (condoList size?) 1)
				followWho: theFollowWho
				loop: targetLoop
				cel: targetCel
			)
			(keyHandler setTarget: 0)
		else
			(self loop: cursorLoop cel: cursorCel setPri: 15)
			(= oldCur (theGame setCursor: 68 1))
		)
	)
)

(class BurgerKeys of Set
	(properties
		curFeat 0
		curLevel 0
		target 0
	)
	
	(method (init)
		(directionHandler addToFront: self)
		(self restart:)
	)
	
	(method (dispose)
		(directionHandler delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp evt)
		(= evt (event message?))
		(event claimed: TRUE)
		(cond 
			(
				(or
					(not (& (event type?) direction))
					(not (User canInput:))
				)
				(event claimed: FALSE)
			)
			((OneOf evt dirN dirS UPARROW DOWNARROW) (self changeLevel:))
			((OneOf evt dirE RIGHTARROW) (self advance:))
			((OneOf evt dirW LEFTARROW) (self retreat:))
		)
	)
	
	(method (advance &tmp newFeat)
		(= newFeat (self at: (+ 1 (self indexOf: curFeat))))
		(if (not (IsObject newFeat))
			(= newFeat (NodeValue (self first:)))
		)
		(= curFeat newFeat)
		(self changeLevel: 0)
	)
	
	(method (retreat &tmp newFeat)
		(= newFeat (self at: (- (self indexOf: curFeat) 1)))
		(if (not (IsObject newFeat))
			(= newFeat (NodeValue (self last:)))
		)
		(= curFeat newFeat)
		(self changeLevel: 0)
	)
	
	(method (changeLevel newLevel)
		(if
		(= curLevel (if argc newLevel else (not curLevel)))
			(= curLevel 1)
			(self setTarget: followTarget)
		else
			(= curLevel 0)
			(self setTarget: 0)
			(theGame
				setCursor: theCursor TRUE (curFeat x?) (curFeat y?)
			)
		)
	)
	
	(method (setTarget param1)
		(if (not argc) (= param1 0))
		(target code: param1)
		(if param1 (param1 doit:))
	)
	
	(method (restart)
		(= curFeat (NodeValue (self first:)))
		(self changeLevel: 0)
	)
)

(instance followTarget of Code
	(properties)
	
	(method (doit &tmp temp0)
		(if (User canInput:)
			(= temp0 ((keyHandler target?) x?))
			(if (> ((keyHandler target?) xStep?) 5)
				(= temp0 (+ temp0 5))
			)
			(theGame
				setCursor: theCursor 1 temp0 (- ((keyHandler target?) y?) 14)
			)
		else
			(keyHandler restart:)
		)
	)
)

(instance instructScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(restoreCode doit:)
				(tPig say: 1 self)
			)
			(2 (tPig say: 2 self))
			(3
				(burgerMusic number: [local8 local7] changeState: play:)
				(client setScript: burgerScript)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
			)
		)
	)
)

(instance readSignCode of Code
	(properties)
	
	(method (doit)
		(SQ4Print 387 1)
		(SQ4Print 387 2)
		(SQ4Print 387 3)
	)
)

(instance burgerScript of Script
	(properties)
	
	(method (changeState newState &tmp theIconBarCurIcon)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(if (not (HaveMouse))
					(theGame
						setCursor: theCursor TRUE
							((keyHandler curFeat?) x?)
							((keyHandler curFeat?) y?)
					)
				)
				(= cycles 1)
			)
			(2
				(grabSound number: 160 play:)
				(dr setCycle: EndLoop self)
			)
			(3
				(dr cel: 0)
				(burger
					show:
					setLoop: 0
					cel: 0
					posn: 14 109
					setMotion: MoveTo 266 109 self
				)
			)
			(4
				(ego setMotion: 0)
				(= theIconBarCurIcon (theIconBar curIcon?))
				(HandsOffCode doit:)
				(theIconBar curIcon: theIconBarCurIcon)
				(theGame setCursor: waitCursor TRUE)
				(keyHandler restart:)
				(burger hide:)
				(= register
					(if
						(and
							(== (cList indexOf: bun) 0)
							(!= (cList indexOf: lettuce) -1)
							(!= (cList indexOf: pickle) -1)
							(!= (cList indexOf: mayo) -1)
							(!= (cList indexOf: must) -1)
						)
						(!= (cList indexOf: cats) -1)
					else
						0
					)
				)
				(lettuce rePosn:)
				(pickle rePosn:)
				(bun rePosn:)
				(mayo rePosn:)
				(must rePosn:)
				(cats rePosn:)
				(cList release:)
				(theGame setCursor: oldCur 1)
				(grabber setLoop: 2 cel: 0 setCycle: EndLoop self)
				(grabSound number: 136 play:)
				(grabSound2 play:)
			)
			(5
				(grabSound number: 138 play:)
				(grabSound2 stop:)
				(burger setLoop: 5 cel: 0 posn: 266 27 show:)
				(grabber setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(6
				(grabber stopUpd:)
				(if register
					(self setScript: goodBurgerScript self)
				else
					(self setScript: rejectScript self)
				)
			)
			(7
				(cond 
					((or (== totalFails 10) (== consecutiveFails 5)) (client setScript: yurHistoryScript))
					((not (-- local2))
						(= local2 (++ local15))
						(if (burger moveSpeed?)
							(burger moveSpeed: (- (burger moveSpeed?) 1))
						else
							(burger xStep: (+ (burger xStep?) 1))
						)
						(if (< (++ local7) 7)
							(burgerMusic number: [local8 local7] changeState: play:)
							(UnLoad SOUND [local8 (- local7 1)])
						)
						(= cycles 1)
					)
					(else (= cycles 1))
				)
			)
			(8
				(HandsOnCode doit:)
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(theIconBar disable: 0)
				(self start: 2 init:)
			)
		)
	)
)

(instance goodBurgerScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 5])
		(switch (= state newState)
			(0
				(DoDisplay saveBits)
				(DoDisplay saveBits2)
				(DoDisplay saveBits3)
				(= totalPay (+ totalPay earnedBuckazoid))
				(restoreCode doit:)
				(ego get: iBuckazoid)
				(= buckazoids (+ buckazoids earnedBuckazoid))
				(= monolithBurgerEarnings (+ monolithBurgerEarnings earnedBuckazoid))
				(= register (burger moveSpeed?))
				(= consecutiveFails 0)
				(burger
					moveSpeed: 0
					setMotion: MoveTo (burger x?) -10 self
				)
			)
			(1
				(burger moveSpeed: register)
				(self dispose:)
			)
		)
	)
)

(instance rejectScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(rejectSign startUpd: setScript: signScript)
				(= seconds 2)
			)
			(2
				(burger setCycle: EndLoop)
				(grabSound number: 139 play:)
				(= seconds 3)
			)
			(3
				(rejectSign setScript: 0 setCycle: 0 cel: 0 stopUpd:)
				(= seconds 2)
			)
			(4
				(++ totalFails)
				(++ consecutiveFails)
				(self dispose:)
			)
		)
	)
)

(instance signScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cel: 2)
				(rejectSound play:)
				(= cycles 5)
			)
			(1
				(client cel: 0)
				(= cycles 5)
			)
			(2 (self init:))
		)
	)
)

(instance yurHistoryScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (curRoom newRoom: 385))
			(1 (self dispose:))
		)
	)
)

(instance burgerMusic of Sound
	(properties
		priority 1
		loop -1
	)
)

(instance rejectSound of Sound
	(properties
		number 101
		priority 2
	)
)

(instance squirtSound of Sound
	(properties
		number 122
		priority 3
	)
)

(instance grabSound of Sound
	(properties
		number 136
		priority 3
	)
)

(instance grabSound2 of Sound
	(properties
		number 137
		priority 3
	)
)

(instance readSignFeat of Sq4Feature
	(properties
		nsTop 168
		nsLeft 230
		nsBottom 187
		nsRight 315
	)
	
	(method (init)
		(keyDownHandler add: self)
		(super init:)
	)
	
	(method (dispose)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if
			(and
				(not (event claimed?))
				(& (event type?) keyDown)
				(OneOf (event message?) 114 82)
			)
			(readSignCode doit:)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (tButton say: 1))
			(else  (readSignCode doit:))
		)
	)
)

(instance cList of List
	(properties)
)

(instance keyHandler of BurgerKeys
	(properties)
)

(instance lettuceBay of Sq4Feature
	(properties
		x 70
		y 131
		nsTop 118
		nsLeft 55
		nsBottom 146
		nsRight 85
		lookStr {Lettuce.}
	)
	
	(method (doVerb theVerb)
		(lettuce doVerb: theVerb)
	)
)

(instance pickleBay of Sq4Feature
	(properties
		x 111
		y 131
		nsTop 118
		nsLeft 95
		nsBottom 146
		nsRight 126
		lookStr 14
	)
	
	(method (doVerb theVerb)
		(pickle doVerb: theVerb)
	)
)

(instance mayoBay of Sq4Feature
	(properties
		x 153
		y 129
	)
)

(instance mustBay of Sq4Feature
	(properties
		x 185
		y 129
	)
)

(instance catsBay of Sq4Feature
	(properties
		x 218
		y 129
	)
)

(instance bunBay of Sq4Feature
	(properties
		x 258
		y 126
		nsTop 118
		nsLeft 245
		nsBottom 146
		nsRight 275
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 16))
			(V_TASTE (narrator say: 5))
			(V_SMELL (narrator say: 6))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance mayo of Condiment
	(properties
		noun MAYO
		view 387
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr {Squeeze me for a plop of mayonnaise.}
		myX 153
		myY 129
		myPri 10
		staticLoop 8
		cursorLoop 9
		targetCel 3
	)
	
	(method (doVerb theVerb theItem)
		(tCONDIM
			noun: MAYO
			modNum: 387
			view: 3876
			z: 400
			mouthOffsetX: 17
			mouthOffsetY: 52
			eyeOffsetX: 17
			eyeOffsetY: 39
			talkerNum: MAYO
		)
		(switch theVerb
			(V_LOOK
				(if (Random 0 10)
					(tCONDIM say: 2)
				else
					(tCONDIM say: 4)
				)
			)
			(V_TASTE (tCONDIM say: 3))
			(V_SMELL (tCONDIM say: 5))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance must of Condiment
	(properties
		view 387
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr {Squeeze me for a plop of mustard.}
		myX 185
		myY 129
		myPri 10
		staticLoop 8
		staticCel 1
		cursorLoop 9
		cursorCel 1
		targetCel 4
	)
	
	(method (doVerb theVerb theItem)
		(tCONDIM
			noun: MUSTARD
			modNum: 387
			view: 3875
			z: 400
			mouthOffsetX: 25
			mouthOffsetY: 53
			eyeOffsetX: 19
			eyeOffsetY: 41
			talkerNum: MUSTARD
		)
		(switch theVerb
			(V_LOOK (tCONDIM say: 2))
			(V_TASTE (tCONDIM say: 3))
			(V_SMELL (tCONDIM say: 4))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance cats of Condiment
	(properties
		noun 28
		view 387
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr {Squeeze me for a plop of catsup.}
		myX 218
		myY 129
		myPri 10
		staticLoop 8
		staticCel 2
		cursorLoop 9
		cursorCel 2
		targetCel 5
	)
	
	(method (doVerb theVerb theItem)
		(tCONDIM
			noun: KETCHUP
			modNum: 387
			view: 3874
			z: 400
			mouthOffsetX: 23
			mouthOffsetY: 55
			eyeOffsetX: 19
			eyeOffsetY: 40
			talkerNum: KETCHUP
		)
		(switch theVerb
			(V_LOOK (tCONDIM say: 2))
			(V_TASTE (tCONDIM say: 3))
			(V_SMELL (tCONDIM say: 4))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance lettuce of Condiment
	(properties
		noun 101
		view 387
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 13
		myX 71
		myY 131
		myPri 10
		staticCel 1
		cursorCel 1
		targetCel 1
	)
	
	(method (doVerb theVerb theItem)
		(tCONDIM
			noun: 101
			modNum: 387
			view: 3873
			z: 400
			mouthOffsetX: 25
			mouthOffsetY: 36
			eyeOffsetX: 30
			eyeOffsetY: 26
			talkerNum: LETTUCE
		)
		(switch theVerb
			(V_LOOK (tCONDIM say: 2))
			(V_TASTE (tCONDIM say: 3))
			(V_SMELL (tCONDIM say: 4))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance pickle of Condiment
	(properties
		noun 102
		view 387
		signal (| ignrAct ignrHrz fixPriOn)
		lookStr 14
		myX 111
		myY 131
		myPri 10
		staticCel 2
		cursorCel 2
		targetCel 2
	)
	
	(method (doVerb theVerb theItem)
		(tCONDIM
			noun: 102
			modNum: 387
			view: 3872
			z: 400
			mouthOffsetX: 13
			mouthOffsetY: 36
			eyeOffsetX: 23
			eyeOffsetY: 18
			talkerNum: PICKLE
		)
		(switch theVerb
			(V_LOOK (tCONDIM say: 2))
			(V_TASTE (tCONDIM say: 3))
			(V_SMELL (tCONDIM say: 4))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance bun of Condiment
	(properties
		noun 100
		view 387
		signal (| ignrAct ignrHrz fixPriOn)
		myX 259
		myY 127
		myPri 10
		staticCel 6
		cursorCel 6
		targetCel 6
	)
	
	(method (doVerb theVerb theItem)
		(tCONDIM
			noun: 100
			modNum: 387
			view: 3871
			z: 400
			mouthOffsetX: 10
			mouthOffsetY: 23
			eyeOffsetX: 19
			eyeOffsetY: 11
			talkerNum: BUN
		)
		(switch theVerb
			(V_LOOK (tCONDIM say: 2))
			(V_TASTE (tCONDIM say: 3))
			(V_SMELL (tCONDIM say: 4))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance burger of Sq4Actor
	(properties
		x 14
		y 109
		view 387
		priority 1
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
		lookStr 17
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (narrator say: 17))
			(V_DO
				(cond 
					((== (lettuce followWho?) 1) (lettuce follow: self) (theGame setCursor: oldCur TRUE))
					((== (pickle followWho?) 1) (pickle follow: self) (theGame setCursor: oldCur TRUE))
					((== (bun followWho?) 1) (bun follow: self) (theGame setCursor: oldCur TRUE))
					((== (mayo followWho?) 1) (mayo follow: self) (theGame setCursor: oldCur TRUE))
					((== (must followWho?) 1) (must follow: self) (theGame setCursor: oldCur TRUE))
					((== (cats followWho?) 1) (cats follow: self) (theGame setCursor: oldCur TRUE))
				)
			)
			(V_TASTE (narrator say: 18))
			(V_SMELL (narrator say: 19))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance dr of Sq4Prop
	(properties
		x 60
		y 115
		view 387
		loop 7
		priority 1
		signal fixPriOn
	)
)

(instance grabber of Sq4Prop
	(properties
		x 255
		y 46
		view 387
		loop 3
		cel 6
		priority 8
		signal (| ignrAct fixPriOn)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 29))
			(V_TASTE (narrator say: 20))
			(V_SMELL (narrator say: 23))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance conveyor of Sq4Prop
	(properties
		x 287
		y 108
		view 387
		loop 6
		priority 1
		signal fixPriOn
	)
)

(instance rejectSign of Sq4Prop
	(properties
		x 186
		y 33
		sightAngle 180
		view 387
		loop 4
		priority 1
		signal fixPriOn
		lookStr 21
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 21))
			(V_TASTE (narrator say: 22))
			(V_SMELL (narrator say: 23))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance belt of Sq4Feature
	(properties
		x 165
		y 91
		nsTop 69
		nsLeft 32
		nsBottom 113
		nsRight 298
		lookStr 24
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 24))
			(V_TASTE (narrator say: 25))
			(V_SMELL (narrator say: 26))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance topCounter of Sq4Feature
	(properties
		x 213
		y 22
		nsLeft 107
		nsBottom 44
		nsRight 319
		lookStr 27
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 27))
			(V_TASTE (narrator say: 28))
			(V_SMELL (narrator say: 28))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance bottomCounter of Sq4Feature
	(properties
		x 163
		y 10
		nsTop 114
		nsLeft 42
		nsBottom 159
		nsRight 285
		lookStr 27
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 27))
			(V_TASTE (narrator say: 28))
			(V_SMELL (narrator say: 28))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance upperPipes of Sq4Feature
	(properties
		x 18
		y 53
		nsTop 38
		nsBottom 69
		nsRight 37
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 33))
			(V_TASTE (narrator say: 29))
			(V_SMELL (narrator say: 30))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance redTube of Sq4Feature
	(properties
		x 5
		y 91
		nsTop 69
		nsBottom 114
		nsRight 11
		lookStr 31
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 31))
			(V_TASTE (narrator say: 29))
			(V_SMELL (narrator say: 30))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance lowerPipes of Sq4Feature
	(properties
		x 18
		y 132
		nsTop 115
		nsBottom 149
		nsRight 36
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK
				(narrator modNum: 70 say: 19)
			)
			(V_TASTE (narrator say: 29))
			(V_SMELL (narrator say: 30))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance hood of Sq4Feature
	(properties
		x 21
		y 92
		nsTop 70
		nsLeft 11
		nsBottom 114
		nsRight 31
		lookStr 32
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(V_LOOK (narrator say: 32))
			(V_TASTE (narrator say: 33))
			(V_SMELL (narrator say: 34))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance tROGER of Sq4Talker
	(properties
		z 400
		noun ROGER
		view 1008
		talkerNum ROGER
		mouthOffsetX 26
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 21
	)
)

(instance tCONDIM of Sq4Talker
	(properties
		z 400
		noun 26
		view 3876
		talkerNum ROGER
		mouthOffsetX 26
		mouthOffsetY 32
		eyeOffsetX 25
		eyeOffsetY 21
	)
)

(instance tButton of Sq4Narrator
	(properties
		noun BUTTON
		talkerNum BUTTON
	)
)

(instance tPig of Sq4Narrator
	(properties
		noun PIGHEAD
		talkerNum PIGHEAD
	)
)
