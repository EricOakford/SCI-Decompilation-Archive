;;; Sierra Script 1.0 - (do not remove this comment)
(script# 41)
(include game.sh)
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
	hatchTubeRm 0
)

(instance hatchTubeRm of Room
	(properties
		picture 41
		north 23
		east 32
		south 42
		west 34
	)
	
	(method (init)
		(super init:)
		(ego view: 232 init:)
		(eastDoor init:)
		(westDoor init:)
		(self
			setRegions: 314
			setScript:
				(switch prevRoomNum
					(east cameFromEastScript)
					(west cameFromWestScript)
					(south cameFromSouthScript)
					(north cameFromNorthScript)
				)
				self
		)
	)
	
	(method (cue)
		(self setScript: roomControlScript)
	)
	
	(method (newRoom nRoom)
		(ego setPri: -1 setLoop: -1 setCycle: Walk)
		(HandsOn)
		(super newRoom: nRoom)
	)
)

(instance cameFromNorthScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 42
					posn: 124 27
					setCycle: Reverse
					setLoop: 3
					setMotion: MoveTo 124 95 self
				)
			)
			(1
				(ego view: 232 setCycle: Walk heading: 0 setLoop: GradualLooper)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cameFromSouthScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					view: 42
					posn: 124 173
					setPri: 5
					ignoreControl: cWHITE
					setMotion: MoveTo 124 97 self
				)
			)
			(1
				(ego view: 232 setPri: -1 setMotion: MoveTo 124 102 self)
			)
			(2
				(HandsOn)
				(ego observeControl: cWHITE)
				(self dispose:)
			)
		)
	)
)

(instance cameFromEastScript of Script
	
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

	(method (doit)
		(cond 
			((< (ego x?) 104)
				(client setScript: goWestScript)
			)
			((> (ego x?) 144)
				(client setScript: goEastScript)
			)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'go,climb[<up][/ladder]')
				(ego setScript: climbStairsScript curRoom)
			)
			((Said 'go,decend,climb[<down][/ladder]')
				(ego setScript: decendStairsScript)
				(self dispose:)
			)
		)
	)
)

(instance goWestScript of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 89 102 self
				)
				(westDoor setMotion: MoveTo 95 51 self)
			)
			(1)
			(2
				(ego ignoreActors: 0 illegalBits: cWHITE)
				(curRoom newRoom: (curRoom west?))
			)
		)
	)
)

(instance goEastScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors: TRUE
					setMotion: MoveTo 159 102 self
				)
				(eastDoor setMotion: MoveTo 151 51 self)
			)
			(1)
			(2
				(ego illegalBits: cWHITE ignoreActors: FALSE)
				(curRoom newRoom: (curRoom east?))
			)
		)
	)
)

(instance climbStairsScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(if 1
					(Print 41 0)
					(self dispose:)
					(return)
				else
					(HandsOff)
					(ego ignoreControl: cWHITE setMotion: MoveTo 124 97 self)
				)
			)
			(1
				(ego view: 42 heading: 0 setMotion: MoveTo 124 39 self)
			)
			(2
				(curRoom newRoom: (curRoom north?))
			)
		)
	)
)

(instance decendStairsScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego ignoreControl: cWHITE setMotion: MoveTo 124 102 self)
			)
			(1
				(ego setMotion: MoveTo 124 97 self)
			)
			(2
				(ego view: 42 heading: 0 setPri: 5)
				(if (ego looper?) ((ego looper?) doit: ego 0))
				(= cycles 5)
			)
			(3
				(if (ego looper?)
					((ego looper?) dispose:)
				)
				(ego
					setLoop: 3
					setCycle: Reverse
					setMotion: MoveTo 124 173 self
				)
			)
			(4
				(curRoom newRoom: (curRoom south?))
				(ego setPri: -1)
			)
		)
	)
)

(instance eastDoor of Actor
	(properties
		y 101
		x 151
		view 41
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: TRUE ignoreControl: cWHITE)
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

(instance westDoor of Actor
	(properties
		y 101
		x 95
		view 41
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 14 ignoreActors: TRUE ignoreControl: cWHITE)
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
