;;; Sierra Script 1.0 - (do not remove this comment)
(script# 910)
(include game.sh)
(use Main)
(use LLRoom)
(use Talker)
(use Feature)
(use Motion)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm910 0
)

(local
	[local0 2]
	local2
	roomTimer
	[local4 2]
	local6
)
(instance rm910 of LLRoom
	(properties
		picture 910
		east 905
	)
	
	(method (init)
		((Inventory at: iDoily) state: 0)
		(Load VIEW 910)
		(Load VIEW 911)
		(Load VIEW 912)
		(Load VIEW 913)
		(super init:)
		(ego
			init:
			normalize:
			view: 911
			setLoop: 0
			setCycle: 0
			posn: 152 100 -5
			setCel: 0
			setPri: 7
		)
		(faucet init: cycleSpeed: 28 setCycle: Forward)
		(drill init:)
		(light init:)
		(degree init:)
		(theWindow init:)
		(chair init:)
		(dentalTools init:)
		(sink init:)
		(mouthPic init:)
		(InFirstPerson 1)
		(HandsOn)
		(Bset fDentistClosed)
		(chiChi init:)
		(switch prevRoomNum
			(915
				(chiChi
					view: 913
					posn: 185 105
					setLoop: 5
					setCel: 0
					cycleSpeed: 8
				)
				(if (Btst fGaveGreenCard)
					(ego setCel: 1)
					(curRoom setScript: sScored)
				else
					(ego setCel: 4)
					(= local2 25)
					(curRoom setScript: sWorkOnTeeth)
				)
			)
			(else 
				(HandsOff)
				(theMusic number: 910 loop: -1 play:)
				(curRoom setScript: sBringInChiChi)
			)
		)
	)
	
	(method (doit)
		(super doit:)
		(return
			(if (!= roomTimer (GetTime SYSTIME1))
				(= roomTimer (GetTime SYSTIME1))
				(++ local2)
			else
				0
			)
		)
	)
	
	(method (doVerb theVerb theItem)
		(switch theVerb
			(verbLook
				(TimePrint 910 0)
			)
			(verbUse
				(switch theItem
					(iGreenCard
						(TimePrint 910 1)
					)
					(else
						(TimePrint 910 2)
					)
				)
			)
			(verbTalk
				(TimePrint 910 1)
			)
			(verbZipper
				(TimePrint 910 3)
				(TimePrint 910 4 #at -1 185)
			)
			(verbWalk
				(HandsOff)
				(Say ego 910 5)
				(theMusic fade:)
				(curRoom newRoom: 905)
			)
			(verbDo
				(Say ego 910 6)
			)
			(else
				(TimePrint 910 7)
			)
		)
	)
)

(instance faucet of Prop
	(properties
		x 225
		y 135
		description {the faucet}
		sightAngle 90
		lookStr {The faucet is drip-, drip-, dripping, right in time to the theMusic that's playing!}
		view 910
		loop 1
		priority 8
		signal (| ignrAct fixedLoop fixPriOn)
	)
)

(instance mouthPic of Feature
	(properties
		x 198
		y 48
		nsTop 33
		nsLeft 176
		nsBottom 63
		nsRight 221
		description {the happy tooth poster}
		sightAngle 40
		lookStr {If you maintained a regular schedule of oral hygiene with Chi Chi Lambada, your teeth would be happy too!}
	)
)

(instance drill of Feature
	(properties
		x 149
		y 33
		nsTop 19
		nsLeft 123
		nsBottom 47
		nsRight 175
		description {the drill}
		sightAngle 40
		lookStr {Whenever you see a high-speed dental drill, you make a silent vow to floss more often!}
	)
)

(instance sink of Feature
	(properties
		x 217
		y 132
		nsTop 118
		nsLeft 193
		nsBottom 146
		nsRight 242
		description {the sink}
		sightAngle 40
		lookStr {You love it when a woman orders you to "Spit!"}
	)
)

(instance dentalTools of Feature
	(properties
		x 124
		y 110
		nsTop 80
		nsLeft 80
		nsBottom 141
		nsRight 169
		description {the tray of dental tools}
		sightAngle 40
		lookStr {You've always believed dental professionals have latent masochistic tendencies.}
	)
)

(instance chair of Feature
	(properties
		x 160
		y 93
		nsTop 60
		nsLeft 119
		nsBottom 127
		nsRight 202
		description {the dentist's chair}
		sightAngle 40
		lookStr {You just love electric furniture!}
	)
)

(instance theWindow of Feature
	(properties
		x 93
		y 48
		nsTop 21
		nsLeft 81
		nsBottom 76
		nsRight 106
		description {the window}
		sightAngle 40
		lookStr {Through the window you see children laughing, skipping, running, playing; all oblivious to their oral hygiene!}
	)
)

(instance degree of Feature
	(properties
		x 123
		y 51
		nsTop 41
		nsLeft 112
		nsBottom 61
		nsRight 134
		description {Chi Chi's degree}
		sightAngle 40
		lookStr {Interesting. You never knew the Columbia School of Broadcasting even HAD a dental college!}
	)
)

(instance light of Feature
	(properties
		x 155
		y 50
		nsTop 43
		nsLeft 147
		nsBottom 58
		nsRight 163
		description {the spotlight}
		sightAngle 40
		lookStr {You love to peer at the reflection of your mouth on the surface of the dental spotlight.}
	)
)

(instance Chi_Chi_Lambada of Talker
	(properties
		nsTop 33
		nsLeft 22
		view 1910
		loop 3
		viewInPrint 1
		name "Chi Chi Lambada"
	)
	
	(method (init)
		(= bust talkerBust)
		(= eyes talkerEyes)
		(= mouth talkerMouth)
		(super init:)
	)
)

(instance talkerBust of View
	(properties
		view 1910
		loop 1
	)
)

(instance talkerEyes of Prop
	(properties
		nsTop 25
		nsLeft 34
		view 1910
		loop 2
		cycleSpeed 18
	)
)

(instance talkerMouth of Prop
	(properties
		nsTop 33
		nsLeft 27
		view 1910
	)
)

(instance sCloseUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (HandsOff) (= cycles 2))
			(1
				(chiChi setLoop: 5 setCel: 255 setCycle: BegLoop self)
			)
			(2 (curRoom newRoom: 915))
		)
	)
)

(instance sBringInChiChi of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (HandsOff) (= ticks 60))
			(1 (Say ego 910 8 #dispose self))
			(2 (= ticks 60))
			(3
				(ego setCel: 1 cycleSpeed: 6)
				(= ticks 30)
			)
			(4 (TimePrint 910 9 #dispose self))
			(5 (= seconds 3))
			(6
				(chiChi
					view: 912
					setLoop: 1
					setCel: -1
					setCycle: Forward
					cycleSpeed: 6
					setStep: 3 2
					moveSpeed: 6
					ignoreActors: 1
					illegalBits: 0
					setMotion: MoveTo 185 105 self
				)
			)
			(7
				(chiChi
					view: 913
					setLoop: 1
					setCel: 0
					setCycle: 0
					setMotion: 0
				)
				(= ticks 30)
			)
			(8
				(Say Chi_Chi_Lambada 910 10 #dispose #caller self)
			)
			(9
				(ego setCel: 0 stopUpd:)
				(= ticks 30)
			)
			(10
				(Say ego 910 11 67 -1 185)
				(= ticks 60)
			)
			(11
				(Say Chi_Chi_Lambada 910 12 #dispose #caller self)
				(ego setCel: 2 stopUpd:)
			)
			(12 (= ticks 60))
			(13
				(chiChi
					view: 913
					setLoop: 5
					setCel: 0
					cycleSpeed: 8
					setCycle: EndLoop self
				)
				(ego setCel: 4 stopUpd:)
			)
			(14 (= seconds 4))
			(15
				(HandsOn)
				(User canControl: 0)
				(theMusic2 stop:)
				(= local2 0)
				(= seconds 4)
			)
			(16
				(Say Chi_Chi_Lambada 910 13 #dispose #caller self)
			)
			(17
				(curRoom setScript: sWorkOnTeeth)
			)
		)
	)
)

(instance sWorkOnTeeth of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(chiChi
					cycleSpeed: (Random 3 6)
					setLoop: 6
					setCel: 0
					setCycle: EndLoop self
				)
			)
			(1
				(if (and (> local2 30) (not local6))
					(Say Chi_Chi_Lambada 910 14 #dispose #caller self)
					(= local6 1)
				else
					(= ticks 60)
				)
			)
			(2 (= ticks 30))
			(3 (chiChi setCycle: BegLoop self))
			(4 (= ticks 60))
			(5
				(if (> local2 35)
					(HandsOff)
					(Say Chi_Chi_Lambada 910 15 #dispose #caller self)
					(chiChi
						setLoop: 5
						setCel: 255
						cycleSpeed: 8
						setCycle: BegLoop self
					)
					(theMusic fade:)
				else
					(self init:)
				)
			)
			(6 0)
			(7 (curRoom newRoom: 905))
		)
	)
)

(instance sDance of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (= register 30) (self cue:))
			(1
				(= start state)
				(chiChi
					view: 913
					setLoop: (Random 2 4)
					setCycle: Forward
					cycleSpeed: (Random 5 12)
				)
				(= ticks (Random 10 30))
			)
			(2
				(if (not (-- register))
					(self dispose:)
				else
					(self init:)
				)
			)
		)
	)
)

(instance chiChi of Actor
	(properties
		x 259
		y 105
		description {Chi Chi Lambada}
		sightAngle 90
		view 912
		priority 8
		signal (| ignrAct fixedLoop fixPriOn)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(verbDo
				(TimePrint 910 16)
			)
			(verbLook
				(HandsOff)
				(curRoom setScript: 0)
				(chiChi
					setLoop: 5
					setCel: 255
					cycleSpeed: 8
					setCycle: BegLoop self
				)
			)
			(verbTalk
				(TimePrint 910 1)
			)
			(verbZipper
				(Say Chi_Chi_Lambada 910 17 #dispose)
			)
			(verbWalk
				(curRoom doVerb: verbWalk)
			)
			(else
				(TimePrint 910 1)
			)
		)
	)
	
	(method (cue)
		(super cue:)
		(curRoom newRoom: 915)
	)
)

(instance sScored of Script
	
	(method (changeState newState &tmp [temp0 2])
		(switch (= state newState)
			(0 (HandsOff) (= cycles 2))
			(1
				(ego setCel: 1)
				(self setScript: sDance self)
			)
			(2
				(chiChi setCycle: 0 stopUpd:)
				(SolvePuzzle 40 fAfterChiChi)
				(Say Chi_Chi_Lambada 910 18 #dispose #caller self)
			)
			(3 (= ticks 90))
			(4
				(TimePrint 910 19
					#at -1 185
					#width 280
				)
				(= ticks 123)
			)
			(5
				(curRoom drawPic: 1 IRISIN)
				(= seconds 3)
			)
			(6
				(theMusic fade:)
				(DoDisplay 3 myDisplayColor 910 20)
				(= seconds 3)
			)
			(7 (curRoom newRoom: 920))
		)
	)
)
