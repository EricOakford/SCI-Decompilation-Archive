;;; Sierra Script 1.0 - (do not remove this comment)
(script# 250)
(include game.sh) (include "250.shm")
(use Main)
(use TellerIcon)
(use OccasionalCycle)
(use Vendor)
(use Bazaar)
(use GloryTalker)
(use PolyPath)
(use Polygon)
(use Feature)
(use LoadMany)
(use Timer)
(use Jump)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm250 0
	beadTalker 1
	fishTalker 2
	ropeTalker 3
)

(local
	theVendor
	beadGreeted
	nearMerchant
	local3
	local4
	begCount
	haramiHere
	askedAboutAcrobatics
	showedThiefSign
	fishGreeted
	ropeGreeted
	talkCount
	[local12 2]
	local14 = [0 -1 -59 -74 -4 -16 -47 -55 -72 -13 999]
	local25 = [0 4 5 6 7 999]
	local31 = [0 16 5 -6 -78 999]
	local37 = [0 -6 -79 5 -54 999]
	local43 = [0 -66 68 69 999]
	local48 = [0 67 999]
	local51 = [0 -66 999]
	[local54 2]
	[local56 2]
	[local58 2]
	[local60 2]
	[local62 3]
	local67_2
	agreedToMeet
	local67_2_2
)
(procedure (HaramiBegs)
	(if haramiHere
		(switch begCount
			(0 (messager say: N_HARAMI V_DOIT 60))
			(1 (messager say: N_HARAMI V_DOIT 61))
			(2 (messager say: N_HARAMI V_DOIT 62))
			(3 (messager say: N_HARAMI V_DOIT 63))
			(4 (messager say: N_HARAMI V_DOIT 64))
			(else  (messager say: N_HARAMI V_DOIT 65))
		)
		(if (> (++ begCount) 6) (= begCount 0))
		((Timer new:) set: aHarami 8)
	)
)

(instance rm250 of Room
	(properties
		noun N_ROOM
		picture 250
		horizon -280
	)
	
	(method (init)
		(self setRegions: BAZAAR)
		(ego
			noun: N_EGO_TELL
			normalize:
			edgeHit: 0
			actions: egoTeller
			init:
			setScale: 0
		)
		(HandsOn)
		(switch prevRoomNum
			(240
				(= style -32759)
				(self setScript: from240)
			)
			(260
				(= style -32759)
				(= local67_2 1)
				(self setScript: from260)
			)
			(else 
				(ego x: 200 y: 110 setHeading: 135)
			)
		)
		(if (or Night (Btst fVisitedBazaar))
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							236 189
							0 189
							0 0
							36 0
							149 69
							55 83
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							319 115
							213 135
							198 112
							315 93
							315 45
							183 63
							116 22
							126 0
							319 0
						yourself:
					)
			)
		else
			(if (== (cSound prevSignal?) -1) (cSound play:))
			(cSound fade: 80 10 5 0)
			(ego code: dayCode)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: PBarredAccess
						init:
							0 189
							0 0
							34 0
							144 61
							154 71
							210 109
							124 124
							238 189
						yourself:
					)
					((Polygon new:)
						type: PBarredAccess
						init:
							319 0
							319 189
							199 119
							315 92
							315 50
							270 47
							244 58
							188 58
							119 23
							120 0
						yourself:
					)
			)
		)
		(if (and (not Night) (not (Btst fVisitedBazaar)))
			(= [local54 0] @local14)
			(egoTeller init: ego @local14 @local54)
			(= [local56 0] @local25)
			(aBeadMaker
				setPri: 1
				setCycle: OccasionalCycle self 1 65 150
				actions: beadTeller
				noun: N_BEADS
				approachVerbs: V_TALK V_DINARS V_ROYALS
				init:
			)
			(beadTeller init: aBeadMaker @local25 @local56)
			(beadVendor
				init:
				goods:
					((List new:)
						add: ((Ware new: N_BEADS)
							price: 95
							denomination: 1
							quantity: 9999
						)
					)
			)
			(= [local58 0] @local31)
			(aFishSeller
				setPri: 9
				setCycle: OccasionalCycle self 1 40 170
				actions: fishTeller
				noun: 3
				approachVerbs: V_TALK V_DINARS V_ROYALS
				init:
			)
			(fishTeller init: aFishSeller @local31 @local58)
			(fishVendor
				init:
				goods:
					((List new:)
						add: ((Ware new: N_FISH)
							price: 50
							quantity: 9999
							denomination: 1
						)
					)
			)
			(= [local60 0] @local37)
			(aRopeMaker
				setCycle: OccasionalCycle self 1 55 150
				noun: N_ROPE
				actions: ropeTeller
				approachVerbs: V_TALK V_DINARS V_ROYALS
				init:
			)
			(ropeTeller init: aRopeMaker @local37 @local60)
			(ropeVendor
				init:
				goods:
					((List new:)
						add:
							((Ware new: N_ROPE)
								price: 100
								denomination: 1
								quantity: (if (Btst fGotRope) 0 else 1)
							)
					)
			)
			(if (and (not (Btst fMetHonorlessHarami)) (== local67_2 1) (> Day 4))
				(ego code: cueCode)
			)
		)
		(if (and (not Night) (not (Btst fVisitedBazaar)))
			(beadtent init:)
			(rack_of_beads init:)
			(fishingpoles init:)
			(fishbuckets init:)
			(fishstand init:)
			(fishsticks init:)
			(brassworks init:)
			(ropes init:)
			(ropestand init:)
			(ropehook init:)
		)
		(river init:)
		(moreriver init:)
		(if (and (not Night) (not (Btst fVisitedBazaar)))
			(fishA init: addToPic:)
			(fishB init: addToPic:)
			(beadA init: addToPic:)
			(ropeA setPri: 9 init: addToPic:)
		)
		(super init: &rest)
	)
	
	(method (doit)
		(super doit: &rest)
		(cond 
			(script)
			((not (if (< 5 (ego x?)) (< (ego x?) 315)))
				(self setScript: sExit)
			)
			((not (if (< 35 (ego y?)) (< (ego y?) 183)))
				(self setScript: sExit)
			)
		)
	)
	
	(method (dispose)
		(LoadMany FALSE HARAMI_TALKER VENDOR)
		(super dispose:)
	)
	
	(method (doVerb theVerb &tmp [temp0 2])
		(switch theVerb
			(V_LOOK
				(messager
					say: N_ROOM V_LOOK NULL (if (or (Btst fVisitedBazaar) Night) 0 else 1)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance dayCode of Code
	
	(method (doit)
		(cond 
			(
				(and
					(not Night)
					(ego inRect: 193 42 237 65)
					(not beadGreeted)
					(not (Btst fVisitedBazaar))
				)
				(curRoom setScript: beadGreet)
				(= beadGreeted TRUE)
			)
			((& (ego onControl: origin) cGREEN)
				(if (and (not Night) (not fishGreeted) (not (Btst fVisitedBazaar)))
					(curRoom setScript: fishGreet)
					(= fishGreeted TRUE)
				)
			)
			((& (ego onControl: origin) cCYAN)
				(if (and (not Night) (not ropeGreeted) (not (Btst fVisitedBazaar)))
					(curRoom setScript: ropeGreet)
					(= ropeGreeted TRUE)
				)
			)
			((not (& (ego onControl: origin) cGREEN))
				(if fishGreeted
					(= fishGreeted FALSE)
				)
			)
			((and (not (& (ego onControl: origin) cCYAN)) ropeGreeted)
				(= ropeGreeted FALSE)
			)
		)
	)
)

(instance beadGreet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 233 58 self)
			)
			(1
				(Face ego aBeadMaker)
				(= cycles 15)
			)
			(2
				(messager say: N_BEADS V_DOIT C_GREET_BEADS 0 self)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance haramiLeave of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego code: dayCode)
				(= local67_2_2 0)
				(= haramiHere FALSE)
				(if (not agreedToMeet)
					(if talkCount
						(messager say: N_HARAMI V_DOIT C_HARAMI_GAB 0 self)
					else
						(messager say: N_HARAMI V_DOIT C_HARAMI_DITCHES 0 self)
					)
				else
					(self cue:)
				)
			)
			(1
				(aHarami
					view: 950
					setScript: 0
					setCycle: Walk
					ignoreActors: TRUE
					setMotion: PolyPath 310 (aHarami y?) self
				)
			)
			(2
				(HandsOn)
				(aHarami dispose:)
				(self dispose:)
			)
		)
	)
)

(instance ropeGreet of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 247 145 self)
			)
			(1
				(Face ego aRopeMaker)
				(= cycles 15)
			)
			(2
				(if showedThiefSign
					(switch (mod Day 6)
						(0
							(messager say: N_ROPE V_DOIT C_GREET_ROPE1 0 self)
						)
						(1
							(messager say: N_ROPE V_DOIT C_GREET_ROPE2 0 self)
						)
						(2
							(messager say: N_ROPE V_DOIT C_GREET_ROPE3 0 self)
						)
						(3
							(messager say: N_ROPE V_DOIT C_GREET_ROPE4 0 self)
						)
						(4
							(messager say: N_ROPE V_DOIT C_GREET_ROPE5 0 self)
						)
						(5
							(messager say: N_ROPE V_DOIT C_GREET_ROPE6 0 self)
						)
					)
				else
					(switch (mod Day 6)
						(0
							(messager say: N_ROPE V_DOIT C_GREET1 0 self)
						)
						(1
							(messager say: N_ROPE V_DOIT C_GREET2 0 self)
						)
						(2
							;EO: No, that's not the wrong case
							(messager say: N_ROPE V_DOIT C_SELF_FISH4 0 self)
						)
						(3
							(messager say: N_ROPE V_DOIT C_GREET3 0 self)
						)
						(4
							(messager say: N_ROPE V_DOIT C_GREET4 0 self)
						)
						(5
							(messager say: N_ROPE V_DOIT C_GREET5 0 self)
						)
					)
				)
			)
			(3
				(if showedThiefSign
					(self setScript: jumpFlip self)
				else
					(= cycles 1)
				)
			)
			(4
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance fishGreet of Script

	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego setMotion: PolyPath 160 78 self)
			)
			(1
				(Face ego aFishSeller)
				(= cycles 15)
			)
			(2
				(switch (mod Day 6)
					(0
						(messager say: N_FISH V_DOIT C_GREET1 0 self)
					)
					(1
						(messager say: N_FISH V_DOIT C_GREET2 0 self)
					)
					(2
						(messager say: N_FISH V_DOIT C_SELF_FISH4 0 self)
					)
					(3
						(messager say: N_FISH V_DOIT C_GREET3 0 self)
					)
					(4
						(messager say: N_FISH V_DOIT C_GREET4 0 self)
					)
					(5
						(messager say: N_FISH V_DOIT C_GREET5 0 self)
					)
				)
			)
			(3
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance from240 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego x: 30 y: 0 setMotion: PolyPath 155 50 self)
			)
			(1
				(ego setHeading: 180 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance from260 of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego
					x: 270
					y: 200
					setHeading: 345
					setMotion: PolyPath 230 172 self
				)
			)
			(1
				(ego setHeading: 0 self)
			)
			(2
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sExit of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= register 0)
				(cond 
					((<= (ego x?) 5)
						(= register 240)
						(ego setMotion: PolyPath -30 (ego y?) self)
					)
					((<= (ego y?) 35)
						(= register 240)
						(ego setMotion: PolyPath (- (ego x?) 30) -30 self)
					)
					((>= (ego x?) 315)
						(if (< (ego y?) 90)
							(messager say: N_MERCHANTS V_DOIT C_CANT_GO)
							(ego setMotion: PolyPath (- (ego x?) 30) (ego y?) self)
						else
							(= register 260)
							(ego setMotion: PolyPath 340 (ego y?) self)
						)
					)
					((>= (ego y?) 183)
						(= register 260)
						(ego setMotion: PolyPath (+ (ego x?) 35) 250 self)
					)
				)
			)
			(1
				(if (!= register 0)
					(curRoom newRoom: register)
				)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance sMeetHarami of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(ego code: dayCode setMotion: 0)
				(= [local62 0] @local43)
				(= [local62 1] @local48)
				(haramiTeller init: aHarami @local43 @local62 @local51)
				(aHarami
					view: 950
					loop: 0
					x: 300
					y: (ego y?)
					noun: 6
					init:
					ignoreActors: TRUE
					setStep: 4 2
					setCycle: Walk
					setMotion: MoveTo (+ (ego x?) 25) (ego y?) self
				)
			)
			(1
				(Face ego aHarami)
				(aHarami
					view: 954
					cel: 0
					loop: 0
					cycleSpeed: 9
					ignoreActors: 0
					setCycle: EndLoop self
				)
				(Bset fMetHonorlessHarami)
			)
			(2
				(messager say: N_HARAMI V_DOIT C_GREET1 0 self)
			)
			(3
				(= haramiHere TRUE)
				(= local67_2_2 1)
				((Timer new:) set: aHarami 5)
				(HandsOn)
				(ego code: walkCode)
				(self dispose:)
			)
		)
	)
)

(instance jumpFlip of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(aRopeMaker
					setLoop: 0
					cel: 0
					ignoreActors: 1
					setCycle: CycleTo 1 1 self
				)
			)
			(1
				(aRopeMaker
					setPri: 8
					setLoop: 1
					cel: 3
					x: 259
					y: 126
					cycleSpeed: 12
					setCycle: CycleTo 7 1 self
				)
			)
			(2
				(aRopeMaker loop: 2 cel: 0 y: 112)
				(= cycles 2)
			)
			(3
				(aRopeMaker
					setCel: -1
					cel: 1
					y: 86
					setCycle: CycleTo 5 1
					setMotion: JumpTo 259 44 self
				)
			)
			(4
				(aRopeMaker
					setCycle: CycleTo 8 1 self
					setMotion: JumpTo 259 53 self
				)
			)
			(5 0)
			(6
				(aRopeMaker setLoop: 3 setCel: 7 y: 84)
				(= ticks 60)
			)
			(7
				(aRopeMaker setLoop: 3 cel: 0)
				(= cycles 6)
			)
			(8
				(aRopeMaker cel: 1 setCycle: EndLoop self)
			)
			(9
				(aRopeMaker cel: 1 setCycle: EndLoop self)
			)
			(10
				(aRopeMaker cel: 1 setCycle: EndLoop)
				(= seconds 3)
			)
			(11
				(aRopeMaker cel: 1 setCycle: EndLoop self)
			)
			(12
				(aRopeMaker
					setLoop: 1
					cel: 0
					x: 260
					y: 84
					setCycle: CycleTo 2 1 self
				)
			)
			(13
				(aRopeMaker
					setLoop: 7
					cel: 0
					x: 264
					y: 82
					setCycle: EndLoop self
				)
			)
			(14
				(aRopeMaker
					setLoop: 2
					cycleSpeed: 17
					setCel: 0
					x: 259
					y: 60
				)
				(= ticks 10)
			)
			(15
				(aRopeMaker
					cel: 1
					x: 261
					y: 36
					setCycle: CycleTo 5 1
					setMotion: JumpTo 261 21 self
				)
			)
			(16
				(aRopeMaker
					setCycle: CycleTo 8 1 self
					setMotion: JumpTo 261 40 self
				)
			)
			(17 0)
			(18
				(= ticks 8)
			)
			(19
				(aRopeMaker setLoop: 3 cel: 0 x: 259 y: 83 setCycle: EndLoop)
				(= ticks 110)
			)
			(20
				(aRopeMaker
					setLoop: 1
					cel: 0
					x: 259
					y: 83
					setCycle: CycleTo 3 1 self
				)
			)
			(21
				(aRopeMaker setMotion: JumpTo 259 126 self)
			)
			(22
				(aRopeMaker cel: 4)
				(= cycles 5)
			)
			(23
				(aRopeMaker setPri: -1 setLoop: 0 cel: 0 cycleSpeed: 8)
				(= seconds 3)
			)
			(24
				(aRopeMaker setCycle: OccasionalCycle self 1 55 150)
				(HandsOn)
				(self dispose:)
			)
		)
	)
)

(instance cueCode of Code
	
	(method (doit)
		(if (< (ego y?) 100)
			(curRoom setScript: sMeetHarami)
		)
	)
)

(instance walkCode of Code

	(method (doit)
		(if
			(and
				(or (ego mover?) (> talkCount 2))
				(not agreedToMeet)
				local67_2_2
			)
			(curRoom setScript: haramiLeave)
		)
	)
)

(instance aHarami of Actor
	(properties
		x 186
		y 96
		view 955
		cel 3
		cycleSpeed 5
	)
	
	(method (cue)
		(if (and (> talkCount 2) (not (curRoom script?)))
			(curRoom setScript: haramiLeave)
		else
			(HaramiBegs)
		)
	)
)

(instance aBeadMaker of Actor
	(properties
		x 220
		y 59
		heading 180
		approachX 233
		approachY 58
		view 246
		signal ignrAct
		cycleSpeed 3
		detailLevel 3
	)
)

(instance aFishSeller of Actor
	(properties
		x 106
		y 85
		heading 90
		approachX 160
		approachY 78
		view 268
		cycleSpeed 9
		detailLevel 3
	)
)

(instance aRopeMaker of Actor
	(properties
		x 259
		y 126
		heading 270
		approachX 247
		approachY 145
		view 262
		cycleSpeed 9
		detailLevel 3
	)
)

(instance beadTalker of GloryTalker
	(properties
		x 200
		y 90
		view 247
		loop 1
		talkWidth 140
		back 57
		textX -178
		textY 3
		backColor 8
	)
	
	(method (init)
		(super init: beadBust beadEyes beadMouth &rest)
	)
)

(instance beadMouth of Prop
	(properties
		nsTop 52
		nsLeft 26
		view 247
	)
)

(instance beadEyes of Prop
	(properties
		nsTop 41
		nsLeft 21
		view 247
		loop 2
	)
)

(instance beadBust of View
	(properties
		nsTop 26
		nsLeft 22
		view 247
		loop 3
	)
)

(instance fishTalker of GloryTalker
	(properties
		x 5
		y 90
		view 269
		loop 1
		talkWidth 140
		back 57
		textX 143
		textY 3
		backColor 13
	)
	
	(method (init)
		(super init: fishBust fishEyes fishMouth &rest)
	)
)

(instance fishMouth of Prop
	(properties
		nsTop 59
		nsLeft 43
		view 269
	)
)

(instance fishEyes of Prop
	(properties
		nsTop 42
		nsLeft 40
		view 269
		loop 2
	)
)

(instance fishBust of View
	(properties
		nsTop 30
		nsLeft 38
		view 269
		loop 3
	)
)

(instance ropeTalker of GloryTalker
	(properties
		x 5
		y 90
		view 263
		loop 1
		talkWidth 140
		back 57
		textX 145
		textY 3
		backColor 13
	)
	
	(method (init)
		(super init: ropeBust ropeEyes ropeMouth &rest)
	)
)

(instance ropeMouth of Prop
	(properties
		nsTop 60
		nsLeft 46
		view 263
	)
)

(instance ropeEyes of Prop
	(properties
		nsTop 43
		nsLeft 49
		view 263
		loop 2
	)
)

(instance ropeBust of View
	(properties
		nsTop 29
		nsLeft 51
		view 263
		loop 3
	)
)

(instance egoTeller of Teller
	
	(method (respond)
		(return
			(if (not local4)
				(super respond:)
			else
				(= local4 0)
				(cond 
					((not query)
						(return TRUE)
					)
					((== query -999)
						(return TRUE)
					)
					((== query 999)
						(self doParent:)
						(return FALSE)
					)
					((and (< query 0) (not (self doChild: query)))
						(return TRUE)
					)
				)
				(if (< query 0)
					(= query (- query))
				)
				(messager say: (client noun?) V_TELL query 0)
				(return TRUE)
			)
		)
	)
	
	(method (showDialog)
		(= nearMerchant (proc51_1))
		(= local3 (ego distanceTo: nearMerchant))
		(switch nearMerchant
			(aFishSeller
				(if (> local3 65)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_MERCHANT)
					(return -999)
				)
			)
			(aBeadMaker
				(if (> local3 35)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_MERCHANT)
					(return -999)
				)
			)
			(else 
				(if (> local3 45)
					(messager say: N_MERCHANTS V_DOIT C_WHICH_MERCHANT)
					(return -999)
				)
			)
		)
		(if
			(!=
				(ego heading?)
				(GetAngle (ego x?) (ego y?) (nearMerchant x?) (nearMerchant y?))
			)
			(Face ego nearMerchant)
		)
		((Timer new:) setCycle: self (+ (ego cycleSpeed?) 10))
		(= iconValue 0)
		(return -999)
	)
	
	(method (doChild &tmp temp0 temp1)
		(return
			(switch query
				(-13
					(ego solvePuzzle: fShowSignToHarami 8)
					(return query)
				)
				(-1
					(cond 
						((cast contains: aHarami)
							(= query 70)
						)
						((== nearMerchant aBeadMaker)
							(= talkCount 3)
							(= query C_GREET_BEAD)
						)
						((== nearMerchant aFishSeller)
							(= talkCount 3)
							(= query C_GREET_FISH)
						)
						((== nearMerchant aRopeMaker)
							(= talkCount 3)
							(= query C_GREET_ROPE)
						)
					)
				)
				(-59
					(cond 
						((cast contains: aHarami)
							(= agreedToMeet TRUE)
							(= haramiHere 0)
							(messager say: N_EGO_TELL V_TELL C_GOODBYE_HARAMI)
							(curRoom setScript: haramiLeave)
							(return 0)
						)
						((== nearMerchant aBeadMaker)
							(= talkCount 3)
							(= query C_GOODBYE_BEAD)
						)
						((== nearMerchant aFishSeller)
							(= talkCount 3)
							(= query C_GOODBYE_FISH)
						)
						((== nearMerchant aRopeMaker)
							(= talkCount 3)
							(= query C_GOODBYE_ROPE)
						)
					)
				)
				(-74
					(cond 
						((cast contains: aHarami)
							(ego solvePuzzle: fShowSignToHarami 8)
							(= query C_THIEF_SIGN_HARAMI)
						)
						((== nearMerchant aBeadMaker)
							(= talkCount 3)
							(= query C_THIEF_SIGN_BEAD)
						)
						((== nearMerchant aFishSeller)
							(= talkCount 3)
							(= query C_THIEF_SIGN_FISH)
						)
						((== nearMerchant aRopeMaker)
							(= talkCount 3)
							(messager say: N_EGO_TELL V_TELL C_THIEF_SIGN_ROPE)
							(curRoom setScript: jumpFlip)
							(= showedThiefSign TRUE)
							(return FALSE)
						)
					)
				)
				(-4
					(= talkCount 3)
					(if (== ((inventory at: iRoyals) message?) V_DINARS)
						(messager say: N_BEADS V_DOIT 77)
						(return FALSE)
					else
						(beadVendor purchase:)
					)
					(return 0)
				)
				(-16
					(= talkCount 3)
					(if (== ((inventory at: iRoyals) message?) V_DINARS)
						(messager say: N_FISH V_DOIT C_WRONG_MONEY)
						(return FALSE)
					else
						(fishVendor purchase:)
						(return FALSE)
					)
				)
				(-47
					(= talkCount 3)
					(if (== ((inventory at: 0) message?) 59)
						(messager say: N_ROPE V_DOIT C_WRONG_MONEY)
						(return 0)
					else
						(if (Btst fGotRope)
							(messager say: N_MERCHANTS V_DOIT C_NO_MORE_ROPE)
						else
							(ropeVendor purchase:)
						)
						(return FALSE)
					)
				)
				(-55
					(= talkCount 3)
					(if
						(or
							(<
								(= temp0
									(+ (* ((inventory at: iRoyals) amount?) 100) commons)
								)
								1000
							)
							(== ((inventory at: iRoyals) message?) V_DINARS)
						)
						(messager say: N_MERCHANTS V_DOIT C_NO_MONEY)
						(return 0)
					else
						(= temp0 (/ commons 100))
						(= commons (mod commons 100))
						((inventory at: iRoyals)
							amount: (+ (- ((inventory at: iRoyals) amount?) 50) temp0)
						)
					)
					(Bset fTrainedAcrobatics)
					(if (> (= [egoStats AGIL] (+ [egoStats AGIL] 30)) 300)
						(= [egoStats AGIL] 300)
					)
					(return TRUE)
				)
				(-72
					(= agreedToMeet TRUE)
					(= haramiHere FALSE)
					(messager say: N_EGO_TELL V_TELL C_AGREE_TO_MEET)
					(if (not (Btst fAgreedToMeetHarami))
						(ego addHonor: 30)
					)
					(ego solvePuzzle: fAgreedToMeetHarami 4)
					(curRoom setScript: haramiLeave)
					(return 0)
				)
			)
		)
	)
	
	(method (cue)
		(= query
			(super
				showDialog:
					-1
					-59
					-74
					(== heroType THIEF)
					-55
					(if (and askedAboutAcrobatics (== nearMerchant aRopeMaker))
						(not (Btst fTrainedAcrobatics))
					else
						0
					)
					-4
					(== nearMerchant aBeadMaker)
					-16
					(== nearMerchant aFishSeller)
					-47
					(== nearMerchant aRopeMaker)
					-72
					(cast contains: aHarami)
					-13
					(if (== heroType THIEF) (cast contains: aHarami) else 0)
			)
		)
		(= local4 1)
		(if iconValue
			(= query iconValue)
		)
		(egoTeller respond:)
	)
)

(instance beadTeller of Teller
	(properties)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(= talkCount 3)
				(messager say: N_BEADS V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(beadVendor purchase:)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance fishTeller of Teller
	
	(method (doChild)
		(switch query
			(-6
				(switch (mod Day 6)
					(0
						(= query C_RUMOR1)
					)
					(1
						(= query C_RUMOR2)
					)
					(2
						(= query C_RUMOR3)
					)
					(3
						(= query C_RUMOR4)
					)
					(4
						(= query C_RUMOR5)
					)
					(5
						(= query C_RUMOR6)
					)
				)
			)
			(-78
				(switch (mod Day 6)
					(0
						(= query C_SELF_FISH1)
					)
					(1
						(= query C_SELF_FISH2)
					)
					(2
						(= query C_SELF_FISH3)
					)
					(3
						(= query C_SELF_FISH5)
					)
					(4
						(= query C_SELF_FISH6)
					)
					(5
						(= query C_SELF_FISH7)
					)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(= talkCount 3)
				(messager say: N_FISH V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(fishVendor purchase:)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance ropeTeller of Teller

	(method (showDialog)
		(super showDialog: -54 showedThiefSign)
	)
	
	(method (doChild)
		(return
			(switch query
				(-6
					(switch (mod Day 6)
						(0
							(= query C_RUMOR1)
						)
						(1
							(= query C_RUMOR2)
						)
						(2
							(= query C_RUMOR3)
						)
						(3
							(= query C_RUMOR4)
						)
						(4
							(= query C_RUMOR5)
						)
						(5
							(= query C_RUMOR6)
						)
					)
				)
				(-79
					(switch (mod Day 6)
						(0
							(= query C_SELF_ROPE1)
						)
						(1
							(= query C_SELF_ROPE2)
						)
						(2
							(= query C_SELF_ROPE3)
						)
						(3
							(= query C_SELF_ROPE4)
						)
						(4
							(= query C_SELF_ROPE5)
						)
						(5
							(= query C_SELF_ROPE6)
						)
					)
				)
				(-54
					(= askedAboutAcrobatics TRUE)
					(return TRUE)
				)
			)
		)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(V_DINARS
				(= talkCount 3)
				(messager say: N_ROPE V_DOIT C_WRONG_MONEY)
			)
			(V_ROYALS
				(if (Btst fGotRope)
					(messager say: N_MERCHANTS V_DOIT C_NO_MORE_ROPE)
				else
					(ropeVendor purchase:)
				)
			)
			(else
				(super doVerb: theVerb)
			)
		)
	)
)

(instance haramiTeller of Teller
	
	(method (showDialog)
		(++ talkCount)
		(super showDialog: &rest)
	)
)

(instance riverWater of View
	(properties
		x 16
		y 170
		view 250
	)
	
	(method (init)
		(if (or Night (Btst fVisitedBazaar))
			(super init: &rest)
		)
	)
)

(instance fishA of View
	(properties
		x 62
		y 172
		view 250
		priority 7
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance fishB of View
	(properties
		x 159
		y 120
		view 250
		cel 1
		priority 7
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance beadA of View
	(properties
		x 181
		y 7
		view 250
		loop 1
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance ropeA of View
	(properties
		x 238
		y 88
		view 250
		loop 1
		cel 1
		priority 10
		signal (| ignrAct skipCheck fixPriOn)
	)
	
	(method (onMe)
		(return FALSE)
	)
)

(instance beadVendor of Vendor
	(properties
		noun N_BEADS
	)
	
	(method (bargain &tmp commStat)	;can't bargain with her
		(= commStat [egoStats COMM])
		(= [egoStats COMM] 0)
		(super bargain: &rest)
		(= [egoStats COMM] commStat)
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(ego
			get: iBeads howMany
			solvePuzzle: fBuyBeads 2
		)
		(Buy what howMany)
		(messager say: N_BEADS V_DOIT C_DONE_DEAL 0 self)
	)
	
	(method (doBargain)
		(messager say: N_BEADS V_DOIT C_CANT_BARGAIN 0 self)
	)
)

(instance fishVendor of Vendor
	(properties
		noun N_FISH
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(ego get: iFish howMany)
		(Buy what howMany)
		(messager say: N_FISH V_DOIT C_DONE_DEAL 0 self)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_FISH V_DOIT C_BARGAIN_SUCCESS 0 self)
			)
			(bargainTRY1
				(messager say: N_FISH V_DOIT C_BARGAIN_TRY1 0 self)
			)
			(bargainTRY2
				(messager say: N_FISH V_DOIT C_BARGAIN_TRY2 0 self)
			)
			(bargainTRY3
				(messager say: N_FISH V_DOIT C_BARGAIN_TRY3 0 self)
			)
			(bargainFAIL
				(messager say: N_FISH V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_FISH V_DOIT C_BARGAIN_NODEAL 0 self)
			)
			(else
				(self cue:)
			)
		)
	)
)

(instance ropeVendor of Vendor
	(properties
		noun N_ROPE
	)
	
	(method (transact what howMany)
		(= theVendor self)
		(ego
			get: iRope howMany
			solvePuzzle: 228 8 puzzleTHIEF
		)
		(Buy what howMany)
		(Bset fGotRope)
		(messager say: N_ROPE V_DOIT C_DONE_DEAL 0 self)
	)
	
	(method (doBargain result)
		(switch result
			(bargainSUCCESS
				(messager say: N_ROPE V_DOIT C_BARGAIN_NODEAL self)
			)
			(bargainTRY1
				(messager say: N_ROPE V_DOIT C_BARGAIN_TRY1 0 self)
			)
			(bargainTRY2
				(messager say: N_ROPE V_DOIT C_BARGAIN_TRY2 0 self)
			)
			(bargainTRY3
				(messager say: N_ROPE V_DOIT C_BARGAIN_TRY3 0 self)
			)
			(bargainFAIL
				(messager say: N_ROPE V_DOIT C_BARGAIN_FAIL 0 self)
			)
			(bargainNODEAL
				(messager say: N_ROPE V_DOIT C_BARGAIN_NODEAL 0 self)
			)
			(else  (self cue:))
		)
	)
)

(instance beadtent of Feature
	(properties
		x 195
		y 22
		noun N_BEAD_TENT
		nsLeft 154
		nsBottom 44
		nsRight 236
		sightAngle 180
	)
)

(instance rack_of_beads of Feature
	(properties
		x 213
		y 20
		noun N_BEAD_RACK
		nsTop 11
		nsLeft 201
		nsBottom 29
		nsRight 226
		sightAngle 180
	)
)

(instance fishingpoles of Feature
	(properties
		x 60
		y 92
		noun N_FISHING_POLES
		nsTop 77
		nsLeft 15
		nsBottom 107
		nsRight 105
		sightAngle 180
	)
)

(instance fishbuckets of Feature
	(properties
		x 143
		y 109
		noun N_FISH_BUCKETS
		nsTop 104
		nsLeft 131
		nsBottom 114
		nsRight 156
		sightAngle 180
	)
)

(instance fishstand of Feature
	(properties
		x 142
		y 65
		noun N_FISH_STAND
		nsTop 64
		nsLeft 115
		nsBottom 90
		nsRight 169
		sightAngle 180
	)
)

(instance fishsticks of Feature
	(properties
		x 175
		y 65
		noun N_FISH_STICKS
		nsTop 64
		nsLeft 169
		nsBottom 113
		nsRight 182
		sightAngle 180
	)
)

(instance brassworks of Feature
	(properties
		x 259
		y 22
		noun N_BRASSWORKS
		nsTop 1
		nsLeft 235
		nsBottom 44
		nsRight 283
		sightAngle 180
	)
)

(instance ropes of Feature
	(properties
		x 272
		y 137
		noun N_ROPES
		nsTop 123
		nsLeft 235
		nsBottom 152
		nsRight 309
		sightAngle 180
	)
)

(instance ropestand of Feature
	(properties
		x 308
		y 116
		noun N_ROPE_STAND
		nsTop 89
		nsLeft 299
		nsBottom 143
		nsRight 318
		sightAngle 180
	)
)

(instance ropehook of Feature
	(properties
		x 217
		y 118
		noun N_ROPE_HOOK
		nsTop 114
		nsLeft 209
		nsBottom 122
		nsRight 225
		sightAngle 180
	)
)

(instance river of Feature
	(properties
		x 31
		y 149
		noun N_RIVER
		nsTop 109
		nsBottom 189
		nsRight 62
		sightAngle 180
	)
)

(instance moreriver of Feature
	(properties
		x 86
		y 170
		noun N_MORE_RIVER
		nsTop 152
		nsLeft 61
		nsBottom 189
		nsRight 111
		sightAngle 180
	)
)
