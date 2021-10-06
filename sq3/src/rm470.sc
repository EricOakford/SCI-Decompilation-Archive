;;; Sierra Script 1.0 - (do not remove this comment)
(script# 470)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	rm470 0
)

(local
	printObj
	lookedAtCounter
	local2
	eyesCel
)
(procedure (FesterTalks)
	(= saveDisabled TRUE)
	(rHand setCycle: 0)
	(= local2 1)
	(balloon setCel: 1)
	(ArmUp changeState: 0)
	(mouth setCycle: Forward)
)

(procedure (localproc_1714 theLoop theCel theX theY theCycle)
	(lHand hide:)
	(lArm setCel: 0)
	(RedrawCast)
	(= local2 1)
	(head cel: 1)
	(eyes posn: 150 58 setCel: 2)
	(lArm view: 81 setLoop: 0 setCel: 0)
	(lHand
		view: 81
		setLoop: theLoop
		setCel: theCel
		posn: theX theY
		setCycle: theCycle
		show:
	)
)

(procedure (localproc_1788)
	(if printObj
		(balloon setCel: 3)
		(cls)
		(= printObj 0)
	)
	(head cel: 0)
	(eyes posn: 140 55 setLoop: 1 setCel: 0)
	(lHand view: 80 setLoop: 6 setCel: 0 posn: 166 80 hide:)
	(lArm view: 80 setLoop: 5 setCel: 0)
	(rHand show: setCycle: Forward)
	(mouth setCel: 6 setCycle: 0)
	(= local2 0)
	(= saveDisabled 0)
)

(procedure (HaggleGem price)
	(return
		(switch
			(Print
				(Format @invStr 470 46 price)
				#icon 81 0 2
				#mode teJustCenter
				#title {Haggle Interface}
				#button { Yes_} 1
				#button { No_} 2
			)
			(1 (return TRUE))
			(2 (return FALSE))
		)
	)
)

(instance rm470 of Room
	(properties
		picture 470
		style IRISOUT
	)
	
	(method (init)
		(User canInput: TRUE canControl: TRUE)
		(Load VIEW 80)
		(Load VIEW 81)
		(Load SOUND 13)
		(super init:)
		(addToPics add: bones robots)
		(addToPics doit:)
		(head init: stopUpd:)
		(eyes init: setScript: EyeBlink)
		(rArm init: stopUpd: setScript: ArmUp)
		(rHand init: setCycle: Forward setScript: MoveEye)
		(lArm init: stopUpd:)
		(lHand init: stopUpd: hide: setScript: EarWax)
		(mouth init:)
		(balloon init: setCel: 3)
		(if (or (== (theMusic state?) 0) (== (theMusic number?) 22))
			(theMusic number: 13 priority: 3 loop: -1 play:)
		)
		(= printObj 0)
		(self setScript: Howdy)
	)
	
	(method (doit &tmp [temp0 30])
		(super doit:)
		(if (and (== (balloon cel?) 1) (not modelessDialog))
			(localproc_1788)
		)
	)
	
	(method (dispose)
		(if printObj (cls))
		(super dispose:)
	)
	
	(method (handleEvent event &tmp item [temp1 50])
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(if (and (== (event message?) ENTER) printObj)
					(localproc_1788)
					(script cue:)
				)
			)
			(saidEvent
				(cond 
					((Said 'look[/area,store]')
						(Print 470 0)
					)
					((Said 'look/counter,box,display,cabinet')
						(Print 470 1)
						(= lookedAtCounter TRUE)
					)
					((Said 'look/head,face,man,blatz,alien,animal,owner')
						(Print 470 2)
					)
					((Said 'look/mouth')
						(Print 470 3)
					)
					((Said 'look/eye')
						(Print 470 4)
					)
					((Said 'look/finger')
						(Print 470 5)
					)
					((Said 'look/mog,head')
						(Print 470 6)
					)
					((Said 'look/rock,crystal')
						(if (ego has: iGlowingGem)
							(Print 470 7)
						else
							(Print 470 8)
						)
					)
					((or (Said 'look/deck,ceiling') (Said 'look<up,down'))
						(Print 470 9)
					)
					((Said 'look/pane')
						(Print 470 10)
					)
					((Said 'look/partition')
						(Print 470 11)
					)
					((Said 'look/glass')
						(Print 470 12)
					)
					((Said 'look/rack')
						(Print 470 13)
					)
					((Said 'look/shirt')
						(Print 470 14)
					)
					((Said 'look/shelf,skull,bone,pilot,android,debris,domino')
						(Print 470 15)
					)
					((Said 'look,read/postcard,card')
						(if (== thePostcard 6)
							(= thePostcard 1)
						else
							(++ thePostcard)
						)
						((ScriptID 471 0) doit:)
					)
					(
						(or
							(Said 'disembark[/area,blatz,store,shop]')
							(Said '/bye')
							(Said 'go<out')
						)
						(curRoom setScript: ByeScript)
					)
					((Said 'ask,converse>')
						(cond 
							(
								(or
									(Said '/dog,(scumsoft<soft)')
									(Said '/blatz/dog,(scumsoft<soft)')
								)
								(curRoom setScript: ScumScript)
							)
							((or (Said '/pestulon') (Said '/blatz/pestulon'))
								(curRoom setScript: PestScript)
							)
							(else
								(event claimed: TRUE)
								(Print 470 16)
							)
						)
					)
					((Said 'break,beat')
						(Print 470 17)
					)
					((Said 'rob,attack')
						(Print 470 18)
					)
					((Said 'kiss')
						(Print 470 19)
					)
					((Said 'wear,(drop<on)/cap')
						(if (ego has: iChickenHat)
							(= wearingChickenHat TRUE)
							(Print 470 20)
						else
							(DontHave)
						)
					)
					((Said 'remove,(get<off)/cap')
						(cond 
							((not (ego has: iChickenHat))
								(DontHave)
							)
							(wearingChickenHat
								(= wearingChickenHat FALSE)
								(Print 470 21)
							)
							(else
								(Print 470 22)
							)
						)
					)
					((Said 'get/skull,bone,android,pilot,mog,debris,head')
						(Print 470 23)
					)
					((Said 'get>')
						(if (= item (inventory firstTrue: #saidMe))
							(cond 
								((item ownedBy: 470)
									(Print 470 23)
								)
								((item ownedBy: ego)
									(Print 470 24)
								)
								(else
									(Print 470 25)
								)
							)
						)
					)
					((Said 'buy/skull,bone,android,pilot,mog,debris,head')
						(Print 470 26)
					)
					((Said 'buy/crystal,rock')
						(if (ego has: iGlowingGem)
							(Print 470 27)
						else
							(Print 470 28)
						)
					)
					((Said 'buy/postcard')
						(Print 470 29)
					)
					((Said 'buy>')
						(if (= item (inventory firstTrue: #saidMe))
							(cond 
								((item ownedBy: 470)
									(if (>= buckazoids 25)
										(Print 470 30)
										(item moveTo: ego)
										(theGame changeScore: 5)
										(= buckazoids (- buckazoids 25))
									else
										(Print 470 31)
									)
								)
								((item ownedBy: ego)
									(Print 470 24)
								)
								(else
									(Print 470 25)
								)
							)
						)
					)
					((Said 'give,drop,sell,display,trade/crystal,crystal[<glowing]')
						(if (ego has: iGlowingGem)
							(if (not haggledWithFester)
								(curRoom setScript: Orium)
							else
								(curRoom setScript: Orium2)
							)
						else
							(Print 470 32)
						)
					)
				)
			)
		)
	)
	
	(method (newRoom n)
		(= saveDisabled FALSE)
		(super newRoom: n)
	)
)

(instance Howdy of Script	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds 2)
			)
			(1
				(FesterTalks)
				(= printObj
					(Print 470 33
						#at -1 1
						#width 280
						#font 600
						#dispose
					)
				)
				(= seconds 15)
			)
			(2
				(localproc_1788)
				(= seconds 8)
			)
			(3
				(client setScript: SalesPitch)
			)
		)
	)
)

(instance PestScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_1788)
				(RedrawCast)
				(FesterTalks)
				(= printObj
					(Print 470 34
						#at -1 1
						#width 280
						#font 600
						#dispose
					)
				)
				(= local2 0)
				(EarWax changeState: 2)
				(= seconds 15)
			)
			(1
				(localproc_1788)
				(curRoom setScript: SalesPitch)
			)
		)
	)
)

(instance ScumScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_1788)
				(RedrawCast)
				(FesterTalks)
				(= printObj
					(Print 470 35
						#at -1 1
						#width 280
						#font 600
						#dispose
					)
				)
				(= local2 0)
				(EarWax changeState: 2)
				(= seconds 15)
			)
			(1
				(localproc_1788)
				(curRoom setScript: SalesPitch)
			)
		)
	)
)

(instance ByeScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(localproc_1788)
				(RedrawCast)
				(FesterTalks)
				(= printObj
					(Print 470 36
						#at -1 1
						#width 280
						#font 600
						#dispose
					)
				)
				(= seconds 10)
			)
			(1
				(localproc_1788)
				(RedrawCast)
				(theMusic fade:)
				(curRoom newRoom: 43)
			)
		)
	)
)

(instance SalesPitch of Script
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if ((inventory at: iOratOnAStick) ownedBy: 470)
					(FesterTalks)
					(localproc_1714 1 0 210 93 Forward)
					(= printObj
						(Print 470 37 #at -1 1 #width 280 #font 600 #dispose)
					)
					(= seconds 15)
				else
					(= cycles 2)
				)
			)
			(2
				(if printObj
					(localproc_1788)
					(= seconds 7)
				else
					(= cycles 2)
				)
			)
			(3
				(if ((inventory at: iChickenHat) ownedBy: 470)
					(FesterTalks)
					(localproc_1714 2 1 217 95 0)
					(= printObj
						(Print 470 38
							#at -1 1
							#width 280
							#font 600
							#dispose
						)
					)
					(= seconds 15)
				else
					(= cycles 2)
				)
			)
			(4
				(if printObj
					(localproc_1788)
					(= seconds 7)
				else
					(= cycles 2)
				)
			)
			(5
				(if ((inventory at: iThermoUnderwear) ownedBy: 470)
					(FesterTalks)
					(localproc_1714 2 0 215 94 0)
					(= printObj
						(Print 470 39
							#at -1 1
							#width 280
							#font 600
							#dispose
						)
					)
					(= seconds 15)
				else
					(= cycles 2)
				)
			)
			(6
				(if printObj
					(localproc_1788)
					(= seconds 7)
				else
					(= cycles 2)
				)
			)
			(7
				(FesterTalks)
				(= printObj
					(Print 470 40
						#at -1 1
						#width 280
						#font 600
						#dispose
					)
				)
				(= seconds 10)
			)
			(8
				(localproc_1788)
				(= seconds 10)
			)
			(9
				(if (== lookedAtCounter TRUE)
					(FesterTalks)
					(= printObj
						(Print 470 41
							#at -1 1
							#width 280
							#font 600
							#dispose
						)
					)
					(= seconds 15)
				else
					(= cycles 2)
				)
			)
			(10
				(if (== lookedAtCounter TRUE)
					(localproc_1788)
					(= seconds 7)
				else
					(= cycles 2)
				)
			)
			(11 (self init:))
		)
	)
)

(instance Orium of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(HandsOff)
				(localproc_1788)
				(= local2 1)
				(= cycles 2)
			)
			(1
				(rock init:)
				(head setCel: 0)
				(eyes setCel: 3 posn: 140 55)
				(mouth setCel: 7)
				(rArm setCel: 2)
				(rHand hide:)
				(lArm setCel: 2)
				(= seconds 4)
			)
			(2
				(rArm setCel: 0)
				(localproc_1788)
				(FesterTalks)
				(= printObj
					(Print 470 42 #at -1 1 #width 280 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(3
				(= haggledWithFester TRUE)
				(localproc_1788)
				(switch (HaggleGem 350)
					(0 (= cycles 2))
					(1
						(ego get: iBuckazoids put: iGlowingGem)
						(+= buckazoids 350)
						(rock dispose:)
						(theGame changeScore: 2)
						(HandsOn)
						(client setScript: SalesPitch)
					)
				)
			)
			(4
				(switch (HaggleGem 400)
					(0 (= cycles 2))
					(1
						(ego get: iBuckazoids put: iGlowingGem)
						(+= buckazoids 400)
						(rock dispose:)
						(theGame changeScore: 4)
						(HandsOn)
						(client setScript: SalesPitch)
					)
				)
			)
			(5
				(switch (HaggleGem 425)
					(0 (= cycles 2))
					(1
						(ego get: iBuckazoids put: iGlowingGem)
						(+= buckazoids 425)
						(rock dispose:)
						(theGame changeScore: 8)
						(HandsOn)
						(client setScript: SalesPitch)
					)
				)
			)
			(6
				(FesterTalks)
				(= printObj
					(Print 470 43 #at -1 1 #width 280 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(7
				(localproc_1788)
				(rock dispose:)
				(HandsOn)
				(= local2 0)
				(client setScript: SalesPitch)
			)
		)
	)
)

(instance Orium2 of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(HandsOff)
				(localproc_1788)
				(= local2 1)
				(= cycles 2)
			)
			(1
				(rock init:)
				(head setCel: 0)
				(eyes setCel: 0 posn: 140 55)
				(FesterTalks)
				(= printObj
					(Print 470 44 #at -1 1 #width 280 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(2
				(localproc_1788)
				(switch (HaggleGem 100)
					(0 (= cycles 2))
					(1
						(ego get: iBuckazoids put: iGlowingGem)
						(= buckazoids (+ buckazoids 100))
						(rock dispose:)
						(theGame changeScore: 1)
						(HandsOn)
						(client setScript: SalesPitch)
					)
				)
			)
			(3
				(FesterTalks)
				(= printObj
					(Print 470 45 #at -1 1 #width 280 #font 600 #dispose)
				)
				(= seconds 8)
			)
			(4
				(localproc_1788)
				(rock dispose:)
				(HandsOn)
				(= local2 0)
				(client setScript: SalesPitch)
			)
		)
	)
)

(instance EyeBlink of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0 (= seconds (Random 4 7)))
			(1
				(if (!= (= eyesCel (eyes cel?)) 3)
					(eyes loop: 8 cel: 255 setCycle: EndLoop self)
				else
					(= cycles 2)
				)
			)
			(2
				(eyes loop: 1 cel: eyesCel)
				(self init:)
			)
		)
	)
)

(instance MoveEye of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0 (= seconds (Random 3 5)))
			(1
				(cond 
					((or printObj (== (eyes cel?) 3)) (self init:))
					((== (eyes cel?) 0) (eyes cel: 1))
					(else (eyes cel: 0))
				)
				(self init:)
			)
		)
	)
)

(instance EarWax of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0 (= seconds (Random 10 25)))
			(1
				(if local2 (self init:) else (= cycles 2))
			)
			(2
				(lArm setCel: 1)
				(lHand show: cycleSpeed: 3 setCycle: EndLoop self)
			)
			(3 (lHand setCycle: BegLoop self))
			(4
				(lHand hide:)
				(lArm setCel: 0)
				(= cycles 1)
			)
			(5 (self init:))
		)
	)
)

(instance ArmUp of Script
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(0
				(if printObj (= seconds (Random 4 12)))
			)
			(1
				(if printObj
					(rHand hide:)
					(rArm setCel: 1)
					(= seconds 2)
				)
			)
			(2
				(rArm setCel: 0)
				(rHand show:)
				(self init:)
			)
		)
	)
)

(instance head of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 0
			setCel: 0
			setPri: 13
			posn: 141 121
		)
	)
)

(instance eyes of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 1
			setCel: 0
			setPri: 14
			posn: 140 55
		)
	)
)

(instance mouth of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 2
			setCel: 6
			setPri: 14
			cycleSpeed: 0
			posn: 142 101
		)
	)
)

(instance rArm of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 3
			setCel: 0
			setPri: 14
			posn: 98 151
		)
	)
)

(instance rHand of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 4
			setCel: 0
			setPri: 14
			posn: 172 150
		)
	)
)

(instance lArm of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 5
			setCel: 0
			setPri: 14
			posn: 166 100
		)
	)
)

(instance lHand of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 6
			setCel: 0
			setPri: 14
			posn: 166 80
		)
	)
)

(instance balloon of Prop
	(method (init)
		(super init:)
		(self
			view: 81
			setLoop: 0
			setCel: 1
			setPri: 15
			posn: 100 53
		)
	)
)

(instance rock of Prop
	(method (init)
		(super init:)
		(self
			view: 80
			setLoop: 7
			setCel: 0
			setPri: 15
			posn: 155 155
		)
	)
)

(instance bones of PicView
	(properties
		y 70
		x 201
		view 74
		priority 3
		signal ignrAct
	)
)

(instance robots of PicView
	(properties
		y 115
		x 208
		view 74
		cel 1
		priority 3
		signal ignrAct
	)
)
