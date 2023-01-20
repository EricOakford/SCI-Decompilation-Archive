;;; Sierra Script 1.0 - (do not remove this comment)
(script# 40)
(include sci.sh)
(use Main)
(use Intrface)
(use SQRoom)
(use Talker)
(use Polygon)
(use Feature)
(use LoadMany)
(use Sound)
(use Motion)
(use User)
(use Actor)
(use System)

(public
	rm40 0
)

(local
	nearShip
	[holePts 16] = [178 169 196 162 222 160 256 164 265 168 253 177 220 182 189 178]
	[shipPts 16] = [178 171 177 155 222 150 264 157 269 170 242 170 232 177 201 170]
	gEgoEdgeHit
	makingPitch
)
(instance rm40 of SQRoom
	(properties
		lookStr {You are on Tiny's Used Spaceship lot. There are only a few interesting ships to choose from.}
		picture 40
		horizon 133
		north 45
		east 41
	)
	
	(method (init)
		(holePoly points: @holePts size: 8)
		(shipPoly points: @shipPts size: 8)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init: 0 0 241 0 179 126 155 149 111 146 75 152 76 163 0 167
					yourself:
				)
				((Polygon new:)
					type: 2
					init: 319 172 308 172 306 166 277 163 253 147 291 144 319 146
					yourself:
				)
		)
		(LoadMany 128 27 514)
		(Load rsSOUND 603)
		(if (Btst 18)
			(self addObstacle: holePoly)
		else
			(self addObstacle: shipPoly)
		)
		(switch prevRoomNum
			(north
				(= style 10)
				(ego posn: 208 (+ horizon 5))
			)
			(east
				(= style 11)
				(ego x: 300)
				(HandsOn)
			)
			(else 
				(= style 8)
				(ego posn: 160 180)
				(HandsOn)
			)
		)
		(tinysign setCycle: Fwd init:)
		(radar setCycle: Fwd init:)
		(usedsign setCycle: Fwd init:)
		(allOfSign init:)
		(store init:)
		(banners init:)
		(pinkShip init:)
		(greenShip init:)
		(distantShips init:)
		(self setRegions: 702)
		(ego init:)
		(super init:)
		(if (Btst 18)
			(hole init: addToPic:)
			(ladder
				view: 140
				loop: 2
				cel: 1
				init:
				setPri: 8
				addToPic:
			)
		else
			(shiplid init: stopUpd:)
			(ship init: stopUpd:)
		)
		(if (not (Btst 19)) (tiny setScript: tinySellsShip))
		(if (not (Btst 27))
			(if (!= prevRoomNum north) (Bclr 20))
			(tiny init: stopUpd:)
			(arm init:)
		)
		(theMusic2 number: 608 loop: -1 flags: 1 play:)
	)
	
	(method (doit)
		(if (and nearShip (= gEgoEdgeHit (ego edgeHit?)))
			(ego edgeHit: 0)
			(curRoom setScript: sayBye)
			(= nearShip 0)
		else
			(super doit:)
		)
	)
	
	(method (dispose)
		(Bset 28)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 40 0))
			(5
				(if (Btst 27) (Print 40 1) else (Print 40 2))
			)
			(12 (Print 40 3))
			(11 (Print 40 4))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
	
	(method (newRoom n)
		(if (not (Btst 20)) (theMusic2 fade:))
		(super newRoom: n)
	)
)

(instance tinySellsShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 2))
			(1
				(if (not (Btst 20))
					(tinyTalker
						init: tinyBust tinyEye tinyMouth 140 0 0 1 self
					)
				else
					(Bclr 20)
					(self cue:)
				)
			)
			(2
				(cond 
					((Btst 19)
						(tinyTalker
							init: tinyBust tinyEye tinyMouth 140 1 0 1 self
						)
					)
					((Btst 28)
						(tinyTalker
							init: tinyBust tinyEye tinyMouth 140 2 0 1 self
						)
						(= register 1)
					)
					(else (= register 1) (self cue:))
				)
			)
			(3
				(if register
					(tinyTalker
						init: tinyBust tinyEye tinyMouth 140 3 0 1 self
					)
					(= register 0)
				else
					(self cue:)
				)
			)
			(4
				(= nearShip 1)
				(= seconds 7)
			)
			(5
				(if (not (Btst 20))
					(tinyTalker
						init: tinyBust tinyEye tinyMouth 140 4 0 1 self
					)
				else
					(self dispose:)
				)
			)
			(6 (self dispose:))
		)
	)
)

(instance sayBye of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if (== gEgoEdgeHit 1)
					(if makingPitch
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{OK! Let's go take a look at those beauties!}
								0
								0
								1
								self
						)
					else
						(tinyTalker
							init: tinyBust tinyEye tinyMouth 140 5 0 1 self
						)
					)
					(Bset 20)
				else
					(tinyTalker
						init: tinyBust tinyEye tinyMouth 140 6 0 1 self
					)
				)
			)
			(1 (self dispose:))
		)
	)
)

(instance getIntoShip of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(tinyTalker
					init: tinyBust tinyEye tinyMouth 140 7 0 1 self
				)
			)
			(1
				(Print 40 5)
				(ego
					ignoreActors: 1
					illegalBits: 0
					posn: 216 137
					view: 27
					setLoop: 0
					setPri: 14
					cycleSpeed: 12
					setCycle: End self
				)
			)
			(2
				(shiplid view: 240 cel: 0 setCycle: End self)
				(ego hide:)
			)
			(3
				(soundFx number: 603 loop: 1 play:)
				(= seconds 2)
			)
			(4
				(theMusic2 fade: 0 10 10 1)
				(Print 40 6)
				(= seconds 3)
			)
			(5
				(soundFx number: 609 loop: 1 play: hold: 1)
				(shiplid hide:)
				(shiptail init:)
				(ship view: 240 loop: 3 setCycle: Fwd)
				(shiptail setCycle: Fwd)
				(ladder init: setCycle: CT 1 1 self)
			)
			(6
				(ladderSound loop: 0 play:)
				(ladder cel: 2 setCycle: End self)
			)
			(7
				(ladder setPri: 8 stopUpd:)
				(= seconds 6)
			)
			(8
				(soundFx hold: 0 fade:)
				(shiptail hide:)
				(ship loop: 4 cel: 0 setCycle: End self)
			)
			(9
				(ship dispose:)
				(hole init: stopUpd:)
				(Bset 18)
				(Bset 24)
				(= seconds 5)
			)
			(10
				(theMusic2
					number: 608
					loop: -1
					play: 30
					fade: 127 25 20 0
				)
				(ego
					ignoreActors: 1
					illegalBits: 0
					show:
					posn: 196 177
					view: 27
					setLoop: 1
					cel: 0
					setCycle: End self
				)
			)
			(11
				(NormalEgo 0 1 61)
				(ego loop: 5 heading: 225)
				((curRoom obstacles?) delete: shipPoly)
				(curRoom addObstacle: holePoly)
				(= cycles 12)
			)
			(12 (Print 40 7) (= seconds 2))
			(13
				(tinyTalker
					init: tinyBust tinyEye tinyMouth 140 8 0 0 self
				)
			)
			(14
				(tinyTalker say: 140 9 0 1 self)
			)
			(15 (HandsOn) (self dispose:))
		)
	)
)

(instance allOfSign of Feature
	(properties
		x 87
		y 10
		onMeCheck $0004
	)
	
	(method (doVerb)
		(tinysign doVerb: &rest)
	)
)

(instance tinysign of Prop
	(properties
		x 87
		y 13
		description {sign}
		lookStr {There is a sign here which indicates that this is Tiny's Used Spaceship Lot.}
		view 140
		loop 1
		cycleSpeed 7
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 40 0))
			(5 (Print 40 2))
			(12 (Print {You can't.}))
			(11 (Print {You can't.}))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance usedsign of Prop
	(properties
		x 104
		y 26
		description {sign}
		onMeCheck $0004
		lookStr {There is a sign here which indicates that this is Tiny's Used Space Ship Lot.}
		view 140
		cycleSpeed 5
		detailLevel 3
	)
	
	(method (doVerb)
		(tinysign doVerb: &rest)
	)
)

(instance store of Feature
	(properties
		description {store}
		onMeCheck $0002
		lookStr {There is a small, sturdy office here which unauthorized personnel are not allowed in.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 40 8))
			(5 (Print 40 9))
			(12 (Print 40 10))
			(11 (Print 40 10))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance banners of Feature
	(properties
		description {sale flags}
		onMeCheck $0010
		lookStr {There are some decorative banners which would seem to indicate that making a purchase at Tiny's can be a festive occasion.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 40 11))
			(5 (Print 40 12))
			(12 (Print {You can't.}))
			(11 (Print {You can't.}))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance pinkShip of Feature
	(properties
		description {pink ship}
		onMeCheck $0008
		lookStr {Who would paint their space ship pink? Anyway, it looks as if Tiny's using this old beast as a source for parts. It definitely won't fly.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 40 13))
			(5 (Print 40 14))
			(12 (Print 40 15))
			(11 (Print 40 16))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance greenShip of Feature
	(properties
		description {green ship}
		onMeCheck $0020
		lookStr {This ship is a real classic - a WalWood WarpBlaster ZX with the original paint job. You've only seen these on old postcards. Too bad it belongs to one of the bar patrons and isn't for sale.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(switch theVerb
			(3 (Print 40 17))
			(5 (Print 40 18))
			(12 (Print 40 19))
			(11 (Print 40 20))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance distantShips of Feature
	(properties
		description {distant ships}
		onMeCheck $0040
		lookStr {You see two ships in the northern part of Tiny's used spacecraft lot.}
	)
	
	(method (doVerb theVerb)
		(= x ((User curEvent?) x?))
		(= y ((User curEvent?) y?))
		(if (OneOf theVerb 3 5 12 11)
			(Print 40 21)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance radar of Prop
	(properties
		x 165
		y 202
		z 30
		description {force field sensor}
		lookStr {The settlement of Ulence Flats is surrounded by these force field generators. They repel such undesirables such as the grell which thrive below the sands. It will allow only airborne vehicles in or out.}
		view 140
		loop 3
		cel 10
		priority 14
		signal $4010
		cycleSpeed 4
		detailLevel 1
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(3 (Print 40 22))
			(5 (Print 40 23))
			(12 (Print 40 22))
			(11 (Print 40 22))
			(else 
				(super doVerb: theVerb theItem)
			)
		)
	)
)

(instance tiny of Actor
	(properties
		x 132
		y 141
		description {Tiny}
		approachX 148
		approachY 149
		lookStr {Impossible to miss is the incredibly rotund owner, Tiny. The jaw jockey is most certainly an unusual-looking being. He looks very much like a fish who has swallowed its fishbowl.}
		view 445
		signal $4000
		cycleSpeed 14
		moveSpeed 14
	)
	
	(method (init)
		(self approachVerbs: 4)
		(super init: &rest)
	)
	
	(method (doVerb theVerb theItem &tmp [temp0 40])
		(switch theVerb
			(5
				(if (Btst 19)
					(tinyTalker
						init:
							tinyBust
							tinyEye
							tinyMouth
							{"I've got a coupl'a other fine ships just to the north of here. Whad'ya say, sport. Shall we go give 'em a gander?"}
							0
							0
							1
					)
					(= makingPitch (= nearShip 1))
				else
					(tinyTalker
						init: tinyBust tinyEye tinyMouth (Format @temp0 40 24 99) 0 0 1
					)
				)
			)
			(3 (Print 40 25))
			(12 (Print 40 26))
			(11 (Print 40 27))
			(4
				(switch theItem
					(10
						(cond 
							((Btst 18) (Print 40 28))
							((Btst 19)
								(tinyTalker
									init:
										tinyBust
										tinyEye
										tinyMouth
										{I'd like to take your money, pal. But, even I wouldn't sell you the same ship twice.}
										0
										0
										1
								)
							)
							(nearShip
								(if (< buckazoids 99)
									(tinyTalker
										init:
											tinyBust
											tinyEye
											tinyMouth
											{"So, what are ya trying to pull? If you don't have the cash you can hit the road. Go on, get out of here. You're wasting my time!"}
											0
											0
											1
									)
								else
									(tinyTalker
										init:
											tinyBust
											tinyEye
											tinyMouth
											{"I tell ya, I think you've made a wise decision. She's a beauty. The keys are in her. If you have any problems don't hesitate to come back and tell us about 'em."}
											0
											0
											1
									)
									(Bset 19)
									(= buckazoids (- buckazoids 99))
								)
							)
							(else (Print 40 29))
						)
					)
					(1
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Thanks, pal. I don't know how long I've wanted one of these.}
								0
								0
								1
						)
						(ego put: 1)
					)
					(0
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{All riiiight! Is this that new Leisure Suit Larry movie?}
								0
								0
								1
						)
						(ego put: 0)
					)
					(15
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{That's real cute. Now can we discuss buying a spaceship? I'm only here to serve you. Talk to me.}
								0
								0
								1
						)
					)
					(4
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{ "Yeah, I've heard about how your kind likes to skin my kind. If you only knew what WE do to monkeys on MY planet, you'd think twice about waving that little sticker in my direction. But, hey, I'd still like to sell you somethin'. What d'ya say?"}
								0
								0
								1
						)
					)
					(5
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Thanks a bunch, guy! This planet really dries me out. I feel like a you-know-what outa water.}
								0
								0
								1
						)
						(ego put: 5)
					)
					(6
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Hey! I'm not responsible. That ship was in A-1 shape when I sold it to you!! I swear it!!! Hey, wait a minute. That's not from any of MY ships. What are you tryin' to pull here?}
								0
								0
								1
						)
					)
					(7
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Right, guy. Like I need a stupid rock. Do I look like I was hatched just yesterday?}
								0
								0
								1
						)
					)
					(9
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Sorry, bud. I only deal in space ships, not sand skimmers.}
								0
								0
								1
						)
					)
					(11
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Sorry, bud. I only deal in space ships, not itsy-bitsy jetpacks.}
								0
								0
								1
						)
					)
					(2
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{Lovely gadget - lovely. So are we gonna make a deal here?. Talk to me.}
								0
								0
								1
						)
					)
					(else 
						(tinyTalker
							init: tinyBust tinyEye tinyMouth {Sorry, pal, but I only take cash.} 0 0 1
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

(instance arm of Prop
	(properties
		x 148
		y 117
		view 445
		loop 2
		cycleSpeed 12
	)
)

(instance hole of View
	(properties
		x 224
		y 167
		description {hole}
		lookStr {There is a hole here. I'm sure you recall its origin. At the bottom is the fine craft you rode to glory in. There is no reason to explore the hole any further.}
		view 140
		loop 2
		priority 13
		signal $4010
	)
	
	(method (doVerb theVerb)
		(if (OneOf theVerb 2 3 1)
			(Print lookStr)
		else
			(switch theVerb
				(3 (Print 40 30))
				(12 (Print 40 31))
				(11 (Print 40 32))
				(5 (Print 40 33))
				(4 (Print 40 34))
				(else  (super doVerb: theVerb))
			)
		)
	)
)

(instance ship of Prop
	(properties
		x 224
		y 167
		description {ship}
		approachX 207
		approachY 171
		lookStr {You are standing near one of Tiny's used spacecraft. Upon close inspection they appear to have quite a few kilolightyears on them.}
		view 340
		priority 13
		signal $4010
		cycleSpeed 6
	)
	
	(method (init)
		(self approachVerbs: 3 1)
		(super init: &rest)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3
				(cond 
					((Btst 19) (curRoom setScript: getIntoShip))
					((cast contains: tiny)
						(tinyTalker
							init:
								tinyBust
								tinyEye
								tinyMouth
								{"Sorry, you can only fly it when you buy it.__Oh, by the way, there are no refunds either.__Company Policy."}
								0
								0
								1
						)
					)
					(else (Print 40 35))
				)
			)
			(12 (Print 40 36))
			(11 (Print 40 37))
			(5 (Print 40 38))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shiplid of Prop
	(properties
		x 224
		y 108
		description {ship lid}
		lookStr {That's the lid that covers and protects you during flight (provided this thing does fly).}
		view 340
		cel 1
		priority 13
		signal $4010
		cycleSpeed 14
	)
	
	(method (doVerb)
		(ship doVerb: &rest)
	)
)

(instance shiptail of Prop
	(properties
		x 224
		y 107
		description {ship tail}
		lookStr {This ship seems to have fins around its upper section. Quite a strange setup, but at least it's cheap.}
		view 240
		loop 1
		priority 13
		signal $4010
		cycleSpeed 3
	)
	
	(method (doVerb)
		(ship doVerb: &rest)
	)
)

(instance ladder of Prop
	(properties
		x 212
		y 167
		z -3
		description {ladder}
		lookStr {It's the ladder you used to climb aboard your mole ship.}
		view 240
		loop 2
		priority 14
		signal $4010
		cycleSpeed 10
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(3 (Print 40 39))
			(12 (Print 40 40))
			(11 (Print 40 41))
			(5 (Print 40 42))
			(else  (super doVerb: theVerb))
		)
	)
)

(instance shipPoly of Polygon
	(properties
		type $0002
	)
)

(instance holePoly of Polygon
	(properties
		type $0002
	)
)

(instance tinyTalker of Talker
	(properties
		x 99
		y 16
		nsTop 5
		nsLeft 11
		view 514
	)
)

(instance tinyBust of View
	(properties
		view 514
		cel 1
	)
)

(instance tinyMouth of Prop
	(properties
		nsTop 44
		nsLeft 29
		view 514
		loop 4
		cycleSpeed 9
	)
)

(instance tinyEye of Prop
	(properties
		nsTop 26
		nsLeft 35
		view 514
		loop 2
		cycleSpeed 30
	)
)

(instance ladderSound of Sound
	(properties
		number 631
	)
)
