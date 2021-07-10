;;; Sierra Script 1.0 - (do not remove this comment)
(script# 42)
(include sci.sh)
(use Main)
(use Intrface)
(use Reverse)
(use Grooper)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	bHatchTubeRm 0
)

(instance bHatchTubeRm of Rm
	(properties
		picture 42
		north 41
		east 33
		west 35
	)
	
	(method (init)
		(super init:)
		(ego init:)
		(eastDoor init:)
		(westDoor init:)
		(self
			setRegions: 314
			setScript:
				(switch prevRoomNum
					(east cameFromEastScript)
					(west cameFromWestScript)
					(north cameFromNorthScript)
				)
				self
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room,scene]') (Print 42 0))
		)
	)
	
	(method (cue)
		(self setScript: roomControlScript)
	)
	
	(method (newRoom newRoomNumber)
		(ego setPri: -1 setLoop: -1 setCycle: Walk)
		(HandsOn)
		(super newRoom: newRoomNumber)
	)
)

(instance cameFromNorthScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 42
					posn: 124 27
					setCycle: Rev
					setLoop: 3
					setMotion: MoveTo 124 95 self
				)
			)
			(1
				(ego
					view: 232
					setCycle: Walk
					heading: 0
					observeControl: -32768
					setLoop: -1
					setLoop: Grooper
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cameFromEastScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					loop: 1
					cel: 7
					posn: 159 104
					setMotion: MoveTo 144 104 self
				)
				(eastDoor posn: 151 51 setMotion: MoveTo 151 101 self)
			)
			(1)
			(2
				(eastDoor stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cameFromWestScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					loop: 0
					cel: 7
					posn: 89 104
					setMotion: MoveTo 104 104 self
				)
				(westDoor posn: 95 51 setMotion: MoveTo 95 101 self)
			)
			(1)
			(2
				(westDoor stopUpd:)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance roomControlScript of Script
	(properties)
	
	(method (doit)
		(cond 
			((< (ego x?) 104) (client setScript: goWestScript))
			((> (ego x?) 144) (client setScript: goEastScript))
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'go,climb[<up][/ladder]') (ego setScript: climbStairsScript) (self dispose:))
		)
	)
)

(instance goWestScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 89 102 self
				)
				(westDoor setMotion: MoveTo 95 51 self)
			)
			(1)
			(2
				(ego illegalBits: -32768 ignoreActors: 0)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)

(instance goEastScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: 1
					setMotion: MoveTo 159 102 self
				)
				(eastDoor setMotion: MoveTo 151 51 self)
			)
			(1)
			(2
				(ego illegalBits: -32768 ignoreActors: 0)
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance climbStairsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: MoveTo 124 97 self)
			)
			(1
				(ego
					view: 42
					heading: 0
					ignoreControl: -32768
					setMotion: MoveTo 124 27 self
				)
			)
			(2
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance eastDoor of Act
	(properties
		y 101
		x 151
		view 41
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: 1 ignoreControl: -32768)
	)
	
	(method (stopUpd)
		(super stopUpd:)
		(doorSound number: (SoundFX 19) play:)
	)
	
	(method (setMotion)
		(super setMotion: &rest)
		(doorSound number: (SoundFX 13) play:)
	)
)

(instance westDoor of Act
	(properties
		y 101
		x 95
		view 41
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: 1 ignoreControl: -32768)
	)
	
	(method (stopUpd)
		(super stopUpd:)
		(doorSound number: (SoundFX 19) play:)
	)
	
	(method (setMotion)
		(super setMotion: &rest)
		(doorSound number: (SoundFX 13) play:)
	)
)

(instance doorSound of Sound
	(properties
		number 19
		priority 2
	)
)
