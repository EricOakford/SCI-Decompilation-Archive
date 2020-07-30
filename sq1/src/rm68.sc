;;; Sierra Script 1.0 - (do not remove this comment)
(script# 68)
(include game.sh)
(use Main)
(use Intrface)
(use Talker)
(use Osc)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm68 0
)

(instance rm68 of Room
	(properties
		picture 68
	)
	
	(method (init)
		(LoadMany VIEW 44 468 470 472 516)
		(greenBldg init:)
		(twoGuys init:)
		(stadium init:)
		(blueBldg init:)
		(apartments init:)
		(guy1 init:)
		(cheerers init:)
		(bubbleGum init:)
		(guy2 init:)
		(clarkKent init:)
		(Load SOUND 535)
		(ego
			init:
			normal: 0
			view: 44
			cycleSpeed: 3
			setLoop: 0
			cel: 0
			posn: 167 137
			setPri: 8
		)
		(xenonLeader init:)
		(greenGirl init:)
		(ship
			init:
			posn: -100 40
			xStep: 13
			yStep: 6
			setCycle: Forward
		)
		(self setScript: roomControl)
		(xen6 init:)
		(xen9 init:)
		(xen8 init:)
		(xen3 init:)
		(super init:)
		(HandsOff)
	)
)

(instance crowdGoesWild of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(xen6 setCycle: EndLoop self)
				(xen8 setCycle: Forward cycleSpeed: 8)
				(xen9 setCycle: EndLoop)
				(xen3 setCycle: Oscillate 2)
			)
			(1 (= seconds 2))
			(2
				(xen6 setCycle: EndLoop self)
				(xen8 setCycle: Forward cycleSpeed: 8)
				(xen9 setCycle: EndLoop)
				(xen3 setCycle: Oscillate 2)
			)
			(3 (= seconds 2))
			(4
				(xen6 setCycle: EndLoop self)
				(xen8 setCycle: Forward cycleSpeed: 8)
				(xen9 setCycle: EndLoop)
				(xen3 setCycle: Oscillate 2)
			)
			(5 (self dispose:))
		)
	)
)

(instance roomControl of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(self setScript: lottaPraise self)
			)
			(2
				(greenGirl setScript: greenGirlGives)
				(xenonLeader setScript: xenonLeaderReceives self)
			)
			(3
				(Print 68 0 #dispose)
				(= seconds 8)
			)
			(4
				(if modelessDialog (modelessDialog dispose:))
				(Print 68 1 #dispose)
				(= seconds 8)
			)
			(5
				(if modelessDialog (modelessDialog dispose:))
				(Print 68 2 #dispose)
				(= seconds 8)
			)
			(6
				(if modelessDialog (modelessDialog dispose:))
				(self dispose:)
			)
		)
	)
)

(instance lottaPraise of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(leaderTalker
					posn: 120 120
					init: leaderBust leaderMouth leaderEyes
						{"Roger Wilco, we, the people of Xenon, extend our limitless appreciation and eternal gratitude for your acts of heroism."}
						0 0 0 self
				)
			)
			(1
				(leaderTalker
					say:
						{"Because of your bravery, the planet Xenon, and indeed the entire galaxy, has been saved from domination by the evil (not to mention ugly) Sariens."}
						0 0 0 self
				)
				(if (not (ego has: iCartridge))
					(leaderTalker
						say:
							{"Now, if you'd only been smart enough to bring along the data cartridge containing the construction specifications, 
							we'd be able build a new Star Generator, thereby saving Xenon from the horrors of a rapidly cooling planet. 
							Oh, well. You are just a janitor."}
					)
				)
			)
			(2
				(leaderTalker
					say:
						{"It is my honor to present you with the coveted Golden Mop, a symbol of pride and accomplishment to members of your esteemed profession."}
						0 0 0 self
				)
			)
			(3
				(leaderTalker
					say:
						{"Henceforth and for all time you will be known as Hero of Xenon."}
						0 0 1 self
				)
			)
			(4
				(ship setScript: zoomingRockets)
				(self dispose:)
			)
		)
	)
)

(instance greenGirlGives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client cycleSpeed: 10 setCycle: EndLoop self)
			)
			(1
				(client setLoop: 1 cel: 0)
				(greenGirlBodyPart init:)
				(= cycles 3)
			)
			(2 (= cycles (Random 30 150)))
			(3
				(= state 1)
				(greenGirlBodyPart cycleSpeed: 5 setCycle: EndLoop self)
			)
		)
	)
)

(instance xenonLeaderReceives of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(xenonLeader cycleSpeed: 10 setCycle: EndLoop self)
			)
			(1
				(xenonLeader cel: 0 cycleSpeed: 10 setCycle: CycleTo 5 1 self)
			)
			(2
				(ego cycleSpeed: 10 setCycle: EndLoop)
				(xenonLeader setCycle: CycleTo 10 1 self)
			)
			(3
				(ego setLoop: 1 cel: 0)
				(= seconds 3)
			)
			(4
				(ego setCycle: EndLoop)
				(xenonLeader cycleSpeed: 8 cel: 10)
				(= cycles 2)
			)
			(5
				(client setCycle: EndLoop self)
				(ego setScript: egoReceivesAward self)
			)
			(6 (= seconds 3))
			(7 (self dispose:))
		)
	)
)

(instance egoReceivesAward of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fireworks1 init: setScript: rocketsRedGlare1)
				(fireworks2 init: setScript: rocketsRedGlare2)
				(fireworks3 init: setScript: rocketsRedGlare3)
				(= cycles 10)
			)
			(1
				(self setScript: crowdGoesWild self)
			)
			(2 (self dispose:))
		)
	)
)

(instance rocketsRedGlare1 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fire1 number: 534 loop: 1 play:)
				(= ticks (Random 10 60))
			)
			(1
				(fire1 number: 535 loop: 1 play:)
				(client
					show:
					x: (Random 20 300)
					y: (Random 10 60)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(client hide:)
				(= cycles (Random 5 15))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance rocketsRedGlare2 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fire2 number: 534 loop: 1 play:)
				(= ticks (Random 10 60))
			)
			(1
				(fire2 number: 535 loop: 1 play:)
				(client
					show:
					x: (Random 20 300)
					y: (Random 10 60)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(client hide:)
				(= cycles (Random 5 15))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance rocketsRedGlare3 of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(fire3 number: 534 loop: 1 play:)
				(= ticks (Random 10 60))
			)
			(1
				(fire3 number: 535 loop: 1 play:)
				(client
					show:
					x: (Random 20 300)
					y: (Random 10 60)
					cel: 0
					setCycle: EndLoop self
				)
			)
			(2
				(client hide:)
				(= cycles (Random 5 15))
			)
			(3 (self changeState: 0))
		)
	)
)

(instance zoomingRockets of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ship init: setLoop: 10 setMotion: MoveTo 400 40 self)
			)
			(1
				(ship posn: -100 40)
				(= cycles 12)
			)
			(2
				(ship xStep: 10 setLoop: 10 setMotion: MoveTo 215 40 self)
			)
			(3
				(fireworks1 show: setPri: 10 posn: 225 40 setCycle: Oscillate 1)
				(fireworks2 show: setPri: 10 posn: 225 40 setCycle: Oscillate 1)
				(fireworks3 show: setPri: 10 posn: 225 40 setCycle: Oscillate 1)
				(ship setMotion: MoveTo 235 40 self)
			)
			(4
				(explosion init: posn: 230 65 setCycle: EndLoop self)
				(fireworks1 setPri: 1 hide:)
				(fireworks2 setPri: 1 hide:)
				(fireworks3 setPri: 1 hide:)
				(ship setMotion: MoveTo 240 80 ship)
			)
			(5
				(explosion dispose:)
				(self setScript: closingCredits self)
			)
			(6 (self dispose:))
		)
	)
)

(instance closingCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 25))
			(1
				(curRoom newRoom: 2)
				(self dispose:)
			)
		)
	)
)

(instance leaderTalker of Talker
	(properties
		x 270
		y 115
		nsTop 5
		nsLeft 230
		view 516
		loop 1
	)
)

(instance leaderBust of View
	(properties
		view 516
		loop 1
		cel 1
	)
)

(instance leaderMouth of Prop
	(properties
		nsTop 68
		nsLeft 22
		view 516
		loop 5
		cycleSpeed 12
	)
)

(instance leaderEyes of Prop
	(properties
		nsTop 47
		nsLeft 18
		view 516
		loop 3
		cycleSpeed 30
	)
)

(instance xenonLeader of Prop
	(properties
		x 207
		y 135
		description {xenonLeader}
		view 470
		cel 11
		priority 6
		signal (| ignrAct fixPriOn)
	)
)

(instance greenGirl of Prop
	(properties
		x 243
		y 136
		description {greenGirl}
		view 472
		priority 6
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance greenGirlBodyPart of Prop
	(properties
		x 244
		y 113
		description {greenGirlBodyPart}
		view 472
		loop 2
		priority 6
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance king of Prop
	(properties
		x 129
		y 92
		description {king}
		view 468
		priority 7
		signal fixPriOn
		cycleSpeed 6
	)
)

(instance fireworks1 of Prop
	(properties
		x 39
		y 30
		description {fireworks1}
		view 468
		loop 7
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance fireworks2 of Prop
	(properties
		x 246
		y 45
		description {fireworks2}
		view 468
		loop 8
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance fireworks3 of Prop
	(properties
		x 182
		y 39
		description {fireworks3}
		view 468
		loop 9
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
	)
)

(instance ship of Actor
	(properties
		x 250
		y 60
		description {ship}
		view 468
		loop 10
		priority 1
		signal (| ignrAct fixPriOn)
		cycleSpeed 6
		moveSpeed 6
	)
	
	(method (cue)
		(self hide:)
	)
)

(instance explosion of Actor
	(properties
		description {explosion}
		view 468
		loop 12
		priority 1
		signal fixPriOn
		cycleSpeed 6
		moveSpeed 6
	)
)

(instance fire1 of Sound
	(properties)
)

(instance fire2 of Sound
	(properties)
)

(instance fire3 of Sound
	(properties)
)

(instance greenBldg of Feature
	(properties
		description {green bldg}
		sightAngle 45
		onMeCheck $2000
	)
)

(instance twoGuys of Feature
	(properties
		description {two guys}
		sightAngle 45
		onMeCheck $4000
	)
)

(instance stadium of Feature
	(properties
		description {stadium}
		sightAngle 45
		onMeCheck $1000
	)
)

(instance blueBldg of Feature
	(properties
		description {blue bldg}
		sightAngle 45
		onMeCheck $0400
	)
)

(instance apartments of Feature
	(properties
		description {apartment buildings}
		sightAngle 45
		onMeCheck $0200
	)
)

(instance guy1 of Feature
	(properties
		description {guy 1}
		sightAngle 45
		onMeCheck $0800
	)
)

(instance cheerers of Feature
	(properties
		description {cheerers}
		sightAngle 45
		onMeCheck NEARCHECK
	)
)

(instance bubbleGum of Feature
	(properties
		description {bubbleGum chewer}
		sightAngle 45
		onMeCheck FARCHECK
	)
)

(instance guy2 of Feature
	(properties
		description {guy 2}
		sightAngle 45
		onMeCheck $0010
	)
)

(instance clarkKent of Feature
	(properties
		description {clark Kent}
		sightAngle 45
		onMeCheck ISNOTHIDDEN
	)
)

(instance xen6 of Prop
	(properties
		x 124
		y 181
		view 168
		cycleSpeed 6	;EO: added from NRS speed patch

	)
)

(instance xen9 of Prop
	(properties
		x 58
		y 189
		view 268
		cycleSpeed 6	;EO: added from NRS speed patch
	)
)

(instance xen8 of Prop
	(properties
		x 217
		y 159
		view 168
		loop 1
		cycleSpeed 6	;EO: added from NRS speed patch
	)
)

(instance xen3 of Prop
	(properties
		x 314
		y 189
		view 168
		loop 2
		cycleSpeed 6	;EO: added from NRS speed patch		
	)
)
