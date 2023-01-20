;;; Sierra Script 1.0 - (do not remove this comment)
(script# 63)
(include sci.sh)
(use Main)
(use Intrface)
(use Deltaur)
(use SQRoom)
(use PolyPath)
(use Polygon)
(use LoadMany)
(use Sound)
(use Motion)
(use Actor)
(use System)

(public
	rm63 0
)

(instance rm63 of SQRoom
	(properties
		lookStr {You don't really like the way the red floor and green walls clash but, all in all, its a pretty nice looking room.}
		picture 63
		west 61
	)
	
	(method (init)
		(self setRegions: 703)
		(HandsOff)
		(Load rsSOUND 6)
		(LoadMany 128 410 163)
		(features
			add: panelOfLights variousMachines transmission rearViewMirror globes
			eachElementDo: #init
		)
		(self
			addObstacle:
				((Polygon new:)
					type: 2
					init:
						221
						78
						159
						78
						210
						50
						188
						50
						146
						78
						55
						79
						41
						79
						0
						79
						0
						0
						319
						0
						319
						189
						0
						189
						0
						87
						217
						87
					yourself:
				)
		)
		(if (== prevRoomNum west) (ego x: 10) (= style 3))
		(panel setCycle: Fwd init:)
		(orbs setCycle: Fwd setScript: switchit init:)
		(walkingGuard setScript: walkGuard init:)
		(if (!= prevRoomNum west) (ego posn: 195 55))
		(ego init:)
		(super init:)
		(addToPics add: standingGuard eachElementDo: #init doit:)
		(if (!= prevRoomNum west)
			(self setScript: fromDoor)
		else
			(ego ignoreActors: 0)
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (> (theGame detailLevel:) 1)
			(if (not (walkingGuard script?))
				(walkingGuard setScript: walkGuard)
			)
			(if (not (orbs script?)) (orbs setScript: switchit))
		else
			(if (walkingGuard script?)
				(walkingGuard loop: 5 cel: 0 setScript: 0)
			)
			(if (orbs script?)
				(orbs show:)
				(panel show:)
				(orbs setScript: 0)
			)
		)
		(cond 
			((or (self script?) (ego script?)) 0)
			((& (ego onControl: 0) $0002) (self setScript: intoDoor))
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(2
				(if (> ((user curEvent?) y?) 100)
					(= lookStr
						{You don't really like the way the red floor and green walls clash but, all in all, its a pretty nice looking room.}
					)
				else
					(= lookStr
						{You don't really like the way the blue floor and green walls clash but, all in all, its a pretty nice looking room.}
					)
				)
			)
		)
		(super doVerb: theVerb theItem &rest)
	)
)

(instance fromDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setPri: 1 loop: 5 setMotion: PolyPath 160 77 self)
			)
			(1
				(ego ignoreActors: 0 setPri: -1)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance intoDoor of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreActors: 1 setMotion: PolyPath 195 55 self)
			)
			(1 (curRoom newRoom: 62))
		)
	)
)

(instance switchit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= seconds (Random 1 4))
				(orbs show:)
				(beamSound number: 6 loop: -1 play:)
			)
			(1
				(= state (- state 2))
				(beamSound stop:)
				(orbs hide:)
				(= seconds (Random 1 4))
			)
		)
	)
)

(instance walkGuard of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 10)))
			(1
				(walkingGuard
					setCycle: Walk
					setLoop: 1
					setMotion: MoveTo 133 180 self
				)
			)
			(2 (= seconds 3))
			(3
				(= state (- state 4))
				(walkingGuard setLoop: 0 setMotion: MoveTo 350 180 self)
			)
		)
	)
)

(instance panel of Prop
	(properties
		x 264
		y 65
		lookStr {A panel of flashing lights.}
		view 163
		loop 1
		priority 15
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2)
			(super doVerb: theVerb &rest)
		else
			(Print 63 0)
		)
	)
)

(instance orbs of Prop
	(properties
		x 134
		y 130
		lookStr {Fantastic technology... isn't it?}
		view 163
		loop 2
		priority 15
		signal $4010
		cycleSpeed 6
		detailLevel 2
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 2)
			(super doVerb: theVerb &rest)
		else
			(Print 63 0)
		)
	)
)

(instance panelOfLights of RegionFeature
	(properties
		x 254
		y 44
		description {panel of lights}
		onMeCheck $4000
		lookStr {It's a panel of lights.}
		level 4
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance variousMachines of RegionFeature
	(properties
		description {various machines}
		onMeCheck $2000
		lookStr {Since your X-ray Vision Specs have not yet arrived in the mail, you don't even know this room is over here, so quit messing around and do something useful.}
		level 4
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance transmission of RegionFeature
	(properties
		x 73
		y 155
		description {transmission}
		onMeCheck $1000
		lookStr {You're uncertain whether this is a Walwoodian cryobaric hypersleep chamber or the transmission out of a late model Buick Skylark.}
		level 4
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance rearViewMirror of RegionFeature
	(properties
		x 21
		y 127
		description {rear view mirror}
		onMeCheck $0800
		lookStr {It appears to be a standard driver-side rear view mirror, probably standard on all ships of this class.}
		level 4
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance globes of RegionFeature
	(properties
		x 133
		y 105
		description {globes-o-power}
		onMeCheck $0400
		lookStr {Two zapping, crackling globes spew forth gargantuan amounts of sizzling raw energy. Kinda neat, huh?}
		level 4
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance walkingGuard of Actor
	(properties
		x 350
		y 180
		description {guard}
		lookStr {You surmise this is a Sarien enlisted being.}
		view 410
		detailLevel 2
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance standingGuard of PicView
	(properties
		x 293
		y 76
		description {guard}
		lookStr {One of those gruesome red-suited Sariens is patrolling the hallways, looking for intruders to toast.}
		view 410
		loop 4
		priority 15
		signal $0010
	)
	
	(method (doVerb theVerb theItem)
		(if (and (== theVerb 4) (== theItem 12))
			(Print 63 1)
		else
			(super doVerb: theVerb theItem &rest)
		)
	)
)

(instance beamSound of Sound
	(properties)
)
