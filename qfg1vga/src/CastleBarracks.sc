;;; Sierra Script 1.0 - (do not remove this comment)
(script# 38)
(include game.sh) (include "38.shm")
(use Main)
(use Procs)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm38 0
)

(local
	saveSortedFeatures
)
(instance rm38 of Room
	(properties
		picture 38
		horizon 85
		north 41
	)
	
	(method (init &tmp egoY soundNum)
		(if (== prevRoomNum 39)
			(self style: SCROLLRIGHT)
		else
			(self style: PLAIN)
		)
		(= soundNum (if Night 32 else 25))
		(if
			(or
				(== (theMusic prevSignal?) -1)
				(!= (theMusic number?) soundNum)
			)
			(theMusic stop: number: soundNum loop: -1 priority: 0 play:)
		)
		(super init: &rest)
		(= saveSortedFeatures useSortedFeatures)
		(= useSortedFeatures FALSE)
		(StatusLine enable:)
		(curRoom
			addObstacle:
				((Polygon new:)
					type: PBarredAccess
					init:
						319 189
						0 189
						0 0
						319 0
						319 67
						302 74
						272 113
						186 114
						197 122
						190 132
						159 131
						106 161
						99 172
						78 184
						94 184
						107 181
						121 181
						127 184
						135 187
						319 187
					yourself:
				)
		)
		(self
			setFeatures:
				doors
				barrack
				towers
				walls
				walkWay
				theDoor
				castle
				waggon
				chimney
				garden
				mounts
				sky
				water
				crest
				pots
		)
		;UPGRADE
;;;		(doors init:)
;;;		(barrack init:)
;;;		(towers init:)
;;;		(walls init:)
;;;		(walkWay init:)
;;;		(theDoor init:)
;;;		(castle init:)
;;;		(waggon init:)
;;;		(chimney init:)
;;;		(garden init:)
;;;		(mounts init:)
;;;		(sky init:)
;;;		(water init:)
;;;		(crest init:)
;;;		(pots init:)
		
		(doors approachVerbs: V_DO)
		(if (not Night)
			(guard init:)
		)
		(if (== prevRoomNum 39)
			(= egoY (ego y?))
			(NormalEgo)
			(ego
				cel: (ego cel?)
				loop: (ego loop?)
				posn: 304 egoY
				init:
			)
		else
			(NormalEgo)
			(ego posn: 266 134 init:)
		)
	)
	
	(method (doit &tmp temp0)
		(if (and (== (ego edgeHit?) EAST) (not (ego script?)))
			(ego setScript: headEast)
		)
		(super doit:)
	)
	
	(method (dispose)
		(= useSortedFeatures saveSortedFeatures)
		(Bset fBeenIn38)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_FLAME (EgoDead 90 91 0 0 503))
			;Uses wrong death icon, but trying to use the correct one (Hero in jail) causes the game to crash with
			;"Invalid Rectangle" error, since the icon and message are too big to be on the screen at once.
			(V_LOOK (messager say: N_ROOM V_LOOK NULL))
			(V_DO (messager say: N_ROOM V_DO NULL))
			(V_SLEEP (ego setScript: sleepyWhen))
			(V_DAZZLE (messager say: N_ROOM V_DAZZLE))	;EO: now uses correct verb
			(else  (super doVerb: theVerb))
		)
	)
)

(instance doors of Feature
	(properties
		x 90
		y 124
		noun N_BARRACKS_DOOR
		nsTop 95
		nsLeft 51
		nsBottom 153
		nsRight 129
		sightAngle 40
		approachX 91
		approachY 128
		approachDist 41
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance barrack of Feature
	(properties
		noun N_BARRACKS
		onMeCheck cYELLOW
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance towers of Feature
	(properties
		noun N_TOWERS
		onMeCheck cBLUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance walls of Feature
	(properties
		noun N_WALLS
		onMeCheck cGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance walkWay of Feature
	(properties
		noun N_WALKWAY
		onMeCheck cCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance theDoor of Feature
	(properties
		noun N_TOWER_DOOR
		onMeCheck cRED
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance castle of Feature
	(properties
		noun N_CASTLE
		onMeCheck cMAGENTA
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_CASTLE V_LOOK))
			(V_DO (messager say: N_CASTLE V_DO))
			(V_FLAME (EgoDead 90 91 0 0 503))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance waggon of Feature ;EO: Was that misspelling a joke or a genuine typo?
	(properties
		noun N_WAGON
		onMeCheck cBROWN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance chimney of Feature
	(properties
		noun N_CHIMNEY
		onMeCheck cLGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance garden of Feature
	(properties
		noun N_GARDEN
		onMeCheck cGREY
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance mounts of Feature
	(properties
		noun N_MOUNTAINS
		onMeCheck cLBLUE
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance sky of Feature
	(properties
		noun N_SKY
		onMeCheck cLGREEN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance water of Feature
	(properties
		noun N_WATER
		onMeCheck cLCYAN
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance crest of Feature
	(properties
		noun N_CREST
		onMeCheck cLRED
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance pots of Feature
	(properties
		noun N_POTS
		onMeCheck cLMAGENTA
	)
	
	(method (doVerb theVerb)
		(if (== theVerb V_FLAME)
			(EgoDead 90 91 0 0 503)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance guard of Prop
	(properties
		x 177
		y 125
		noun N_GUARD
		approachX 208
		approachY 146
		view 38
		priority 9
		signal (| fixPriOn stopUpdOn)
	)
	
	(method (doit)
		(if
			(and
				(< 110 (ego y?))
				(< (ego y?) 140)
				(< (ego x?) 250)
				(!= script guardThreatens)
			)
			(self setScript: guardThreatens)
		)
		(super doit:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_LOOK (messager say: N_GUARD V_LOOK))
			(V_TALK (messager say: N_GUARD V_TALK))
			(V_ROCK (EgoDead 90 91 0 0 503))
			(V_DAGGER (EgoDead 90 91 0 0 503))
			(V_SWORD (EgoDead 90 91 0 0 503))
			(V_FLAME (EgoDead 90 91 0 0 503))
			(V_DO (EgoDead 90 91 0 0 503))
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance guardThreatens of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(guard startUpd:)
				(= ticks 12)
			)
			(1
				(ego setMotion: 0)
				(guard cel: 0 cycleSpeed: 18 setCycle: Forward)
				(= ticks 100)
			)
			(2
				(messager say: N_ROOM 0 C_GOAWAY 1 self)
			)
			(3
				(guard cel: 0 ignoreActors: setCycle: EndLoop self)
			)
			(4
				(guard cel: 0 stopUpd: setCycle: 0)
				(= ticks 12)
			)
			(5
				(HandsOn)
				(self changeState: 4)
			)
		)
	)
)

(instance headEast of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (> (ego y?) 125)
					(ego setMotion: PolyPath 335 (ego y?) self)
				else
					(ego setMotion: PolyPath 335 (- (ego y?) 10) self)
				)
			)
			(1
				(if (> (ego y?) 125)
					(curRoom newRoom: 39)
				else
					(curRoom newRoom: 41)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sleepyWhen of Script
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(messager say: N_ROOM V_SLEEP NULL 1 self)
			)
			(1
				(if (and (< 750 Clock) (< Clock 2550))
					(FixTime 21)
				)
				(= ticks 60)
			)
			(2 (curRoom newRoom: 37))
		)
	)
)
