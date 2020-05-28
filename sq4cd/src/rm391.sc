;;; Sierra Script 1.0 - (do not remove this comment)
(script# 391)
(include game.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Sq4Dialog)
(use Sq4Narrator)
(use Sq4Feature)
(use Osc)
(use RandCyc)
(use LoadMany)
(use Window)
(use Sound)
(use Motion)
(use System)

(public
	rm391 0
)

(local
	saveBits
	oldWindow
	oldFont
	[str 500]
)
(procedure (CatalogDisplay theString)
	(curRoom drawPic: 393)
	(Display theString
		p_color myTopBordColor
		p_at 2 12
		p_width 316
		p_font SYSFONT
		p_mode teJustCenter
	)
	(Display &rest
		106 270	;p_width
		p_at 25 32
		p_font SYSFONT
	)
)

(instance rm391 of SQRoom
	(properties
		picture 391
		style FADEOUT
		lookStr 3
	)
	
	(method (init)
		(LoadMany PICTURE 392 393 803)
		(LoadMany SOUND 875 392 393 394 395)
		(Load FONT 0)
		(= oldWindow systemWindow)
		(= oldFont userFont)
		(lEar init: setCycle: Forward)
		(rEar init: setCycle: Forward)
		(theMouth init: setCycle: RandCycle)
		(theEyes init: setCycle: Oscillate)
		(chest init: hide: stopUpd:)
		(music number: 0 loop: 1 vol: 127 stop:)
		(super init:)
		(addToPics add: roboHead eachElementDo: #init doit:)
		(globalSound number: 396 loop: -1 vol: 127 playBed:)
		(robotEarSnd vol: 65)
		(robotEarSnd init: play:)
		(self setScript: welcomeSir)
	)
	
	(method (dispose)
		(music fade:)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_WALK (curRoom newRoom: 390))
			(V_SMELL (narrator say: 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roboTalk of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= saveBits
					(Display register
						p_width 315
						p_at 118 154
						p_mode teJustCenter
						p_font 300
						p_color myTextColor5
						p_save
					)
				)
				(= seconds 3)
			)
			(1
				(Display 391 0 p_restore saveBits)
				(self dispose:)
			)
		)
	)
)

(instance welcomeSir of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(chest show: forceUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance startTerminal of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theIconBar disable:)
				(theIconBar curIcon: (theIconBar at: ICON_DO))
				(theGame setCursor: ((theIconBar curIcon?) cursor?))
				(= systemWindow BlueWindow)
				(= userFont SYSFONT)
				(BlueWindow color: myTextColor11 back: myTextColor19)
				(DrawStatus {___} myLowlightColor 0)
				(cast eachElementDo: #hide)
				(if (== (curRoom style?) SCROLLUP) (curRoom style: SCROLLDOWN))
				(curRoom drawPic: 392)
				(robotEarSnd init: fade:)
				(globalSound number: 396 loop: -1 vol: 127 playBed:)
				(= seconds 1)
			)
			(1
				(if
					(not
						(= register
							(PrintD {__}
								#button {Instructions} doInstructions
								{__}
								#button {Specials} doSpecials
								{__}
								#button {Catalog} doCatalog
								{__}
								#button {Exit} backToRob
								{_}
								#at -1 130
							)
						)
					)
					(= register backToRob)
				)
				(curRoom style: 13)
				(= cycles 2)
			)
			(2
				(music number: (Random 392 395) play:)
				(client setScript: register)
			)
		)
	)
)

(instance doInstructions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(BlueWindow color: myTextColor11)
				(Message MsgGet curRoomNum 2 0 1 1 @str)
				(CatalogDisplay {INSTRUCTIONS} @str 102 myTextColor5)
				(= seconds 2)
			)
			(1
				(if (!= msgType TEXT_MSG) (newRob say: 1))
				(if
					(PrintD
						{__}
						#button {Continue} 0
						{__}
						#button {Return to Top Menu} 1
						{__}
						#at 56 153
					)
					(music number: (Random 392 395) play:)
					(client setScript: startTerminal)
				else
					(music number: (Random 392 395) play:)
					(Message MsgGet curRoomNum 2 0 2 1 @str)
					(CatalogDisplay {INSTRUCTIONS} @str 102 myTextColor5)
					(= seconds 2)
				)
			)
			(2
				(if (!= msgType TEXT_MSG) (newRob say: 2))
				(if
					(PrintD
						{_}
						#button {Return to Top Menu} 0
						{__}
						#button {Limited Time Specials} 1
						{_}
						#at 31 157
					)
					(music number: (Random 392 395) play:)
					(client setScript: doSpecials)
				else
					(music number: (Random 392 395) play:)
					(client setScript: startTerminal)
				)
			)
		)
	)
)

(instance doSpecials of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(BlueWindow
					color: (VGAOrEGA myLftBordColor myBotBordColor)
				)
				(Message MsgGet curRoomNum 2 0 3 1 @str)
				(CatalogDisplay
					{SPECIALS}
					@str
					102
					(VGAOrEGA myTextColor3 myTextColor2)
				)
				(= seconds 2)
			)
			(1
				(if (!= msgType TEXT_MSG) (newRob say: 3))
				(if
					(PrintD
						{_}
						#button {Continue} 0
						{_}
						#button {Return to Top Menu} 1
						{_}
						#at 60 122
					)
					(music number: (Random 392 395) play:)
					(client setScript: startTerminal)
				else
					(music number: (Random 392 395) play:)
					(Message MsgGet curRoomNum 2 0 4 1 @str)
					(CatalogDisplay {SPECIALS} @str
						p_color (VGAOrEGA myTextColor3 myTextColor2)
					)
					(= seconds 2)
				)
			)
			(2
				(if (!= msgType TEXT_MSG) (newRob say: 4))
				(switch
					(PrintD
						#button {Order} 0
						{_}
						#button {To Top Menu} 1
						{_}
						#button {Automated Catalog} 2
						#at 20 135
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - dealers only!__}
							#button {Continue} 1
							#at 60 140
						)
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
					(1
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: doCatalog)
					)
				)
			)
		)
	)
)

(instance doCatalog of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 200])
		(switch (= state newState)
			(0
				0
				(BlueWindow color: (VGAOrEGA myTextColor8 myTextColor7))
				(Message MsgGet curRoomNum 2 0 3 1 @str)
				(CatalogDisplay
					{- Automated Catalog -}
					{\n\n\nWelcome to our Automated Catalog!__Please select from the following departments:_}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(1
				1
				(self
					changeState:
						(PrintD
							{_}
							#button {Electronic Gadgets} 2
							{__}
							#button {The Electronic Mommy} 13
							{_}
							#new
							{_}
							#new
							{_}
							#button {TechnoTots Toy Dept.} 21
							{__}
							#button {Return to Top Menu} 29
							{_}
							#at 22 102
						)
						(music number: (Random 392 395) play:)
				)
			)
			(2
				2
				(Message MsgGet curRoomNum 2 0 7 1 @str)
				(CatalogDisplay
					{- Electronic Gadgets -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(3
				3
				(if (!= msgType TEXT_MSG) (newRob say: 7))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 142
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Back-ordered; none currently available.}
							#new
							#button {Continue} 1
							#at 73 142
						)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(4
				4
				(Message MsgGet curRoomNum 2 0 8 1 @str)
				(CatalogDisplay
					{- Electronic Gadgets -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(5
				5
				(if (!= msgType TEXT_MSG) (newRob say: 8))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 40 152
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Discontinued.__}
							#button {Continue} 1
							#at 60 152
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(6
				6
				(Message MsgGet curRoomNum 2 0 9 1 @str)
				(CatalogDisplay
					{- Electronic Gadgets -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(7
				7
				(if (!= msgType TEXT_MSG) (newRob say: 9))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 142
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Estimated delivery:\nSummer 2735.}
							#new
							#button {Continue} 1
							#at 80 132
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(8
				8
				(Message MsgGet curRoomNum 2 0 10 1 @str)
				(CatalogDisplay
					{- Electronic Gadgets -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(9
				9
				(if (!= msgType TEXT_MSG) (newRob say: 10))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 144
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{You do not possess the necessary number of Buckazoids.}
							#at 60 144
						)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(10
				10
				(Message MsgGet curRoomNum 2 0 11 1 @str)
				(CatalogDisplay
					{- Electronic Gadgets -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(11
				11
				(if (!= msgType TEXT_MSG) (newRob say: 11))
				(switch
					(PrintD
						{_}
						#button
						(if (or (ego has: iPlug) (Btst fPlugInLaptop))
							{Exchange}
						else
							{Order}
						)
						0
						{_}
						#button {Catalog Menu} 1
						{_}
						#button {eturn to Top Menu} 2
						{_}
						#at 5 32
					)
					(0
						(music number: (Random 392 395) play:)
						(cond 
							((or (ego has: iPlug) (Btst fPlugInLaptop))
								(PrintD
									{With many a grind, moan and "Well, I dunno...", the salesbot finally allows you to exchange the plug._}
									#at 60 132
								)
								(self setScript: choosePlug self)
							)
							((>= buckazoids 1999)
								(PrintD
									{With great patience, you insert each and every one of the 1999 buckazoids into the salesbot's coin receptor._}
									#at 60 132
								)
								(if (< (= buckazoids (- buckazoids 1999)) 1)
									(ego put: iBuckazoid)
								)
								(SolvePuzzle fBuyPlug 15)
								(ego get: iPlug)
								(self setScript: choosePlug self)
							)
							(else
								(PrintD
									{You do not possess the necessary number of Buckazoids.}
									#at 60 132
								)
								(= cycles 1)
							)
						)
					)
					(1
						(music number: (Random 392 395) play:)
						(self changeState: 0)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(12 12 (self changeState: 0))
			(13
				(Message MsgGet curRoomNum 2 0 13 1 @str)
				(CatalogDisplay
					{- Electronic Mommy -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(14
				14
				(if (!= msgType TEXT_MSG) (newRob say: 13))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 136
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Recalled by manufacturer.__}
							#button {Continue} 1
							#at 35 146
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(15
				15
				(Message MsgGet curRoomNum 2 0 14 1 @str)
				(CatalogDisplay
					{- Electronic Mommy -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(16
				16
				(if (!= msgType TEXT_MSG) (newRob say: 14))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 125
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Not available in the Spiral Arm.__}
							#new
							#button {Continue} 1
							#at 70 120
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(17
				17
				(Message MsgGet curRoomNum 2 0 15 1 @str)
				(CatalogDisplay
					{- Electronic Mommy -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(18
				18
				(if (!= msgType TEXT_MSG) (newRob say: 15))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to top menu} 2
						{_}
						#at 35 163
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Estimated date of uplink: November 2803.}
							#new
							#button {Continue} 1
							#at 65 145
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(19
				19
				(Message MsgGet curRoomNum 2 0 16 1 @str)
				(CatalogDisplay
					{- Electronic Mommy -}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(20
				20
				(if (!= msgType TEXT_MSG) (newRob say: 16))
				(switch
					(PrintD
						#button {Order} 0
						{_}
						#button {Return to Catalog Menu} 1
						{_}
						#button {Top Menu} 2
						#at 20 155
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Currently under UCC investigation.}
							#new
							#button {Continue} 1
							#at 68 140
						)
						(music number: (Random 392 395) play:)
						(self changeState: 0)
					)
					(1
						(music number: (Random 392 395) play:)
						(self changeState: 0)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(21
				21
				(Message MsgGet curRoomNum 2 0 17 1 @str)
				(CatalogDisplay
					{TechnoTots Toys}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(22
				22
				(if (!= msgType TEXT_MSG) (newRob say: 17))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 40 135
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Sold out.__}
							#button {Continue} 1
							#at 60 135
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(23
				23
				(Message MsgGet curRoomNum 2 0 18 1 @str)
				(CatalogDisplay
					{TechnoTots Toys}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(24
				24
				(if (!= msgType TEXT_MSG) (newRob say: 18))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 135
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Discontinued.__}
							#button {Continue} 1
							#at 50 142
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(25
				25
				(Message MsgGet curRoomNum 2 0 19 1 @str)
				(CatalogDisplay
					{TechnoTots Toys}
					@str
					p_color (VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(26
				26
				(if (!= msgType TEXT_MSG) (newRob say: 19))
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Continue} 1
						{_}
						#button {Return to Top Menu} 2
						{_}
						#at 35 140
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Due to delay in manufacturing, this item\nis not yet available.}
							#new
							#button {Continue} 1
							#at 87 125
						)
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(1
						(music number: (Random 392 395) play:)
						(= cycles 1)
					)
					(2
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(27
				27
				(Message MsgGet curRoomNum 2 0 20 1 @str)
				(CatalogDisplay
					{TechnoTots Toys}
					@str
					102
					(VGAOrEGA myTextColor8 myTextColor7)
				)
				(= seconds 2)
			)
			(28
				28
				(if (!= msgType TEXT_MSG) (newRob say: 20))	;EO: This one was mistakenly not voiced; now it is
				(switch
					(PrintD
						{_}
						#button {Order} 0
						{_}
						#button {Return to Top Menu} 1
						{_}
						#at 60 132
					)
					(0
						(music number: (Random 392 395) play:)
						(PrintD
							{Sorry - Sold out.__}
							#button {Continue} 1
							#at 80 132
						)
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
					(1
						(music number: (Random 392 395) play:)
						(client setScript: startTerminal)
					)
				)
			)
			(29
				29
				(client setScript: startTerminal)
			)
		)
	)
)

(instance backToRob of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ShowStatus 10)
				(= systemWindow oldWindow)
				(= userFont oldFont)
				(curRoom style: FADEOUT drawPic: 391)
				(cast eachElementDo: #show)
				(addToPics add: roboHead eachElementDo: #init doit:)
				(ego hide:)
				(globalSound number: 396 loop: -1 vol: 127 playBed:)
				(robotEarSnd vol: 65)
				(robotEarSnd init: play:)
				(= seconds 3)
			)
			(1
				(chest dispose:)
				(= cycles 2)
			)
			(2
				(self
					setScript: roboTalk self { THANK YOU\nFOR SHOPPING\nHz SO GOOD!}
				)
				(= cycles 5)
			)
			(3
				(HandsOn)
				(curRoom newRoom: 390)
			)
		)
	)
)

(instance choosePlug of Script
	(properties)
	
	(method (changeState newState &tmp i theConnector theX theY)
		(switch (= state newState)
			(0
				(curRoom drawPic: 803 PLAIN)
				(= register (List new:))
				(= i 0)
				(while (< i 10)
					(= theX (+ 120 (* 50 (/ i 5))))
					(= theY (+ 30 (* 30 (mod i 5))))
					((= theConnector (connector new:))
						setCel: i
						x: theX
						y: theY
						init:
					)
					(register addToFront: theConnector)
					(++ i)
				)
				(Display
					{Select the plug you\n wish to purchase.}
					@str
					p_at 97 163
					p_color myTextColor5
				)
			)
			(1
				(register eachElementDo: #dispose)
				(= cycles 2)
			)
			(2
				(register dispose:)
				(self dispose:)
			)
		)
	)
)

(instance theEyes of Sq4Prop
	(properties
		x 162
		y 25
		view 391
		loop 4
		lookStr 5
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DO (narrator say: 6))
			(V_SMELL (narrator say: 1))
			(V_TASTE (narrator say: 2))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance lEar of Sq4Prop
	(properties
		x 79
		y 26
		view 391
		loop 1
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 8))
			(V_SMELL (narrator say: 1))
			;(V_TASTE (narrator say: 2))	;EO: This case already exists, and will probably never be executed
			(V_DO (narrator say: 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance rEar of Sq4Prop
	(properties
		x 245
		y 26
		view 391
		loop 2
		lookStr 7
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 8))
			(V_SMELL (narrator say: 1))
			;(V_TASTE (narrator say: 2))	;EO: This case already exists, and will probably never be executed
			(V_DO (narrator say: 9))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance theMouth of Sq4Prop
	(properties
		x 162
		y 60
		view 391
		loop 3
		lookStr 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 11))
			(V_SMELL (narrator say: 12))
			(V_DO (narrator say: 13))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance roboHead of Sq4Prop
	(properties
		x 160
		y 9
		view 391
		lookStr 14
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 2))
			(V_SMELL (narrator say: 15))
			(V_DO (narrator say: 16))
			(V_TALK (narrator say: 17))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance chest of Sq4View
	(properties
		x 161
		y 189
		view 393
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_TASTE (narrator say: 18))
			(V_SMELL (narrator say: 19))
			(else 
				(curRoom setScript: startTerminal)
			)
		)
	)
	
	(method (onMe param1)
		(return
			(if
				(and
					(<= nsLeft (param1 x?))
					(<= (param1 x?) nsRight)
					(<= nsTop (param1 y?))
				)
				(<= (param1 y?) nsBottom)
			else
				0
			)
		)
	)
)

(instance connector of Sq4View
	(properties
		view 391
		loop 5
		priority 15
		signal fixPriOn
		lookStr 21
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
	)
	
	(method (handleEvent event)
		(if
			(or
				(MousedOn self event)
				(and
					(== (event type?) keyDown)
					(== (event message?) ENTER)
				)
			)
			(event claimed: TRUE)
			((inventory at: iPlug) state: cel)
			(choosePlug cue:)
		)
	)
	
	(method (delete)
		(super delete:)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
	)
)

(instance newRob of Sq4Narrator
	(properties
		noun ROBOCLERK
		modNum 391
		talkerNum ROBOCLERK
		nMsgType 2
	)
)

(instance BlueWindow of SysWindow
	(properties)
)

(instance robotEarSnd of Sound
	(properties
		number 875
		vol 65
		loop -1
	)
)
