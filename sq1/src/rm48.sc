;;; Sierra Script 1.0 - (do not remove this comment)
(script# 48)
(include game.sh)
(use Main)
(use Intrface)
(use Osc)
(use RandCyc)
(use Feature)
(use Motion)
(use Game)
(use Invent)
(use Actor)
(use System)

(public
	rm48 0
)

(local
	noMoneyCount
	redeemedCoupon
	defTechPrice =  55
	suxPrice =  40
	navPrice =  45
	[local5 10]
	local15
	local16 =  1
	local17
)

(enum ;droids displayed for sale
	FB-001
	DEF-TECH
	YX-10
	MURAGE
	SUX-9000
	HU-1D
	DALICK
	HA-Y-AO
	MAX-42
	ROC-2000
	NAV-201
)
(procedure (BugSays param1)
	(= [local5 local15] [param1 0])
	(return (++ local15))
)

(procedure (SalesPitch)
	(if (not (sellRobots register?))
		(= local15 0)
		(bugTalker state: 0 seconds: 0)
	)
)

(instance rm48 of Room
	(properties
		picture 48
	)
	
	(method (init)
		(mouseDownHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(super init:)
		(bugMouth init:)
		(antenna init: stopUpd: setScript: wiggleAntenna)
		(rightarm init: setCycle: RandCycle setScript: bugTalker)
		(display init: stopUpd:)
		(readout init: stopUpd:)
		(bug init:)
		(award init:)
		(robotHead init:)
		(robotArm init:)
		(bugButt init:)
		(DBUsign init:)
		(screen init:)
		(diaphram init:)
		(tubes init:)
		(table init:)
		(parts init:)
		(self setScript: sellRobots)
		(theMusic number: 623 loop: -1 play:)
	)
	
	(method (doit)
		(if (-- local16)
			(switch local17
				(0
					(Palette PALCycle 64 73 (- (Random 0 30) 15))
				)
				(1 (Palette PALCycle 64 73 8))
				(2 (Palette PALCycle 64 73 3))
				(3 (Palette PALCycle 64 73 -3))
				(4 (Palette PALCycle 64 73 -8))
			)
		else
			(= local16 (Random 20 50))
			(= local17 (Random 0 4))
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event &rest))
			((not (sellRobots register?)) (bugTalker seconds: 0 cue:) (event claimed: TRUE))
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_WALK))
					(or
						(== (event message?) dirN)
						(& (event type?) direction)
					)
				)
				(curRoom newRoom: 47)
				(event claimed: TRUE)
			)
			(
				(and
					(== (theIconBar curIcon?) (theIconBar at: ICON_ITEM))
					(== (theIconBar curInvIcon?) (Inventory at: iBuckazoid))
					(== (event message?) verbUse)
				)
				(Print 48 0)
			)
		)
	)
)

(instance sellRobots of Script
	(properties)
	
	(method (changeState newState &tmp displayCel)
		(switch (= state newState)
			(0
				(HandsOn)
				(theIconBar disable: ICON_SMELL ICON_TASTE)
				(BugSays
					{Please observe our robot preview screen. If you have any questions, don't hesitate to ask.}
				)
				(if (Btst fBeenInDroidsBUs)
					(BugSays
						{Oh, we're back, are we? Well, perhaps we can manage to take home a droid this time, eh?}
					)
				else
					(BugSays
						{However, I can see that we would be more... ah... interested in our line of economy-priced, used robots, wouldn't we?}
					)
					(BugSays
						{We have a wide variety of work-saving, life- enhancing, shiny-new, factory-fresh, technological wonders of modern engineering wizardry!}
					)
					(BugSays
						{Greetings, sir! Allow me to show you our fine line of robots, otherwise known as "droids".}
					)
				)
				(= cycles 2)
			)
			(1
				(if register
					(= displayCel (display cel?))
					(if
					(and (OneOf (++ displayCel) 10 1 4) (Btst fHaveNavDroid))
						(++ displayCel)
					)
					(display cel: displayCel forceUpd:)
					(readout cel: displayCel forceUpd:)
				)
				(= seconds 3)
			)
			(2 (= start 1) (self init:))
		)
	)
)

(instance bugTalker of Script
	(properties)
	
	(method (doit)
		(if (and local15 (== state 0))
			(sellRobots register: 0)
			(= seconds 0)
			(self cue:)
		)
		(super doit: &rest)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (not local15)
					(HandsOn)
					(theIconBar disable: 4 5)
				else
					(HandsOff)
				)
			)
			(1
				(= register
					(DoDisplay
						[local5 (-- local15)]
						67
						6
						134
						70
						190
						#color colLYellow
						33
						60
					)
				)
				(bugMouth setCycle: Forward)
				(= seconds (+ 1 (/ (StrLen [local5 local15]) 10)))
			)
			(2
				(cond 
					((noMoney register?) (curRoom newRoom: 47))
					((soldDroid register?) (curRoom newRoom: 46))
					(else
						(DoDisplay register)
						(bugMouth setCycle: 0)
						(if (not local15) (sellRobots register: 1))
						(self init:)
					)
				)
			)
		)
	)
)

(instance soldDroid of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SalesPitch)
				(BugSays {Thank you, sir.})
				(BugSays
					{You may pick up your purchase at our convenient Droids-B-Us pickup area, located just out the door and to your right.}
				)
				(= cycles 4)
			)
			(1 (= register 1))
		)
	)
)

(instance noMoney of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(SalesPitch)
				(switch (++ noMoneyCount)
					(1
						(BugSays
							{You could fill out one of our credit applications, although I seriously doubt that you would qualify.}
						)
					)
					(2
						(BugSays
							{I believe that the FloorMart store on Petulence is having a sale on pencil sharpeners that you might just be able to afford!}
						)
					)
					(3
						(BugSays
							{Why can't I ever get a customer with more money than brains? Well... in your case, you might just qualify at that.}
						)
					)
					(else 
						(BugSays
							{Look, why don't you go break your piggy bank open and come back with a little more cash? Now, if you'll excuse me, you really must be going.}
						)
						(= register 1)
					)
				)
				(BugSays
					{Pardon me for saying so, sir, but we don't appear to have enough money for our purchase, now, do we?}
				)
				(self dispose:)
			)
		)
	)
)

(instance bug of Feature
	(properties
		y 189
		description {salesbug}
		onMeCheck $0010
		lookStr {The salesbug appears to be a Chdnarian, a race renowned for their predatory sales techniques. You decide to count your fingers once you've completed your transaction.}
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbTalk
				(SalesPitch)
				(switch (display cel?)
					(FB-001
						(BugSays
							{If you were to insist, I could let you have it for a mere 512 buckazoids (407 with a coupon).}
						)
						(BugSays
							{However, I have only one left, and I put it to work in the warehouse.}
						)
						(BugSays
							{This is an all-purpose household model. It cooks, sews, does windows and brews a little moonshine on the side.}
						)
					)
					(DEF-TECH
						(BugSays
							{Don't bother haggling. This one's worth 55 buckazoids for the parts alone. That would be 46 buckazoids with a coupon.}
						)
						(BugSays
							{That race killed themselves off, however, and spare parts are nearly impossible to find.}
						)
						(BugSays
							{It was produced exclusively for Droids-B-Us by a small planet who used these mechanical warriors to fight their battles.}
						)
						(BugSays
							{Ah! This model is one of my personal favorites.}
						)
					)
					(YX-10
						(BugSays
							{And, if you've got kids, you'll appreciate its low, low price of 698 buckazoids (559 with a coupon).}
						)
						(BugSays
							{If you can live with the paranoia, though, it makes a great baby-sitter.}
						)
						(BugSays
							{Whenever it gets excited, it waves its arms around wildly and yells "DANGER! DANGER!"}
						)
						(BugSays
							{However, the manufacturer had to discontinue it due to a psychological disorder.}
						)
						(BugSays
							{This model was originally designed as a family companion on those long space vacations.}
						)
					)
					(MURAGE
						(BugSays
							{I'd love to sell you one, but every last one I had was snapped up by a movie director from New Japan IV - sorry.}
						)
						(BugSays
							{Although a handsome machine, this robot has a habit of killing people without any real reason.}
						)
						(BugSays
							{Ah, yes, a truly beautiful piece of machinery. This design rates five stars.}
						)
					)
					(SUX-9000
						(BugSays
							{Yes, this dandy, compact unit goes for a mere 29 buckazoids (23 with coupon). You can't go wrong when it's SUX!}
						)
						(BugSays
							{...but of course you wouldn't want to hear about that.}
						)
						(BugSays
							{I just happen to have one of these in stock. It's been completely re-conditioned since its regrettable accident that took the lives of...}
						)
						(BugSays
							{The brand name says it all. SUX is a major manufacturer of refrigerators, dishwashers, and, some day, even time machines!}
						)
					)
					(HU-1D
						(BugSays
							{We've got a set of three, and you can have them all for just 999 buckazoids (799 with a coupon).}
						)
						(BugSays
							{Yessir, they may look like kleenex boxes, but they're built to run a loooong time. And quiet? You bet!}
						)
						(BugSays
							{These little robots are perfect for gardening chores. And they don't mind at all if they're away on space missions for centuries.}
						)
					)
					(DALICK
						(BugSays
							{However, we're fresh out of them right now. Nobody seems to survive long enough to trade them in.}
						)
						(BugSays
							{They're just a wee bit too ambitious is all. And who of us isn't? It's quite an endearing quality, actually.}
						)
						(BugSays
							{Um... This model has had a bad rep, unearned if you ask me.}
						)
					)
					(HA-Y-AO
						(BugSays
							{I could let you have him for just 875 buckazoids (700 with a coupon).}
						)
						(BugSays
							{Of course, they're all in hibernation this time of the century.}
						)
						(BugSays
							{These are really some of the most useful all-purpose robots around, and extremely good with children.}
						)
					)
					(MAX-42
						(BugSays
							{His memory isn't the best, so I'll let you have him for just 512 buckazoids (410 with a coupon).}
						)
						(BugSays
							{If it had one fault, it was probably a tendency to be over- zealous. It's no longer available except for demolition purposes.}
						)
						(BugSays
							{This was one of my better bodyguard models, and it also doubled as a radial-arm saw.}
						)
					)
					(ROC-2000
						(BugSays
							{Uh, well, maybe you shouldn't consider buying this particular model.}
						)
						(BugSays
							{These robots have a bad attitude. You've got to keep them in line, or they'll walk all over you.}
						)
					)
					(NAV-201
						(BugSays
							{I got it from some gambling type who was required to pay-up or perish.}
						)
						(BugSays
							{I ask only 45 buckazoids for it (36 with coupon).}
						)
						(BugSays
							{It will pilot any modern fighter or cruiser. And, it is one of the most experienced droids we carry.}
						)
						(BugSays
							{That model is ideal for flight systems operations.}
						)
					)
				)
			)
			(verbUse
				(switch theItem
					(iCartridge
						(BugSays
							{I'm afraid I can't take data cartridges in lieu of currency. Is sir a bit short of cash, hmmmm?}
						)
					)
					(iGadget
						(BugSays
							{I'm afraid I can't accept your translator in exchange for a droid. Buckazoids work nicely to grease the wheels of commerce, however.}
						)
					)
					(iKnife
						(BugSays
							{Please, sir! Violence is never the answer. Money, on the other hand, is almost always the answer.}
						)
					)
					(iDehydratedWater
						(Print
							{If he drinks from this, you won't. You decide not to offer him any.}
						)
					)
					(iJetpack
						(Print 48 1)
					)
					(iDroidsBUsCoupon
						(SalesPitch)
						(= redeemedCoupon TRUE)
						(ego put: iDroidsBUsCoupon)
						(= defTechPrice (- defTechPrice 9))
						(= suxPrice (- suxPrice 9))
						(= navPrice (- navPrice 9))
						(BugSays
							{Very good, sir. This coupon entitles you to a reduction of twenty percent in the price of any of our fine, previously- owned droids.}
						)
					)
					(10
						(SalesPitch)
						(switch (display cel?)
							(FB-001
								(bugMouth setScript: noMoney)
							)
							(DEF-TECH
								(if (>= buckazoids defTechPrice)
									(= buckazoids (- buckazoids defTechPrice))
									(Bset fBoughtDefTechParts)
									(bugMouth setScript: soldDroid)
								else
									(bugMouth setScript: noMoney)
								)
							)
							(YX-10
								(bugMouth setScript: noMoney)
							)
							(MURAGE
								(BugSays
									{Sorry, sir, but I've sold my last one of those.}
								)
							)
							(SUX-9000
								(if (>= buckazoids suxPrice)
									(= buckazoids (- buckazoids suxPrice))
									(Bset fBoughtSUXDroid)
									(bugMouth setScript: soldDroid)
								else
									(bugMouth setScript: noMoney)
								)
							)
							(HU-1D
								(bugMouth setScript: noMoney)
							)
							(DALICK
								(BugSays
									{Sorry, sir, but we have none of those in stock.}
								)
							)
							(HA-Y-AO
								(bugMouth setScript: noMoney)
							)
							(MAX-42
								(bugMouth setScript: noMoney)
							)
							(ROC-2000
								(BugSays
									{I'm sorry, sir, but my conscience just won't let me sell him to you. Besides, your next of kin might sue.}
								)
							)
							(NAV-201
								(if (>= buckazoids navPrice)
									(= buckazoids (- buckazoids navPrice))
									(Bset fBoughtNavDroid)
									(bugMouth setScript: soldDroid)
								else
									(bugMouth setScript: noMoney)
								)
							)
						)
					)
					(else 
						(BugSays
							{I'm afraid that item can not be exchanged for enough money to purchase one of our quality products. 
							Is sir financially embarassed, or might there be cash in one of those pockets?}
						)
					)
				)
			)
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance antenna of Prop
	(properties
		x 198
		y 60
		description {antenna}
		view 148
		cel 2
		priority 12
		signal (| ignrAct fixPriOn)
		cycleSpeed 30
	)
)

(instance wiggleAntenna of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCycle: Oscillate (Random 1 3) self)
			)
			(1 (= cycles (Random 10 24)))
			(2 (self init:))
		)
	)
)

(instance bugMouth of Prop
	(properties
		x 195
		y 91
		view 148
		loop 1
		priority 8
		signal (| ignrAct fixPriOn)
		cycleSpeed 12
	)
)

(instance rightarm of Prop
	(properties
		x 264
		y 97
		view 148
		loop 2
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 24
		detailLevel 2
	)
)

(instance display of View
	(properties
		x 87
		y 93
		view 248
		signal (| ignrAct fixPriOn)
	)
)

(instance readout of View
	(properties
		x 83
		y 112
		view 248
		loop 1
		signal (| ignrAct fixPriOn)
	)
)

(instance award of Feature
	(properties
		y 189
		description {plaque}
		onMeCheck NEARCHECK
		lookStr {The plaque says: "Salesbug of the year"! You'd better watch your money around this guy.}
	)
)

(instance robotHead of Feature
	(properties
		y 189
		description {robot head}
		onMeCheck FARCHECK
		lookStr {Yikes! It's an Ultron-17, the only robot bad enough to go head-to-head with an Arnoid-1000 Exterminator. You note with relief that he appears to be out of commission, at least temporarily.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbTalk
				(Print 48 2)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance robotArm of Feature
	(properties
		y 189
		description {robot apendage}
		onMeCheck ISNOTHIDDEN
		lookStr {That's an upgrade option for the Arnoid series. It's capable of crushing a person's skull. Very useful if you're in that line of work.}
	)
)

(instance bugButt of Feature
	(properties
		y 200
		description {exit button}
		onMeCheck $0020
		lookStr {Click on this to leave Droids-B-Us.}
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(curRoom newRoom: 47)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance DBUsign of Feature
	(properties
		y 189
		description {sign}
		onMeCheck $0040
		lookStr {The plastoid letters on the wall spell out: "Droids-B-Us"}
	)
)

(instance screen of Feature
	(properties
		y 189
		description {view screen}
		onMeCheck $0080
		lookStr {The viewscreen shows pictures of droids currently available in your rather limited price range.}
	)
)

(instance diaphram of Feature
	(properties
		y 189
		description {diaphram}
		onMeCheck $0100
		lookStr {It looks like some sort of gigantic... diaphragm?}
	)
)

(instance tubes of Feature
	(properties
		y 189
		description {power tubes}
		onMeCheck $0200
		lookStr {These tubes on the wall are power conduits.}
	)
)

(instance table of Feature
	(properties
		y 189
		description {work table}
		onMeCheck $0400
		lookStr {There are many robotic parts scattered about the work table.}
	)
)

(instance parts of Feature
	(properties
		y 189
		description {robot parts}
		onMeCheck $0800
		lookStr {Many parts, bits, and pieces of robotic paraphanalia adorn the walls of this small store.}
	)
)
