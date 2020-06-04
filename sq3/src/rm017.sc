;;; Sierra Script 1.0 - (do not remove this comment)
(script# 17)
(include game.sh)
(use Main)
(use Intrface)
(use Timer)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Menu)
(use Actor)
(use System)

(public
	rm017 0
)

(local
	local0
	local1
	local2
	[str 5]
	[local8 25]
	local33
	[local34 49]
	local83
	[local84 49]
	local133
	[local134 49]
	local183
	[local184 49]
	local233
)
(procedure (localproc_0a2c message theY theColor)
	(if (< numColors 16) (= theColor vWHITE))
	(Display message
		p_font 600
		p_width 280
		p_color theColor
		p_at 20 theY
	)
	(RedrawCast)
)

(procedure (localproc_0a59 param1 theY)
	(if param1
		(Display param1
			p_font 600
			p_width 280
			p_color vBLACK
			p_at 20 theY
		)
	)
)

(procedure (localproc_0a78)
	(engine
		cel: (cond 
			((!= motivatorState motivatorINSHIP) 2)
			((== global208 2) 1)
			(else 0)
		)
		draw:
	)
	(TLBut
		loop:
			(if
				(or
					(and (== global206 2) (!= global207 2))
					(== global206 0)
				)
				2
			else
				3
			)
		cel:
			(if
				(and
					(== global208 2)
					(!= shipLocation shipSPACE)
					(not (== currentSector 62))
					(not twoGuysOnBoard)
				)
				0
			else
				2
			)
		draw:
	)
	(cruiseBut
		cel:
			(cond 
				((== global209 2) 1)
				((or (!= global208 2) (not selectedSector)) 2)
				(else 0)
			)
		draw:
	)
	(LSpeedBut
		cel:
			(cond 
				((== global209 6) 1)
				(
				(or (not selectedSector) global179 (!= global208 2)) 2)
				(else 0)
			)
		draw:
	)
	(ASpeedBut
		cel:
			(cond 
				((== global209 3) 1)
				(
					(or
						(and (not selectedSector) (not global218))
						(!= global208 2)
					)
					2
				)
				(else 0)
			)
		draw:
	)
	(radarBut cel: (if global207 1 else 0) draw:)
	(navBut
		cel:
			(if
				(and
					(== global208 2)
					(== global206 3)
					(not twoGuysOnBoard)
					(not local2)
				)
				0
			else
				2
			)
		draw:
	)
	(weaponBut
		cel: (if (and (> global206 0) (not local2)) 0 else 2)
		draw:
	)
)

(instance rm017 of Room
	(properties
		style HSHUTTER
	)
	
	(method (init &tmp [temp0 50])
		(asm
			pushi    #setRegions
			pushi    1
			pushi    701
			self     6
			pushi    #hide
			pushi    0
			class    TheMenuBar
			send     4
			pushi    #disable
			pushi    0
			class    StatusLine
			send     4
			lsg      numColors
			ldi      16
			lt?     
			bnt      code_0050
			pushi    2
			pushi    129
			pushi    170
			callk    Load,  4
			pushi    #drawPic
			pushi    1
			pushi    170
			self     6
			pushi    2
			pushi    128
			pushi    141
			callk    Load,  4
			jmp      code_006a
code_0050:
			pushi    2
			pushi    129
			pushi    17
			callk    Load,  4
			pushi    #drawPic
			pushi    1
			pushi    17
			self     6
			pushi    2
			pushi    128
			pushi    41
			callk    Load,  4
code_006a:
			pushi    2
			pushi    132
			pushi    40
			callk    Load,  4
			pushi    2
			pushi    132
			pushi    83
			callk    Load,  4
			pushi    2
			pushi    132
			pushi    95
			callk    Load,  4
			pushi    2
			pushi    132
			pushi    96
			callk    Load,  4
			pushi    #init
			pushi    0
			super    Room,  4
			pushi    0
			callb    HandsOff,  0
			pushi    #mapKeyToDir
			pushi    1
			pushi    0
			class    User
			send     6
			ldi      1
			sag      global592
			pushi    #setCursor
			pushi    2
			lsg      normalCursor
			pushi    0
			callk    HaveMouse,  0
			push    
			lag      theGame
			send     8
			ldi      1
			sag      saveDisabled
			pushi    #add
			pushi    8
			lofsa    engine
			push    
			lofsa    navBut
			push    
			lofsa    TLBut
			push    
			lofsa    cruiseBut
			push    
			lofsa    LSpeedBut
			push    
			lofsa    ASpeedBut
			push    
			lofsa    radarBut
			push    
			lofsa    weaponBut
			push    
			pushi    101
			pushi    1
			pushi    90
			pushi    83
			pushi    0
			pToa     controls
			send     30
			lag      global167
			bnt      code_00fd
			lag      twoGuysOnBoard
			not     
			bnt      code_00fd
			pushi    #setScript
			pushi    1
			lofsa    arrivalScript
			push    
			self     6
code_00fd:
			pushi    0
			call     localproc_0a78,  0
			lsg      global206
			ldi      2
			eq?     
			bnt      code_0122
			lsg      global207
			ldi      2
			eq?     
			bnt      code_0122
			lofsa    {ASCENT HALTED DUE TO OBSTRUCTION}
			sal      local33
			pushi    3
			push    
			pushi    104
			pushi    14
			call     localproc_0a2c,  6
code_0122:
			pushi    3
			lag      selectedSector
			bnt      code_013a
			dup     
			lea      @local8
			push    
			lofsa    {DESTINATION: SECTOR %d}
			push    
			lsg      selectedSector
			callk    Format,  6
			jmp      code_013d
code_013a:
			lofsa    {NO COURSE SELECTED}
code_013d:
			push    
			pushi    154
			pushi    9
			call     localproc_0a2c,  6
			sal      local0
			pushi    11
			pushi    17
			pushi    0
			pushi    100
			pushi    20
			pushi    144
			pushi    105
			pushi    600
			pushi    106
			pushi    250
			pushi    102
			pushi    10
			callk    Display,  22
			pushi    11
			pushi    17
			pushi    1
			pushi    100
			pushi    97
			pushi    67
			pushi    105
			pushi    600
			pushi    106
			pushi    180
			pushi    102
			pushi    15
			callk    Display,  22
			lag      global179
			bnt      code_019f
			lag      global181
			not     
			bnt      code_019f
			pushi    2
			pushi    132
			pushi    27
			callk    Load,  4
			pushi    #setScript
			pushi    1
			lofsa    LightWarning
			push    
			self     6
code_019f:
			ret     
		)
	)
	
	(method (doit)
		(if (and global167 (not script))
			(self setScript: arrivalScript)
		)
		(Display
			(Format @str 17 2 currentSector)
			p_at 100 144
			p_width 15
			p_color vLGREEN
			p_back vBLACK
			p_font 600
		)
		(super doit:)
	)
	
	(method (handleEvent event)
		(super handleEvent: event)
		(if (event claimed?) (return))
		(switch (event type?)
			(keyDown
				(if (and (== (event message?) `#6) (not local2))
					(event claimed: 1)
					(= saveDisabled 0)
					(curRoom newRoom: 14)
				)
			)
			(mouseDown
				(if
					(and
						(<= 95 (event x?))
						(<= (event x?) 222)
						(<= 60 (event y?))
						(<= (event y?) 77)
						(not local2)
					)
					(event claimed: TRUE)
					(= saveDisabled 0)
					(curRoom newRoom: 14)
				)
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(= local233 1)
		(RedrawCast)
		(User mapKeyToDir: 1)
		(timers eachElementDo: #dispose 84)
		(= saveDisabled 0)
		(super newRoom: newRoomNumber)
	)
)

(instance arrivalScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global167 0)
				(HandsOff)
				(= local2 1)
				(= global209 1)
				(= currentSector selectedSector)
				(= selectedSector 0)
				(localproc_0a78)
				(= local133
					(if (== global209 6)
						{CUTTING LIGHT ENGINES}
					else
						{THROTTLING ENGINES BACK}
					)
				)
				(localproc_0a2c local133 134 14)
				(= seconds 3)
			)
			(1
				(localproc_0a59 local133 134)
				(= cycles 2)
			)
			(2
				(= local133
					(cond 
						((== currentSector 39) {ORBITING PLANET PHLEEBHUT})
						((== currentSector 62) {APPROACHING MONOLITH BURGER})
						((== currentSector 82) {ORBITING PLANET ORTEGA})
						((== currentSector 69) {ORBITING PESTULON})
					)
				)
				(localproc_0a2c local133 134 3)
				(= seconds 3)
			)
			(3
				(localproc_0a59 local133 134)
				(cond 
					((== currentSector 62) (= global208 0) (= saveDisabled 0) (curRoom newRoom: 27))
					((== currentSector 39) (= shipLocation 4))
					((== currentSector 82) (= shipLocation 3))
					((== currentSector 69) (= shipLocation 7))
				)
				(= local2 0)
				(localproc_0a78)
				(curRoom setScript: 0)
			)
		)
	)
)

(instance LightWarning of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= global181 1)
				(localproc_0a2c {** LIGHT SPEED NON-FUNCTIONAL **} 174 14)
				(beeper play: self)
			)
			(1
				(Print 17 3)
				(localproc_0a59 {** LIGHT SPEED NON-FUNCTIONAL **} 174)
				(client setScript: 0)
			)
		)
	)
)

(instance responseScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {FULL THRUST NECESSARY BEFORE MANEUVERING})
				(= local1 84)
				(localproc_0a2c local83 local1 4)
				(self changeState: 13)
			)
			(2
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {A COURSE MUST BE SET BEFORE SELECTING SPEED})
				(= local1 84)
				(localproc_0a2c local83 local1 4)
				(self changeState: 13)
			)
			(3
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {NO LANDING SURFACE AVAILABLE})
				(= local1 84)
				(localproc_0a2c local83 local1 4)
				(self changeState: 13)
			)
			(4
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {** LIGHT SPEED NON-FUNCTIONAL **})
				(= local1 164)
				(localproc_0a2c local83 local1 4)
				(self changeState: 13)
			)
			(5
				(if local83 (localproc_0a59 local83 local1))
				(= local83
					{WEAPONS SYSTEM INOPERABLE WHILE NOT IN FLIGHT}
				)
				(= local1 114)
				(localproc_0a2c local83 local1 6)
				(self changeState: 13)
			)
			(6
				(if local83 (localproc_0a59 local83 local1))
				(= local83 local33)
				(= local1 104)
				(localproc_0a2c local83 local1 2)
				(self changeState: 13)
			)
			(7
				(if local83 (localproc_0a59 local83 local1))
				(= local83
					{NAVIGATION SYSTEM INOPERABLE WHILE NOT IN FLIGHT}
				)
				(= local1 124)
				(localproc_0a2c local83 local1 2)
				(self changeState: 13)
			)
			(8
				(if local83 (localproc_0a59 local83 local1))
				(= local83
					{** ERROR DETECTED: CONSULT DIAGNOSTIC COMPUTER **}
				)
				(= local1 164)
				(localproc_0a2c local83 local1 4)
				(self changeState: 13)
			)
			(9
				(if local83 (localproc_0a59 local83 local1))
				(= local83 local183)
				(= local1 84)
				(localproc_0a2c local83 local1 2)
				(self changeState: 13)
			)
			(10
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {THRUST GENERATION UNDERWAY})
				(= local1 94)
				(localproc_0a2c local83 local1 14)
				(self changeState: 13)
			)
			(11
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {ADEQUATE THRUST ACHIEVED})
				(= local1 94)
				(localproc_0a2c local83 local1 2)
				(self changeState: 13)
			)
			(12
				(if local83 (localproc_0a59 local83 local1))
				(= local83 {** NAVIGATION SYSTEM NON-FUNCTIONAL **})
				(= local1 164)
				(localproc_0a2c local83 local1 4)
				(self changeState: 13)
			)
			(13 (Timer set: self 3))
			(14
				(localproc_0a59 local83 local1)
				(= programControl 0)
				(= local2 0)
			)
		)
	)
)

(instance toScript of Script
	(properties)
	
	(method (changeState newState &tmp [temp0 50])
		(switch (= state newState)
			(1
				(= local2 1)
				(if (== global206 2)
					(= local183 {TAKEOFF IN PROGRESS})
				else
					(= local183 {LANDING/DOCKING IN PROGRESS})
				)
				(responseScript changeState: 9)
				(switch shipLocation
					(3
						(= global206 0)
						(= global208 0)
						(= shipLocation 5)
					)
					(4
						(= global206 0)
						(= global208 0)
						(= shipLocation 6)
					)
					(7
						(= global206 0)
						(= global208 0)
						(= shipLocation 8)
					)
					(2 (= global208 0))
					(5
						(= global206 3)
						(= shipLocation 3)
					)
					(6
						(= global206 3)
						(= shipLocation 4)
					)
					(8
						(= global206 3)
						(= shipLocation 7)
					)
				)
				(= saveDisabled 0)
				(curRoom
					newRoom:
						(cond 
							((== shipLocation 2) (if (== global206 1) 28 else 31))
							(
								(or
									(== shipLocation 0)
									(== shipLocation 3)
									(== shipLocation 4)
									(== shipLocation 7)
								)
								14
							)
							(
								(or
									(== shipLocation 6)
									(== shipLocation 5)
									(== shipLocation 8)
								)
								21
							)
						)
				)
			)
		)
	)
)

(instance sbScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 1))
			(1
				(= programControl 1)
				(powerUp play:)
				(responseScript changeState: 10)
				(= seconds 5)
			)
			(2
				(standBy setLoop: 8 setCel: 3)
				(= cycles 2)
			)
			(3
				(music number: 59 loop: -1 priority: 0 play:)
				(responseScript changeState: 11)
				(= global208 2)
				(= programControl 0)
				(localproc_0a78)
			)
		)
	)
)

(instance StupidCrash of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local2 1)
				(= seconds 3)
			)
			(1
				(ShakeScreen 10 3)
				(Print 17 4)
				(EgoDead 0 0 5 7)
			)
		)
	)
)

(instance engine of DIcon
	(properties
		state $0001
		nsTop 23
		nsLeft 26
		key 49
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(2
				(responseScript changeState: 8)
				(badBlip play:)
			)
			(1
				(goodBlip play:)
				(if (> global206 1)
					(= global220 0)
					(= global209 1)
					(= global206 3)
				)
				(= global208 0)
				(if (and (== shipLocation 0) (== global207 2))
					(curRoom setScript: StupidCrash)
				)
				(music stop:)
				(powerDown play:)
			)
			(0
				(standBy dispose:)
				(goodBlip play:)
				(= global208 1)
				(standBy
					posn: (engine nsLeft?) (engine nsTop?)
					init:
					setScript: sbScript
				)
			)
		)
		(localproc_0a78)
	)
)

(instance cruiseBut of DIcon
	(properties
		state $0001
		nsTop 41
		nsLeft 99
		key 52
		loop 4
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit &tmp [temp0 50])
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(self cel: 1 draw:)
				(goodBlip play:)
				(if
					(and
						(or
							(and (== scanningSector 69) (== currentSector 82))
							(and (== scanningSector 82) (== currentSector 69))
						)
						(not global218)
					)
					(= global220 10)
				else
					(= global220 180)
				)
				(= global209 2)
				(= shipLocation 1)
				(= saveDisabled 0)
				(curRoom newRoom: (if sawTerminator 14 else 31))
			)
			(1
				(= global209 0)
				(goodBlip play:)
				(localproc_0a78)
			)
			(2
				(badBlip play:)
				(responseScript changeState: 2)
			)
		)
	)
)

(instance ASpeedBut of DIcon
	(properties
		state $0001
		nsTop 41
		nsLeft 160
		key 54
		loop 6
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(self cel: 1 draw:)
				(goodBlip play:)
				(if
					(and
						(or
							(and (== scanningSector 69) (== currentSector 82))
							(and (== scanningSector 82) (== currentSector 69))
						)
						(not global218)
					)
					(= global220 10)
				else
					(= global220 90)
				)
				(= global209 3)
				(= shipLocation 1)
				(= saveDisabled 0)
				(if (not global218)
					(curRoom newRoom: (if sawTerminator 14 else 31))
				)
			)
			(1
				(goodBlip play:)
				(= global209 0)
				(localproc_0a78)
			)
			(2
				(badBlip play:)
				(responseScript changeState: 2)
			)
		)
	)
)

(instance LSpeedBut of DIcon
	(properties
		state $0001
		nsTop 23
		nsLeft 160
		key 53
		loop 5
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(goodBlip play:)
				(self cel: 1 draw:)
				(= global220 2)
				(= global209 6)
				(= shipLocation 1)
				(= saveDisabled 0)
				(curRoom newRoom: 31)
			)
			(1
				(goodBlip play:)
				(= global209 0)
				(localproc_0a78)
			)
			(2
				(badBlip play:)
				(if global179
					(responseScript changeState: 4)
				else
					(responseScript changeState: 2)
				)
			)
		)
	)
)

(instance navBut of DIcon
	(properties
		state $0001
		nsTop 41
		nsLeft 26
		key 50
		loop 1
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(goodBlip play:)
				(self cel: 1 draw:)
				(= saveDisabled 0)
				(curRoom newRoom: 19)
			)
			(2
				(badBlip play:)
				(cond 
					((!= global208 2) (responseScript changeState: 1))
					((or global218 (== global176 5)) (responseScript changeState: 12))
					(else (responseScript changeState: 7))
				)
			)
		)
	)
)

(instance radarBut of DIcon
	(properties
		state $0001
		nsTop 23
		nsLeft 231
		key 55
		loop 7
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(goodBlip play:)
				(self cel: 1 draw:)
				(= local33 {RADAR IS NOW IN OPERATION})
				(= global207 1)
				(responseScript changeState: 6)
			)
			(1
				(if global206
					(badBlip play:)
					(Print 17 5)
				else
					(goodBlip play:)
					(self cel: 0 draw:)
					(= local33 {RADAR IS NOW INOPERATIVE})
					(= global207 0)
					(responseScript changeState: 6)
				)
			)
		)
	)
)

(instance weaponBut of DIcon
	(properties
		state $0001
		nsTop 41
		nsLeft 231
		key 56
		loop 8
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(goodBlip play:)
				(self cel: 1 draw:)
				(= saveDisabled 0)
				(curRoom newRoom: 18)
			)
			(2
				(badBlip play:)
				(responseScript changeState: 5)
			)
		)
	)
)

(instance TLBut of DIcon
	(properties
		state $0001
		nsTop 23
		nsLeft 99
		key 51
		loop 2
	)
	
	(method (init)
		(= view (if (> numColors 4) 41 else 141))
	)
	
	(method (doit)
		(if (or programControl local233) (return))
		(switch (self cel?)
			(0
				(goodBlip play:)
				(if (== (self loop?) 2)
					(self cel: 1 draw:)
					(= global206 2)
					(toScript changeState: 1)
					(= ladderOnGround 1)
				else
					(self cel: 1 draw:)
					(= global206 1)
					(= global209 0)
					(if (== global207 2)
						(= global207 1)
						(if local33 (localproc_0a59 local33 104))
					)
					(toScript changeState: 1)
				)
			)
			(2
				(badBlip play:)
				(if (!= global208 2)
					(responseScript changeState: 1)
				else
					(responseScript changeState: 3)
				)
			)
		)
	)
)

(instance standBy of Prop
	(properties)
	
	(method (init)
		(super init:)
		(self
			view: (if (> numColors 4) 41 else 141)
			setLoop: 9
			cel: 0
			priority: 15
			cycleSpeed: 2
			setCycle: Forward
		)
	)
)

(instance beeper of Sound
	(properties
		number 27
		priority 2
		loop 2
	)
)

(instance powerUp of Sound
	(properties
		number 40
		priority 2
	)
)

(instance powerDown of Sound
	(properties
		number 83
		priority 2
	)
)

(instance goodBlip of Sound
	(properties
		number 95
		priority 2
	)
)

(instance badBlip of Sound
	(properties
		number 96
		priority 2
	)
)
