;;; Sierra Script 1.0 - (do not remove this comment)
(script# 46)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use PAvoid)
(use Talker)
(use PChase)
(use Polygon)
(use Feature)
(use LoadMany)
(use Grooper)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm46 0
)

(local
	robotPartsHere
	confirmOpenBox
	talkCount
)
(instance rm46 of SQRoom
	(properties
		lookStr {This is the pickup area for Droids-B-Us.}
		picture 46
		east 47
		south 41
		west 45
	)
	
	(method (init)
		(switch prevRoomNum
			(south (= style 10))
			(east
				(ego x: 300)
				(= style 11)
				(HandsOn)
			)
			(west
				(ego x: 20)
				(= style 12)
				(HandsOn)
			)
			(else 
				(= style 8)
				(cond 
					((Btst 32) (= robotPartsHere 1) (self setScript: complexRobot))
					((Btst 44) (LoadMany 132 411 630) (self setScript: rightRobot 0 1))
					((and (Btst 25) (not (Btst 33))) (self setScript: rightRobot))
					(else (HandsOn))
				)
				(if robotPartsHere
					(self
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									319
									179
									289
									176
									260
									170
									230
									161
									187
									146
									173
									140
									173
									150
									139
									152
									135
									145
									119
									145
									106
									140
									86
									140
									83
									126
									102
									116
									94
									110
									84
									107
									87
									97
									0
									97
									0
									0
									319
									0
								yourself:
							)
					)
				else
					(self
						addObstacle:
							((Polygon new:)
								type: 2
								init:
									319
									179
									289
									176
									260
									170
									230
									161
									194
									142
									173
									140
									173
									150
									133
									150
									130
									134
									163
									133
									129
									126
									108
									126
									100
									119
									95
									107
									84
									107
									87
									97
									0
									97
									0
									0
									319
									0
								yourself:
							)
					)
				)
			)
		)
		(if (!= style 8)
			(self
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							319
							179
							289
							176
							260
							170
							230
							161
							194
							142
							173
							140
							173
							150
							133
							150
							123
							130
							108
							126
							100
							119
							95
							107
							84
							107
							87
							97
							0
							97
							0
							0
							319
							0
						yourself:
					)
			)
		)
		(self setRegions: 702)
		(ego init:)
		(super init:)
		(if robotPartsHere
			(addToPics add: box2 box3 eachElementDo: #init doit:)
			(box1 init: stopUpd:)
		)
		(radar init: setCycle: Fwd)
		(robbie init: stopUpd:)
		(store init:)
		(pickupArea init:)
		(pickupSign init:)
		(giraffe init:)
	)
	
	(method (newRoom n)
		(theMusic2 flags: 0)
		(super newRoom: n)
	)
)

(instance complexRobot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 331 186 setMotion: MoveTo 111 162 self)
			)
			(1
				(ego setMotion: MoveTo 113 150 self)
			)
			(2
				(ego setMotion: MoveTo 126 147 self)
			)
			(3
				(theMusic2 number: 622 loop: -1 flags: 1 play:)
				(robbie
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 158 145 self
				)
			)
			(4
				(robbie
					setLoop: 1
					cel: 0
					cycleSpeed: 18
					setCycle: End self
				)
			)
			(5
				(robbie stopUpd:)
				(robbieT
					init:
						robBust
						topGear
						robMouth
						{"Here's your incredibly complex robot, sir.__I sprained my thermodiodinal-tricycler carrying it out here for you.__I hope you're satisfied."}
						0
						0
						0
						self
				)
			)
			(6
				(robbieT
					say:
						{"And I hope you're smarter than you look, or you'll never be able to put it together.__How depressing."}
						0
						0
						1
						self
				)
			)
			(7
				(theMusic2 stop:)
				(Bclr 32)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance blowUpBox of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(Print 46 0)
				(ego setMotion: MoveTo 85 142 self)
			)
			(1 (ego setHeading: 0 self))
			(2
				(ego
					view: 10
					setLoop: 2
					cel: 0
					cycleSpeed: 18
					setCycle: CT 2 1 self
				)
			)
			(3
				(soundFx number: 411 loop: 1 flags: 1 play:)
				(box1
					view: 146
					setLoop: 2
					cel: 0
					cycleSpeed: 16
					setPri: (+ (ego priority?) 1)
					setCycle: CT 2 1 self
				)
			)
			(4
				(ego dispose:)
				(box1 posn: (ego x?) (+ (ego y?) 1) setCycle: End self)
			)
			(5 (= cycles 6))
			(6 (Print 46 1) (= cycles 30))
			(7
				(EgoDead
					948
					0
					0
					{Guess this was a bit beyond your capabilities. Speaking of bits, that's about all that remains of you. Hope you can regroup those molecules and get back to the game.}
				)
			)
		)
	)
)

(instance suxRobotS of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(soundFx number: 630 loop: -1 play:)
				(suxRobot
					init:
					setPri: 9
					setCycle: Walk
					setMotion: MoveTo 64 145 self
				)
			)
			(1
				(soundFx stop:)
				(ego setHeading: 215 self)
			)
			(2
				(ego view: 146 setLoop: 3 cel: 0)
				(suxRobot setLoop: 1 cel: 0)
				(= cycles 3)
			)
			(3
				(suxRobot cel: 1)
				(= cycles 3)
			)
			(4
				(suxRobot cel: 0)
				(= cycles 3)
			)
			(5
				(suxRobot cel: 1)
				(= cycles 3)
			)
			(6
				(suxRobot cel: 0 cycleSpeed: 10 setCycle: CT 2 1 self)
				(ego cycleSpeed: 12 setCycle: End)
			)
			(7
				(soundFx number: 411 loop: 1 play:)
				(suxRobot setCycle: End self)
			)
			(8
				(suxRobot setLoop: 2 cel: 0 cycleSpeed: 15 setCycle: Fwd)
				(= seconds 3)
			)
			(9
				(theMusic2 number: 622 loop: -1 flags: 1 play:)
				(robbieT
					init:
						robBust
						topGear
						robMouth
						{"Oh my. Another shredded customer. I suppose someone will be upset about this. Why do I bother."}
						0
						0
						1
						self
				)
			)
			(10
				(theMusic2 stop:)
				(= seconds 4)
			)
			(11
				(EgoDead
					948
					0
					0
					{You seem to have had trouble maintaining your composure (not to mention your molecular structure). Get yourself together. Unfortunately, you're the universe's only hope.}
				)
			)
		)
	)
)

(instance rightRobot of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego posn: 331 186 setMotion: MoveTo 111 162 self)
			)
			(1
				(ego setMotion: MoveTo 113 156 self)
			)
			(2
				(ego setMotion: MoveTo 126 151 self)
			)
			(3
				(theMusic2 number: 622 loop: -1 flags: 1 play:)
				(robbie
					setLoop: 0
					setCycle: Walk
					setMotion: MoveTo 158 145 self
				)
			)
			(4
				(robbie
					setLoop: 1
					cel: 0
					cycleSpeed: 18
					setCycle: End self
				)
			)
			(5
				(robbie stopUpd:)
				(robbieT
					init:
						robBust
						topGear
						robMouth
						{"Hello, sir.__Your new robot will be here in a moment."}
						0
						0
						1
						self
				)
			)
			(6
				(if register
					(theMusic2 stop:)
					(client setScript: suxRobotS)
				else
					((ScriptID 702 1)
						init:
						posn: 192 128
						illegalBits: 0
						setLoop: -1
						setLoop: (Grooper new:)
						setCycle: Walk
						setMotion: MoveTo 83 132 self
					)
					(ego setAvoider: PAvoider)
				)
			)
			(7
				(theMusic2 stop:)
				((ScriptID 702 1) setLoop: -1)
				(Face (ScriptID 702 1) ego self)
			)
			(8
				(Face ego (ScriptID 702 1) self)
			)
			(9 (= ticks 12))
			(10
				(theMusic2 number: 622 loop: -1 flags: 1 play:)
				(SolvePuzzle 4 157)
				(robbieT
					init:
						robBust
						topGear
						robMouth
						{"Well, there he is, sir, programmed to follow you around like a wimpering little puppy dog. How humiliating."}
						0
						0
						1
						self
				)
			)
			(11
				(theMusic2 stop:)
				((ScriptID 702 1)
					illegalBits: 64
					setMotion: PFollow ego 45
				)
				(Bset 33)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance radar of Prop
	(properties
		x 93
		y 80
		description {force field sensor}
		lookStr {The settlement of Ulence Flats is surrounded by these force field generators. They repel such undesirables such as the grell which thrive below the sands. It will allow only airborne vehicles in or out.}
		view 146
		loop 1
		cel 2
		signal $4000
		cycleSpeed 4
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 46 2))
			(5 (Print 46 3))
			(12 (Print 46 2))
			(11 (Print 46 2))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance robbie of Actor
	(properties
		x 155
		y 137
		description {warehouse robot}
		lookStr {This robot is a very old model.__Somehow, he looks tired and depressed.}
		view 449
		loop 1
		signal $4000
		cycleSpeed 8
		moveSpeed 8
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(5
				(theMusic2 number: 622 loop: -1 flags: 1 play:)
				(switch (++ talkCount)
					(0
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{It's easy for you to be chearful, isn't it? YOU don't have deteriorating flaberthrusters.}
								0
								0
								1
								self
						)
					)
					(1
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{What do you want now? I was just about finished with my internal diagnostics check. Now I'm going to have to recalibrate my photo-electric scanner membranes and begin all over again.}
								0
								0
								1
								self
						)
					)
					(2
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{Carbon-based life-forms can be sooo trying sometimes. If you haven't purchased a robot, I have nothing for you. Now, go away!}
								0
								0
								1
								self
						)
					)
					(3
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{(Sigh!) How depressing. Let's begin again from the start and proceed veeerrrryyyy sssslllooowwwllly. I do NOT want to be disturbed. You ARE disturbing me. GO AWAY! Thank you so much.}
								0
								0
								1
								self
						)
					)
					(6
						(robbieT
							init: robBust topGear robMouth {I'm STILL ignoring you!} 0 0 1 self
						)
					)
					(else 
						(robbieT
							init: robBust topGear robMouth {I'm ignoring you.} 0 0 1 self
						)
					)
				)
			)
			(3
				(theMusic2 number: 622 loop: -1 flags: 1 play:)
				(robbieT
					init:
						robBust
						topGear
						robMouth
						{Do NOT touch me! Carbon-based life-forms are notoriously filthy creatures. Everyone knows that they veritably ooze with metal-tarnishing agents.}
						0
						0
						1
						self
				)
			)
			(4
				(if (OneOf theItem 4 15 10 0 2 5 11 18)
					(theMusic2 number: 622 loop: -1 flags: 1 play:)
				)
				(switch theItem
					(4
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{I am programmed to defend myself against all attacks. Even against such a laughable attempt at destruction as this. It would be a shame to expend the energy required to kill you, don't you agree? Yes, I thought you would.}
								0
								0
								1
								self
						)
					)
					(15
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{Although I am amused at your feeble attempt to modify my magnetic field, I must warn you that I can, and most definitely will, defend myself if need be. Now, please put that thing away. Thank you so much.}
								0
								0
								1
								self
						)
					)
					(10
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{Sorry, sir. I'm not allowed to accept tips.}
								0
								0
								1
								self
						)
					)
					(0
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{It appears to be some sort of data cartridge, sir, but I'm afraid I'm not equipped with the necessary port to access the information it contains.}
								0
								0
								1
								self
						)
					)
					(2
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{You won't need your translation device, sir. I'm fluent in more than three million modes of spoken communication.}
								0
								0
								1
								self
						)
					)
					(5
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{No thank you, sir - I never touch the stuff.}
								0
								0
								1
								self
						)
					)
					(11
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{I've seen a jet pack much like this one reduced to slag after one brief flight. It wasn't a pretty sight.}
								0
								0
								1
								self
						)
					)
					(17
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{Many of our local businesses issue these coupons for discounts through the Ulence Flats Chamber of Commerce. It's done wonders for business around here.}
								0
								0
								1
								self
						)
					)
					(18
						(robbieT
							init:
								robBust
								topGear
								robMouth
								{Many of our local businesses issue thses coupons for discounts through the Ulence Flats Chamber of Commerce. It's done wonders for business around here.}
								0
								0
								1
								self
						)
					)
					(else 
						(super doVerb: theVerb theItem)
					)
				)
			)
			(12 (Print 46 4))
			(11 (Print 46 5))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
	
	(method (cue)
		(theMusic2 stop:)
	)
)

(instance suxRobot of Actor
	(properties
		x 192
		y 128
		description {SUX robot}
		lookStr {What a fine robot you've purchased!}
		view 450
		signal $0800
		cycleSpeed 11
		moveSpeed 11
	)
)

(instance box1 of Prop
	(properties
		x 100
		y 137
		description {box}
		lookStr {Great. Three boxes of robot parts, sporting large signs which read: "Caution: This product contains explosive parts. Not intended for children under three."}
		view 146
		cel 2
		signal $4000
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 3 4 11)
			(switch confirmOpenBox
				(0 (Print 46 6))
				(1 (Print 46 7))
				(2
					(curRoom setScript: blowUpBox)
				)
			)
			(++ confirmOpenBox)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance box2 of PicView
	(properties
		x 125
		y 143
		description {box}
		lookStr {Great. Three boxes of robot parts, sporting large signs which read: "Caution: This product contains explosive parts. Not intended for children under three."}
		view 146
		cel 1
		signal $4000
	)
	
	(method (doVerb)
		(box1 doVerb: &rest)
	)
)

(instance box3 of PicView
	(properties
		x 113
		y 131
		description {box}
		lookStr {Great. Three boxes of robot parts, sporting large signs which read: "Caution: This product contains explosive parts. Not intended for children under three."}
		view 146
		signal $4000
	)
	
	(method (doVerb)
		(box1 doVerb: &rest)
	)
)

(instance pickupArea of Feature
	(properties
		description {Droids-B-Us warehouse and pickup area}
		onMeCheck $0002
		lookStr {This portion of the building is the warehouse where customers come to pick up their purchases.}
	)
	
	(method (doVerb)
		(store doVerb: &rest)
	)
)

(instance store of Feature
	(properties
		description {Droids-B-Us store}
		onMeCheck $0010
		lookStr {That's the Droids-B-Us storefront. Droids-B-Us is known throughout this end of the galaxy for the best deals on new and used psionic and cybernetic systems for home, school, office, and spaceship.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y (+ ((User curEvent?) y?) 30))
		(switch theVerb
			(3 (Print 46 8))
			(5 (Print 46 9))
			(12 (Print 46 10))
			(11 (Print 46 11))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pickupSign of Feature
	(properties
		description {sign above the door}
		onMeCheck $0008
		lookStr {The badly weathered sign says "Droids-B-Us Pickup".}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y (+ ((User curEvent?) y?) 60))
		(switch theVerb
			(3 (Print 46 12))
			(5 (Print 46 13))
			(12 (Print 46 14))
			(11 (Print 46 15))
			(else  (super doVerb: &rest))
		)
	)
)

(instance giraffe of Feature
	(properties
		description {Gerry G}
		onMeCheck $0020
		lookStr {This cheerful fellow is Gerry Giraffazoid, logo, spokesbeing, and C.E.O. of Droids-B-Us, Inc.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y (+ ((User curEvent?) y?) 60))
		(switch theVerb
			(3 (Print 46 12))
			(5 (Print 46 16))
			(12 (Print 46 17))
			(11 (Print 46 18))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance robbieT of Talker
	(properties
		x 6
		y 26
		nsTop 8
		nsLeft 201
		view 518
	)
)

(instance robBust of View
	(properties
		view 518
		cel 1
	)
)

(instance robMouth of Prop
	(properties
		nsTop 53
		nsLeft 27
		view 518
		loop 4
		cycleSpeed 6
	)
)

(instance topGear of Prop
	(properties
		nsTop 8
		nsLeft 39
		view 518
		loop 3
		cycleSpeed 6
	)
)

(instance leftGear of Prop
	(properties
		nsTop 31
		nsLeft 176
		view 518
		loop 1
		cycleSpeed 6
	)
)

(instance rightGear of Prop
	(properties
		nsTop 22
		nsLeft 244
		view 518
		loop 2
		cycleSpeed 6
	)
)
