;;; Sierra Script 1.0 - (do not remove this comment)
(script# 205)
(include sci.sh)
(use Main)
(use KQ6Print)
(use KQ6Room)
(use Kq6Talker)
(use Kq6Ego)
(use Print)
(use Messager)
(use Scaler)
(use Motion)
(use Actor)
(use System)

(public
	rm205 0
)

(local
	local0
	local1
)
(instance rm205 of KQ6Room
	(properties
		picture 200
	)
	
	(method (init)
		(super init: &rest)
		(theMusic number: 915 loop: -1 play:)
		(theGlobalSound number: 916 loop: -1 play:)
		(= messager demoMessager)
		(= ego ego)
		(royalRing init:)
		(ego init: posn: 168 120)
		(ourCursor
			init:
			illegalBits: 0
			ignoreActors: 1
			ignoreHorizon: 1
		)
		(Cursor showCursor: 0)
		(= msgType 1)
		(theGame handsOff:)
		(self setScript: helpScript)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (handleEvent event)
		(return
			(if
				(and
					(not (event modifiers?))
					(& (event type?) evMOUSEKEYBOARD)
					(not (event claimed?))
				)
				(restartCode doit:)
				(return 1)
			else
				0
			)
		)
	)
)

(instance restartCode of Code
	(properties)
	
	(method (doit param1 &tmp temp0)
		(Cursor showCursor: 1)
		(theGame setCursor: normalCursor)
		(if (and argc param1)
			(= temp0 1)
		else
			(= temp0
				(Print
					posn: 70 70
					font: 4
					addText: 0 0 5 1 0 0
					addButton: 1 0 0 5 3 0 20
					addButton: 0 0 0 5 2 0 38
					init:
				)
			)
		)
		(if temp0
			(theMusic fade:)
			(theGlobalSound fade:)
			(DrawPic 98)
			(mouseDownHandler delete: curRoom)
			(keyDownHandler delete: curRoom)
			(theGame restart: 1)
		else
			(Cursor showCursor: 0)
		)
		(super doit:)
	)
)

(instance ego of Body
	(properties
		modNum 0
		sightAngle 45
		view 900
	)
	
	(method (reset param1 param2)
		(if (> argc 0) (ego loop: param1))
		(ego
			view: (if (> argc 1) param2 else 900)
			signal: 4096
			z: 0
			setLoop: -1
			setLoop: theEgoGroop
			setPri: -1
			setMotion: 0
			illegalBits: 0
			ignoreActors: 0
			ignoreHorizon: 1
			setStep: 5 3
			setCycle: Walk
			normal: 1
			setSpeed: currentSpeed
		)
		(if (and oldScaleSignal (== view 900))
			(cond 
				((& oldScaleSignal $0002) (= scaleSignal oldScaleSignal) (= maxScale oldMaxScale))
				((or oldMaxScale oldBackSize oldFrontY oldBackY)
					(ego
						setScale: Scaler oldMaxScale oldBackSize oldFrontY oldBackY
					)
				)
				(else (ego setScale:))
			)
			(= oldScaleSignal
				(= oldMaxScale
					(= oldBackSize (= oldFrontY (= oldBackY 0)))
				)
			)
		)
	)
)

(instance demoMessager of Messager
	(properties)
	
	(method (findTalker param1 &tmp temp0)
		(= temp0
			(switch param1
				(2 Alex_Demo)
				(else  narrator)
			)
		)
	)
)

(instance helpScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 [temp1 5])
		(switch (= state newState)
			(0 (ego reset: 2) (= cycles 2))
			(1 (messager say: 0 0 6 0 self))
			(2 (messager say: 0 0 0 1 self))
			(3
				(theIconBar disable:)
				(ourCursor setCel: 7 setMotion: MoveTo 23 23 self)
			)
			(4
				(theWalkIcon init: stopUpd:)
				(theHandIcon init: stopUpd:)
				(theLookIcon init: stopUpd:)
				(theTalkIcon init: stopUpd:)
				(theCurInvIcon init: stopUpd:)
				(theCurInvIconMask init: stopUpd:)
				(theInvIcon init: stopUpd:)
				(theControlIcon init: stopUpd:)
				(= cycles 2)
			)
			(5
				(if (FileIO fiEXISTS {g})
					(ourCursor hide:)
					(Cursor showCursor: 1)
					(= temp1 0)
					(= temp0 0)
					(theGame setCursor: normalCursor)
					(while
						(not
							(!=
								(= temp0
									(Print
										font: smallFont
										addText: {Which script?}
										addEdit: @temp1 5 80
										addButton: 1 {1._______Continue_______} 0 20
										addButton: 2 {2._____OtherIcons_______} 0 34
										addButton: 3 {3.____InventoryScript___} 0 48
										addButton: 4 {4.__ControlPanelScript__} 0 62
										init:
									)
								)
								0
							)
						)
					)
					(if temp1 (= temp0 (ReadNumber @temp1)))
					(switch temp0
						(1 (helpScript cue:))
						(2
							(client setScript: otherIconsScript)
						)
						(3
							(client setScript: inventoryScript)
						)
						(4
							(client setScript: controlPanelScript)
						)
					)
					(ourCursor show:)
					(Cursor showCursor: 0)
				else
					(helpScript cue:)
				)
			)
			(6 (messager say: 0 0 0 2 self))
			(7 (messager say: 0 0 0 3 self))
			(8
				(theWalkIcon setCel: 1)
				(= seconds 2)
			)
			(9
				(theWalkIcon setCel: 0)
				(ourCursor setMotion: MoveTo 67 23 self)
			)
			(10
				(messager say: 0 0 0 4 self)
			)
			(11
				(theHandIcon setCel: 1)
				(= seconds 2)
			)
			(12
				(theHandIcon setCel: 0)
				(ourCursor setMotion: MoveTo 108 23 self)
			)
			(13
				(messager say: 0 0 0 5 self)
			)
			(14
				(theLookIcon setCel: 1)
				(= seconds 2)
			)
			(15
				(theLookIcon setCel: 0)
				(ourCursor setMotion: MoveTo 154 23 self)
			)
			(16
				(messager say: 0 0 0 6 self)
			)
			(17
				(theTalkIcon setCel: 1)
				(= seconds 2)
			)
			(18
				(theTalkIcon setCel: 0)
				(ourCursor setMotion: MoveTo 202 23 self)
			)
			(19
				(messager say: 0 0 0 7 self)
			)
			(20
				(ourCursor setMotion: MoveTo 253 23 self)
			)
			(21
				(messager say: 0 0 0 8 self)
			)
			(22
				(theInvIcon setCel: 1)
				(= seconds 2)
			)
			(23
				(theInvIcon setCel: 0)
				(ourCursor setMotion: MoveTo 297 23 self)
			)
			(24
				(messager say: 0 0 0 9 self)
			)
			(25
				(theControlIcon setCel: 1)
				(= seconds 2)
			)
			(26
				(theControlIcon setCel: 0)
				(= cycles 1)
			)
			(27
				(messager say: 0 0 0 10 self)
			)
			(28
				(ourCursor setMotion: MoveTo 23 23 self)
			)
			(29 (= seconds 2))
			(30
				(theWalkIcon setCel: 1)
				(= seconds 2)
			)
			(31
				(theWalkIcon setCel: 0)
				(ourCursor setCel: 0)
				(= cycles 15)
			)
			(32
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(ourCursor setMotion: MoveTo 118 90 self)
			)
			(33
				(messager say: 0 0 0 11 self)
			)
			(34 (= seconds 2))
			(35
				(ourCursor setCel: 1)
				(= seconds 1)
			)
			(36
				(ourCursor setCel: 2)
				(= seconds 1)
			)
			(37
				(ourCursor setCel: 3)
				(= seconds 1)
			)
			(38
				(ourCursor setCel: 0)
				(= seconds 1)
			)
			(39
				(ourCursor setCel: 1)
				(= seconds 1)
			)
			(40
				(messager say: 0 0 0 12 self)
			)
			(41 (= seconds 2))
			(42
				(ourCursor setCel: 0)
				(= seconds 1)
			)
			(43
				(ourCursor setCel: 1)
				(= seconds 1)
			)
			(44
				(ourCursor setCel: 0)
				(= seconds 1)
			)
			(45
				(ourCursor setCel: 1)
				(= seconds 1)
			)
			(46
				(messager say: 0 0 0 13 self)
			)
			(47
				(messager say: 0 0 0 14 self)
			)
			(48
				(ourCursor setMotion: MoveTo 23 23 self)
			)
			(49
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor setCel: 7)
				(= cycles 2)
			)
			(50
				(messager say: 0 0 0 15 self)
			)
			(51 (= seconds 2))
			(52
				(theWalkIcon setCel: 1)
				(= seconds 2)
			)
			(53
				(theWalkIcon setCel: 0)
				(ourCursor setCel: 0)
				(= cycles 15)
			)
			(54
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(ourCursor setMotion: MoveTo 217 158 self)
			)
			(55
				(ego setMotion: MoveTo 217 158 self)
			)
			(56 (= seconds 2))
			(57
				(ourCursor setMotion: MoveTo 168 120 self)
			)
			(58 (= seconds 2))
			(59
				(ego setMotion: MoveTo 168 120 self)
			)
			(60
				(ego reset: 2)
				(= cycles 2)
			)
			(61
				(ourCursor setMotion: MoveTo 108 23 self)
			)
			(62
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor setCel: 7)
				(= cycles 2)
			)
			(63
				(messager say: 0 0 0 16 self)
			)
			(64
				(messager say: 0 0 0 17 self)
			)
			(65
				(theLookIcon setCel: 1)
				(= seconds 2)
			)
			(66
				(theLookIcon setCel: 0)
				(ourCursor setCel: 1)
				(= cycles 15)
			)
			(67
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(ourCursor setMotion: MoveTo 104 134 self)
			)
			(68 (= cycles 2))
			(69
				(messager say: 0 0 0 18 self)
			)
			(70
				(messager say: 0 0 0 19 self)
			)
			(71
				(ourCursor setMotion: MoveTo 67 23 self)
			)
			(72
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor setCel: 7)
				(= cycles 2)
			)
			(73
				(messager say: 0 0 0 20 self)
			)
			(74
				(messager say: 0 0 0 21 self)
			)
			(75
				(theHandIcon setCel: 1)
				(= seconds 2)
			)
			(76
				(theHandIcon setCel: 0)
				(ourCursor setCel: 2)
				(= cycles 15)
			)
			(77
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(ourCursor setMotion: MoveTo 104 131 self)
			)
			(78
				(ego
					moveSpeed: 4
					cycleSpeed: 4
					setMotion: MoveTo 134 136 self
				)
			)
			(79
				(self setScript: takeRingScr self)
			)
			(80
				(ego
					moveSpeed: 4
					cycleSpeed: 4
					setMotion: MoveTo 168 120 self
				)
			)
			(81
				(ego reset: 2)
				(= cycles 2)
			)
			(82
				(messager say: 0 0 0 22 self)
			)
			(83
				(messager say: 0 0 0 23 self)
			)
			(84
				(client setScript: otherIconsScript)
			)
		)
	)
)

(instance otherIconsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ourCursor setMotion: MoveTo 154 23 self)
			)
			(1
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor setCel: 7)
				(= cycles 2)
			)
			(2 (messager say: 0 0 3 1 self))
			(3
				(theTalkIcon setCel: 1)
				(= seconds 2)
			)
			(4
				(theTalkIcon setCel: 0 stopUpd:)
				(ourCursor setCel: 3)
				(= cycles 2)
			)
			(5
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(= cycles 2)
			)
			(6 (messager say: 0 0 3 2 self))
			(7
				(ourCursor setMotion: MoveTo 79 162 self)
			)
			(8 (messager say: 0 0 3 3 self))
			(9 (messager say: 0 0 3 4 self))
			(10
				(ourCursor setMotion: MoveTo 202 23 self)
			)
			(11
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor setCel: 7)
				(= cycles 2)
			)
			(12
				(messager say: 0 0 3 5 self)
			)
			(13
				(client setScript: inventoryScript)
			)
		)
	)
)

(instance inventoryScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ourCursor setMotion: MoveTo 253 23 self)
			)
			(1 (messager say: 0 0 2 1 self))
			(2
				(theInvIcon setCel: 1)
				(= seconds 2)
			)
			(3
				(theInvIcon setCel: 0)
				(= cycles 15)
			)
			(4
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(= cycles 2)
			)
			(5
				(theInvWindow init: setPri: 15 stopUpd: addToPic:)
				(invEye init: stopUpd:)
				(invHand init: stopUpd:)
				(invArrow init: stopUpd:)
				(invTalk init: stopUpd:)
				(invMore init: stopUpd:)
				(invOk init: stopUpd:)
				(= local1 1)
				(= local0 1)
				(= cycles 2)
			)
			(6 (messager say: 0 0 2 2 self))
			(7
				(ourCursor setMotion: MoveTo 86 119 self)
			)
			(8
				(invEye setCel: 1)
				(= seconds 2)
			)
			(9
				(invEye setCel: 0 stopUpd:)
				(ourCursor cel: 1 setMotion: MoveTo 89 76 self)
			)
			(10 (= seconds 2))
			(11
				(messager say: 0 0 2 3 self)
			)
			(12
				(ourCursor setMotion: MoveTo 117 119 self)
			)
			(13
				(messager say: 0 0 2 4 self)
			)
			(14
				(messager say: 0 0 2 5 self)
			)
			(15
				(invHand setCel: 1)
				(= seconds 2)
			)
			(16
				(invHand setCel: 0 stopUpd:)
				(ourCursor cel: 2 setMotion: MoveTo 89 76 self)
			)
			(17
				(messager say: 0 0 2 6 self)
			)
			(18
				(ourCursor setMotion: MoveTo 174 119 self)
			)
			(19 (= seconds 2))
			(20
				(messager say: 0 0 2 7 self)
			)
			(21
				(invTalk setCel: 1)
				(= seconds 2)
			)
			(22
				(invTalk setCel: 0 stopUpd:)
				(ourCursor cel: 3 setMotion: MoveTo 89 76 self)
			)
			(23
				(messager say: 0 0 2 8 self)
			)
			(24
				(ourCursor setCel: 7 setMotion: MoveTo 203 119 self)
			)
			(25 (= seconds 2))
			(26
				(messager say: 0 0 2 9 self)
			)
			(27
				(messager say: 0 0 2 10 self)
			)
			(28
				(invMore setCel: 1)
				(= seconds 2)
			)
			(29
				(invMore setCel: 0 stopUpd:)
				(= cycles 2)
			)
			(30
				(messager say: 0 0 2 11 self)
			)
			(31
				(ourCursor setMotion: MoveTo 146 119 self)
			)
			(32 (= seconds 2))
			(33
				(messager say: 0 0 2 12 self)
			)
			(34
				(invArrow setCel: 1)
				(= seconds 2)
			)
			(35
				(invArrow setCel: 0 stopUpd:)
				(ourCursor cel: 7 setMotion: MoveTo 89 76 self)
			)
			(36
				(ourCursor view: 990 setLoop: 2 cel: 14)
				(= seconds 2)
			)
			(37
				(messager say: 0 0 2 13 self)
			)
			(38
				(messager say: 0 0 2 14 self)
			)
			(39
				(ourCursor setMotion: MoveTo 232 119 self)
			)
			(40
				(messager say: 0 0 2 15 self)
			)
			(41
				(invOk setCel: 1)
				(= seconds 2)
			)
			(42
				(theInvWindow dispose:)
				(invEye dispose:)
				(invHand dispose:)
				(invArrow dispose:)
				(invTalk dispose:)
				(invMore dispose:)
				(invOk dispose:)
				(= local1 0)
				(= local0 0)
				(= cycles 2)
			)
			(43
				(DrawPic (curRoom picture?) dpOPEN_PIXELATION)
				(ourCursor setMotion: MoveTo 202 23 self)
			)
			(44
				(theCurInvIconMask
					view: 990
					setLoop: 2
					ignoreActors: 1
					illegalBits: 0
					ignoreHorizon: 1
					cel: 14
					posn: 199 9
					show:
					stopUpd:
				)
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor view: 998 setLoop: 1 setCel: 7)
				(= cycles 2)
			)
			(45
				(messager say: 0 0 2 16 self)
			)
			(46
				(messager say: 0 0 2 17 self)
			)
			(47
				(messager say: 0 0 2 18 self)
			)
			(48
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(ourCursor
					view: 990
					setLoop: 2
					cel: 14
					setMotion: MoveTo 79 162 self
				)
			)
			(49 (= seconds 2))
			(50
				(messager say: 0 0 2 19 self)
			)
			(51
				(messager say: 0 0 2 20 self)
			)
			(52
				(ourCursor setMotion: MoveTo 168 90 self)
			)
			(53
				(messager say: 0 0 2 21 self)
			)
			(54
				(messager say: 0 0 2 22 self)
			)
			(55
				(messager say: 0 0 2 23 self)
			)
			(56
				(ourCursor setMotion: MoveTo 23 23 self)
			)
			(57
				(theWalkIcon setCel: 2 show: stopUpd:)
				(theHandIcon setCel: 2 show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask
					view: 980
					setLoop: 4
					setCel: 1
					posn: 176 7
					show:
					stopUpd:
				)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor view: 998 setLoop: 1 setCel: 7)
				(= cycles 2)
			)
			(58
				(messager say: 0 0 2 24 self)
			)
			(59
				(client setScript: controlPanelScript)
			)
		)
	)
)

(instance controlPanelScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ourCursor setMotion: MoveTo 297 23 self)
			)
			(1
				(theWalkIcon show: stopUpd:)
				(theHandIcon show: stopUpd:)
				(theLookIcon show: stopUpd:)
				(theTalkIcon show: stopUpd:)
				(theCurInvIcon show: stopUpd:)
				(theCurInvIconMask show: stopUpd:)
				(theInvIcon show: stopUpd:)
				(theControlIcon show: stopUpd:)
				(ourCursor setCel: 7)
				(= cycles 2)
			)
			(2 (messager say: 0 0 1 1 self))
			(3
				(theControlIcon setCel: 1)
				(= seconds 2)
			)
			(4
				(theControlIcon setCel: 0)
				(= seconds 2)
			)
			(5
				(theWalkIcon hide:)
				(theHandIcon hide:)
				(theLookIcon hide:)
				(theTalkIcon hide:)
				(theCurInvIcon hide:)
				(theCurInvIconMask hide:)
				(theInvIcon hide:)
				(theControlIcon hide:)
				(= cycles 2)
			)
			(6
				(= local1 1)
				(theControlPanel init: stopUpd:)
				(saveBut init: stopUpd:)
				(restoreBut init: stopUpd:)
				(restartBut init: stopUpd:)
				(quitBut init: stopUpd:)
				(aboutBut init: stopUpd:)
				(modeBut init: stopUpd:)
				(playBut init: stopUpd:)
				(= cycles 2)
			)
			(7 (messager say: 0 0 1 2 self))
			(8
				(ourCursor setMotion: MoveTo 100 48 self)
			)
			(9 (messager say: 0 0 1 3 self))
			(10
				(saveBut setCel: 1)
				(= seconds 2)
			)
			(11
				(saveBut setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 100 68 self)
			)
			(12
				(messager say: 0 0 1 4 self)
			)
			(13
				(restoreBut setCel: 1)
				(= seconds 2)
			)
			(14
				(restoreBut setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 100 88 self)
			)
			(15
				(messager say: 0 0 1 5 self)
			)
			(16
				(restartBut setCel: 1)
				(= seconds 2)
			)
			(17
				(restartBut setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 100 108 self)
			)
			(18
				(messager say: 0 0 1 6 self)
			)
			(19
				(quitBut setCel: 1)
				(= seconds 2)
			)
			(20
				(= local0 1)
				(quitBut setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 100 128 self)
			)
			(21
				(messager say: 0 0 1 7 self)
			)
			(22
				(aboutBut setCel: 1)
				(= seconds 2)
			)
			(23
				(aboutBut setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 100 148 self)
			)
			(24
				(messager say: 0 0 1 8 self)
			)
			(25
				(playBut setCel: 1)
				(= seconds 2)
			)
			(26
				(playBut setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 187 150 self)
			)
			(27
				(if (& msgType $0002)
					(= seconds 2)
				else
					(KQ6Print
						posn: 10 10
						width: 290
						addText:
							{The MODE button lets you switch from speech to text during play.}
						init: self
					)
				)
			)
			(28
				(modeBut setCel: 1)
				(= seconds 2)
			)
			(29
				(modeBut setLoop: 9 setCel: 0)
				(= seconds 2)
			)
			(30
				(modeBut setCel: 1)
				(= seconds 1)
			)
			(31
				(modeBut setLoop: 8 setCel: 0 stopUpd:)
				(ourCursor setMotion: MoveTo 147 116 self)
			)
			(32
				(messager say: 0 0 1 9 self)
			)
			(33
				(ourCursor setMotion: MoveTo 187 116 self)
			)
			(34
				(messager say: 0 0 1 10 self)
			)
			(35
				(ourCursor setMotion: MoveTo 227 116 self)
			)
			(36
				(messager say: 0 0 1 11 self)
			)
			(37
				(theControlPanel dispose:)
				(saveBut dispose:)
				(restoreBut dispose:)
				(restartBut dispose:)
				(quitBut dispose:)
				(aboutBut dispose:)
				(modeBut dispose:)
				(playBut dispose:)
				(= cycles 2)
			)
			(38
				(= local1 0)
				(client setScript: doneScript)
			)
		)
	)
)

(instance doneScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (messager say: 0 0 4 1 self))
			(1 (messager say: 0 0 4 2 self))
			(2 (messager say: 0 0 4 3 self))
			(3 (messager say: 0 0 4 4 self))
			(4
				(ourCursor hide:)
				(= seconds 2)
			)
			(5 (restartCode doit: 1))
		)
	)
)

(instance objectGlitter of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 5 10)))
			(1
				(= state -1)
				(client cel: 0 setCycle: EndLoop self)
			)
		)
	)
)

(instance takeRingScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (!= (ego loop?) 1)
					(ego setHeading: 315 self)
				else
					(self cue:)
				)
			)
			(1 (= cycles 2))
			(2
				(ego
					normal: 0
					setSpeed: 6
					view: 201
					loop: 4
					cel: 0
					setScale: 0
					setCycle: EndLoop self
				)
				(royalRing dispose:)
			)
			(3
				(ego
					posn: (royalRing approachX?) (royalRing approachY?)
					reset: 7
					setScale: Scaler 100 50 112 57
					get: 39
				)
				(= cycles 2)
			)
			(4
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance ourCursor of Actor
	(properties
		x 120
		y 120
		view 998
		cycleSpeed 0
		moveSpeed 0
	)
	
	(method (init)
		(self setLoop: 1 setPri: 15)
		(super init: &rest)
	)
)

(instance theWalkIcon of Actor
	(properties
		x 4
		y 7
		view 980
		priority 14
		signal $1010
	)
)

(instance theHandIcon of Actor
	(properties
		x 47
		y 7
		view 980
		loop 1
		priority 14
		signal $1010
	)
)

(instance theLookIcon of Actor
	(properties
		x 90
		y 7
		view 980
		loop 2
		priority 14
		signal $1010
	)
)

(instance theTalkIcon of Actor
	(properties
		x 133
		y 7
		view 980
		loop 3
		priority 14
		signal $1010
	)
)

(instance theCurInvIcon of Actor
	(properties
		x 176
		y 7
		view 980
		loop 4
		priority 14
		signal $1010
	)
)

(instance theCurInvIconMask of Actor
	(properties
		x 176
		y 7
		view 980
		loop 4
		cel 1
		priority 14
		signal $1010
	)
)

(instance theInvIcon of Actor
	(properties
		x 238
		y 7
		view 980
		loop 5
		priority 14
		signal $1010
	)
)

(instance theControlIcon of Actor
	(properties
		x 281
		y 7
		view 980
		loop 6
		priority 14
		signal $1010
	)
)

(instance royalRing of Prop
	(properties
		x 104
		y 134
		sightAngle 45
		approachX 134
		approachY 136
		view 202
		loop 2
	)
	
	(method (init)
		(self
			cel: 0
			setCycle: EndLoop
			setScript: (Clone objectGlitter)
		)
		(super init: &rest)
	)
)

(instance theControlPanel of Actor
	(properties
		x 58
		y 20
		view 1205
		priority 15
		signal $0010
	)
	
	(method (init)
		(super init: &rest)
		(self addToPic:)
	)
	
	(method (dispose)
		(super dispose: &rest)
		(DrawPic (curRoom picture?))
	)
)

(instance saveBut of Actor
	(properties
		x 76
		y 36
		view 947
		loop 2
		priority 15
		signal $0010
	)
)

(instance restoreBut of Actor
	(properties
		x 76
		y 56
		view 947
		loop 3
		priority 15
		signal $0010
	)
)

(instance restartBut of Actor
	(properties
		x 76
		y 76
		view 947
		loop 4
		priority 15
		signal $0010
	)
)

(instance quitBut of Actor
	(properties
		x 76
		y 96
		view 947
		loop 5
		priority 15
		signal $0010
	)
)

(instance aboutBut of Actor
	(properties
		x 76
		y 116
		view 947
		loop 6
		priority 15
		signal $0010
	)
)

(instance modeBut of Actor
	(properties
		x 161
		y 136
		view 947
		loop 8
		priority 15
		signal $0010
	)
)

(instance playBut of Actor
	(properties
		x 76
		y 136
		view 947
		loop 7
		priority 15
		signal $0010
	)
)

(instance theInvWindow of Actor
	(properties
		x 63
		y 63
		view 1205
		loop 2
		priority 15
	)
)

(instance invEye of Actor
	(properties
		x 86
		y 121
		view 901
		loop 2
		priority 15
		signal $0010
	)
)

(instance invHand of Actor
	(properties
		x 116
		y 121
		view 901
		priority 15
		signal $0010
	)
)

(instance invArrow of Actor
	(properties
		x 145
		y 121
		view 901
		loop 4
		priority 15
		signal $0010
	)
)

(instance invTalk of Actor
	(properties
		x 174
		y 121
		view 901
		loop 5
		priority 15
		signal $0010
	)
)

(instance invMore of Actor
	(properties
		x 203
		y 121
		view 901
		loop 6
		priority 15
		signal $0010
	)
)

(instance invOk of Actor
	(properties
		x 232
		y 121
		view 901
		loop 3
		priority 15
		signal $0010
	)
)

(instance Alex_Demo of Kq6Talker
	(properties
		view 1205
		loop 1
		disposeWhenDone 1
		name "Alex Demo"
	)
	
	(method (init)
		(if local1
			(if local0
				(self
					cel: 1
					x: 167
					y: 75
					talkWidth: 290
					textX: -158
					textY: -65
					keepWindow: 0
				)
			else
				(self
					cel: 1
					x: 167
					y: 75
					talkWidth: 290
					textX: -158
					textY: 45
					keepWindow: 0
				)
			)
			(super init: 0 0 0 &rest)
		else
			(self
				cel: 1
				x: 167
				y: 75
				talkWidth: 290
				textX: -158
				textY: 32
				keepWindow: 0
			)
			(super init: 0 0 tMouthA &rest)
		)
	)
)

(instance tMouthA of Prop
	(properties
		view 1205
		loop 1
	)
)
