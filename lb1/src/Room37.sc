;;; Sierra Script 1.0 - (do not remove this comment)
(script# 37)
(include game.sh)
(use Main)
(use Intrface)
(use DCIcon)
(use RFeature)
(use Path)
(use Avoider)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room37 0
)
(synonyms
	(stair upstair)
	(helmet head mask)
	(body chest)
	(balcony balcony banister)
	(room hall)
)

(local
	saveBits
	local1 =  150
	local2 = [65 173 91 171 96 156 82 175 122 210 104 163 157 145 92 142 128 145 154 171 174 162 179 174 157]
	leftBotPts = [103 178 56 150 30 148 PATHEND]
	rightBotPts = [218 177 269 148 290 148 PATHEND]
	leftTopPts = [263 152 205 183 PATHEND]
	rightTopPts = [57 153 117 182 PATHEND]
	local51
	local52
	local53
	local54
	local55
	local56
	local57
	local58
	theCSound
	local60
)
(procedure (RoomPrint)
	(Print &rest
		#at 160 150
		#font 4
		#width 140
		#mode teJustCenter
		#draw
		#dispose
	)
)

(procedure (localproc_002d)
	(if (not local53)
		(= local53 1)
		(User canControl: FALSE)
		(Room37 drawPic: 49 IRISOUT)
		(cast eachElementDo: #hide)
		(Visor show:)
		(note show:)
		(if (not (ego has: iValveHandle))
			(valve setPri: 1 init:)
		)
		(if local57
			(= saveBits
				(Display 37 0
					p_at 48 8
					p_width 256
					p_color vWHITE
					p_back -1
					p_font SYSFONT
					p_save
				)
			)
		)
	)
)

(procedure (localproc_00ab)
	(if local53
		(cls)
		(= local53 0)
		(User canControl: TRUE)
		(if local57 (Display 37 1 p_restore saveBits))
		(Room37 drawPic: curRoomNum 6)
		(cast eachElementDo: #show)
		(addToPics
			add: postR postL phone lamp clock mirror
			eachElementDo: #init
			doit:
		)
		(Can hide:)
		(Visor hide:)
		(note hide:)
		(valve hide:)
		(if (and (!= local52 2) (!= local52 5) (!= local52 6))
			(HandsOn)
		)
	)
)

(instance Room37 of Room
	(properties
		picture 37
	)
	
	(method (init)
		(= horizon 80)
		(= south 16)
		(= global102 0)
		(super init:)
		(Load FONT 4)
		(Load PICTURE 49)
		(LoadMany VIEW 10 12)
		(LoadMany SOUND 9 36 83 73)
		(Load SCRIPT AVOIDER)
		(if
			(and
				(or (not (Btst 41)) (not (Btst 42)) (not (Btst 43)))
				(> currentAct 0)
			)
			(Load VIEW 925)
			(LoadMany 143 412)
			(= local60 1)
		)
		(addToPics
			add: postR postL phone lamp clock mirror
			eachElementDo: #init
			doit:
		)
		(self
			setRegions: 211
			setFeatures: phone lamp clock mirror Armor Axe Carpet Couch
		)
		(note setPri: 1 init: hide:)
		(lampL setPri: 7 init: stopUpd:)
		(lampR setPri: 7 init: stopUpd:)
		(if howFast
			(lampR startUpd: setCycle: Forward)
			(lampL startUpd: setCycle: Forward)
		)
		(Can setStep: 5 5 ignoreHorizon: TRUE init: hide:)
		(Visor setPri: 2 init: hide:)
		(knight ignoreActors: TRUE init: stopUpd:)
		(chand
			setLoop: 2
			ignoreHorizon:
			illegalBits: 0
			setPri: 15
			yStep: 10
			init:
			stopUpd:
		)
		(= theCSound cSound)
		(if (!= prevRoomNum 33)
			(theCSound number: 28 loop: -1 play:)
		)
		(switch prevRoomNum
			(36
				(ego illegalBits: cYELLOW setPri: -1 posn: 92 88)
			)
			(38
				(ego illegalBits: cYELLOW setPri: -1 posn: 229 88)
			)
			(47
				(HandsOff)
				(= local54 1)
				(ego
					illegalBits: cLMAGENTA
					setPri: 14
					setMotion: MoveTo (if (< (ego x?) 160) 30 else 290) 150
				)
			)
			(33
				(if (< (ego x?) 99) (ego posn: 110 (ego y?)))
				(if (> (ego x?) 222) (ego posn: 205 (ego y?)))
			)
			(else 
				(ego illegalBits: cYELLOW setPri: -1)
			)
		)
		(ego view: 0 init:)
	)
	
	(method (doit)
		(switch (ego onControl: 1)
			(256
				(if (and (== (ego loop?) 0) (not local54))
					(= local54 1)
					(= local56 0)
					(= global101 1)
					(= horizon 50)
					(= local58 1)
					(= north 47)
					(HandsOff)
					(ego
						ignoreActors: TRUE
						illegalBits: 0
						setPri: 14
						setMotion: rightBotPath
					)
				)
			)
			(4096
				(if (and (== (ego loop?) 1) (not local54))
					(= local54 1)
					(= local58 1)
					(= local56 0)
					(= horizon 50)
					(= north 47)
					(= global101 1)
					(ego illegalBits: 0 setPri: 14)
					(HandsOff)
					(ego setMotion: leftBotPath)
				)
			)
			(1
				(if (not local56)
					(= local56 1)
					(HandsOn)
					(= global101 0)
					(ego illegalBits: cYELLOW setPri: -1)
					(= horizon 80)
					(= north 33)
					(= local54 0)
				)
			)
			(4 (curRoom newRoom: 36))
			(2 (curRoom newRoom: 38))
			(32
				(if (not local55)
					(= local58 0)
					(= global101 0)
					(= local55 1)
					(HandsOn)
					(ego ignoreActors: 0 illegalBits: cLMAGENTA)
				)
			)
			(64
				(if (not local55)
					(= local55 1)
					(= local58 0)
					(= global101 0)
					(HandsOn)
					(ego ignoreActors: 0 illegalBits: cLMAGENTA)
				)
			)
			(128
				(if local55
					(HandsOff)
					(= local55 0)
					(ego illegalBits: 0 setMotion: MoveTo 52 33)
				)
			)
			(1024
				(if local55
					(HandsOff)
					(= local55 0)
					(ego illegalBits: 0 setMotion: MoveTo 264 17)
				)
			)
			(512
				(if local55
					(= local55 0)
					(= local58 1)
					(HandsOff)
					(ego setMotion: leftTopPath)
				)
			)
			(8
				(if local55
					(= local55 0)
					(= local58 1)
					(HandsOff)
					(ego setMotion: rightTopPath)
				)
			)
			(16384
				(= north 47)
				(= horizon 50)
			)
			(16
				(if (and (== (ego loop?) 2) (== script 0))
					(self setScript: crush)
				)
			)
		)
		(cond 
			((< (ego x?) 130) (= vertAngle 10))
			((< (ego x?) 190) (= vertAngle 0))
			(else (= vertAngle 170))
		)
		(super doit:)
		(if (FirstEntry)
			(Print 37 2)
		)
		(if (and local60 (& (ego onControl: origin) cBLACK))
			(HandsOff)
			(= local60 0)
			(self setScript: (ScriptID 412 0))
		)
	)
	
	(method (dispose)
		(DisposeScript PATH)
		(DisposeScript AVOIDER)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp temp0)
		(asm
			pushi    #claimed
			pushi    0
			lap      event
			send     4
			bnt      code_06f9
			ldi      1
			ret     
code_06f9:
			lal      local57
			bnt      code_0767
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      69
			eq?     
			bt       code_071a
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			ldi      101
			eq?     
			bnt      code_0767
code_071a:
			pushi    4
			pushi    #type
			pushi    0
			lap      event
			send     4
			eq?     
			bnt      code_0767
			lsl      local53
			ldi      1
			eq?     
			bnt      code_0767
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			lal      local53
			bt       code_0741
			lal      local57
			bnt      code_0767
code_0741:
			pushi    #stop
			pushi    0
			lofsa    Crash
			send     4
			pushi    0
			call     localproc_00ab,  0
			lal      local57
			bnt      code_0767
			pushi    #setScript
			pushi    1
			pushi    0
			self     6
			pushi    #setAvoider
			pushi    1
			pushi    0
			lag      ego
			send     6
			ldi      0
			sal      local57
code_0767:
			pushi    #type
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      128
			eq?     
			bnt      code_0d3b
			pushi    1
			lofsa    'get,force,rotate,detach,rotate/bird,post[<newel]'
			push    
			callk    Said,  2
			bnt      code_078e
			pushi    2
			pushi    37
			pushi    3
			calle    Print,  4
			jmp      code_0d30
code_078e:
			pushi    1
			lofsa    'get>'
			push    
			callk    Said,  2
			bnt      code_07de
			pushi    1
			lofsa    '/armor,cloth'
			push    
			callk    Said,  2
			bnt      code_07b0
			pushi    2
			pushi    37
			pushi    4
			calle    Print,  4
			jmp      code_0d30
code_07b0:
			pushi    1
			lofsa    '/ax[<battle]'
			push    
			callk    Said,  2
			bnt      code_07c7
			pushi    2
			pushi    37
			pushi    5
			calle    Print,  4
			jmp      code_0d30
code_07c7:
			pushi    1
			lofsa    '/letter'
			push    
			callk    Said,  2
			bnt      code_0d30
			pushi    2
			pushi    37
			pushi    6
			calle    Print,  4
			jmp      code_0d30
code_07de:
			pushi    1
			lofsa    'detach/ax[<battle]'
			push    
			callk    Said,  2
			bnt      code_07f5
			pushi    2
			pushi    37
			pushi    5
			calle    Print,  4
			jmp      code_0d30
code_07f5:
			pushi    1
			lofsa    'lift,force,open,lift,(examine<in)/helmet'
			push    
			callk    Said,  2
			bnt      code_0820
			lsg      global142
			ldi      2
			and     
			bnt      code_0814
			pushi    #setScript
			pushi    1
			lofsa    openVisor
			push    
			self     6
			jmp      code_0d30
code_0814:
			pushi    2
			pushi    37
			pushi    7
			calle    Print,  4
			jmp      code_0d30
code_0820:
			pushi    1
			lofsa    '(press,move,rotate,open,lift,break,(examine<in))>'
			push    
			callk    Said,  2
			bnt      code_09a5
			ldi      0
			sal      local52
			pushi    1
			lofsa    '/armor,cloth'
			push    
			callk    Said,  2
			bnt      code_0846
			pushi    2
			pushi    37
			pushi    7
			calle    Print,  4
			jmp      code_0980
code_0846:
			pushi    1
			lofsa    '/neck'
			push    
			callk    Said,  2
			bnt      code_0858
			ldi      4
			sal      local52
			jmp      code_0980
code_0858:
			pushi    1
			lofsa    '/body'
			push    
			callk    Said,  2
			bnt      code_086a
			ldi      3
			sal      local52
			jmp      code_0980
code_086a:
			pushi    1
			lofsa    '/knee<right'
			push    
			callk    Said,  2
			bnt      code_087c
			ldi      12
			sal      local52
			jmp      code_0980
code_087c:
			pushi    1
			lofsa    '/knee<left'
			push    
			callk    Said,  2
			bnt      code_088e
			ldi      11
			sal      local52
			jmp      code_0980
code_088e:
			pushi    1
			lofsa    '/knee'
			push    
			callk    Said,  2
			bnt      code_08a0
			ldi      11
			sal      local52
			jmp      code_0980
code_08a0:
			pushi    1
			lofsa    '/leg<right'
			push    
			callk    Said,  2
			bnt      code_08b2
			ldi      7
			sal      local52
			jmp      code_0980
code_08b2:
			pushi    1
			lofsa    '/leg<left'
			push    
			callk    Said,  2
			bnt      code_08c4
			ldi      13
			sal      local52
			jmp      code_0980
code_08c4:
			pushi    1
			lofsa    '/leg'
			push    
			callk    Said,  2
			bnt      code_08d6
			ldi      13
			sal      local52
			jmp      code_0980
code_08d6:
			pushi    1
			lofsa    '/deliver<right'
			push    
			callk    Said,  2
			bnt      code_08e8
			ldi      10
			sal      local52
			jmp      code_0980
code_08e8:
			pushi    1
			lofsa    '/deliver<left'
			push    
			callk    Said,  2
			bt       code_08fe
			pushi    1
			lofsa    '/ax[<battle]'
			push    
			callk    Said,  2
			bnt      code_0905
code_08fe:
			ldi      6
			sal      local52
			jmp      code_0980
code_0905:
			pushi    1
			lofsa    '/deliver'
			push    
			callk    Said,  2
			bnt      code_0917
			ldi      6
			sal      local52
			jmp      code_0980
code_0917:
			pushi    1
			lofsa    '/elbow<right'
			push    
			callk    Said,  2
			bnt      code_0929
			ldi      9
			sal      local52
			jmp      code_0980
code_0929:
			pushi    1
			lofsa    '/elbow<left'
			push    
			callk    Said,  2
			bnt      code_093b
			ldi      5
			sal      local52
			jmp      code_0980
code_093b:
			pushi    1
			lofsa    '/elbow'
			push    
			callk    Said,  2
			bnt      code_094d
			ldi      5
			sal      local52
			jmp      code_0980
code_094d:
			pushi    1
			lofsa    '/arm<right'
			push    
			callk    Said,  2
			bnt      code_095f
			ldi      8
			sal      local52
			jmp      code_0980
code_095f:
			pushi    1
			lofsa    '/arm<left'
			push    
			callk    Said,  2
			bnt      code_0971
			ldi      2
			sal      local52
			jmp      code_0980
code_0971:
			pushi    1
			lofsa    '/arm'
			push    
			callk    Said,  2
			bnt      code_0980
			ldi      2
			sal      local52
code_0980:
			lal      local52
			bnt      code_0d30
			lsg      global142
			pushi    1
			shl     
			and     
			bnt      code_0999
			pushi    2
			pushi    37
			pushi    8
			calle    Print,  4
			jmp      code_0d30
code_0999:
			pushi    2
			pushi    37
			pushi    9
			calle    Print,  4
			jmp      code_0d30
code_09a5:
			pushi    1
			lofsa    'examine>'
			push    
			callk    Said,  2
			bnt      code_0ab1
			pushi    1
			lofsa    '[<around,at][/room]'
			push    
			callk    Said,  2
			bnt      code_09c6
			pushi    2
			pushi    37
			pushi    2
			calle    Print,  4
			jmp      code_0d30
code_09c6:
			pushi    1
			lofsa    '/chandelier,ceiling'
			push    
			callk    Said,  2
			bt       code_09dc
			pushi    1
			lofsa    '<up'
			push    
			callk    Said,  2
			bnt      code_09e8
code_09dc:
			pushi    2
			pushi    37
			pushi    10
			calle    Print,  4
			jmp      code_0d30
code_09e8:
			pushi    1
			lofsa    '/hidden<door'
			push    
			callk    Said,  2
			bnt      code_09ff
			pushi    2
			pushi    37
			pushi    11
			calle    Print,  4
			jmp      code_0d30
code_09ff:
			pushi    1
			lofsa    '/door<back'
			push    
			callk    Said,  2
			bnt      code_0a16
			pushi    2
			pushi    37
			pushi    12
			calle    Print,  4
			jmp      code_0d30
code_0a16:
			pushi    1
			lofsa    '<(in,behind)/clock'
			push    
			callk    Said,  2
			bt       code_0a37
			pushi    1
			lofsa    '<(in,behind)/mirror'
			push    
			callk    Said,  2
			bt       code_0a37
			pushi    1
			lofsa    '/time'
			push    
			callk    Said,  2
			bnt      code_0a3e
code_0a37:
			pushi    0
			callb    NotClose,  0
			jmp      code_0d30
code_0a3e:
			pushi    1
			lofsa    '/ax[<battle]'
			push    
			callk    Said,  2
			bnt      code_0a55
			pushi    2
			pushi    37
			pushi    13
			calle    Print,  4
			jmp      code_0d30
code_0a55:
			pushi    1
			lofsa    '/door'
			push    
			callk    Said,  2
			bnt      code_0a6c
			pushi    2
			pushi    37
			pushi    14
			calle    Print,  4
			jmp      code_0d30
code_0a6c:
			pushi    1
			lofsa    '/bird,post[<newel]'
			push    
			callk    Said,  2
			bnt      code_0a83
			pushi    2
			pushi    37
			pushi    15
			calle    Print,  4
			jmp      code_0d30
code_0a83:
			pushi    1
			lofsa    '<below/stair'
			push    
			callk    Said,  2
			bnt      code_0a9a
			pushi    2
			pushi    37
			pushi    16
			calle    Print,  4
			jmp      code_0d30
code_0a9a:
			pushi    1
			lofsa    '/downstair'
			push    
			callk    Said,  2
			bnt      code_0d30
			pushi    2
			pushi    37
			pushi    17
			calle    Print,  4
			jmp      code_0d30
code_0ab1:
			pushi    1
			lofsa    'oil>'
			push    
			callk    Said,  2
			bnt      code_0c82
			pushi    #has
			pushi    1
			pushi    3
			lag      ego
			send     6
			bnt      code_0c6b
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      1
			and     
			bnt      code_0c5c
			ldi      0
			sal      local52
			pushi    1
			lofsa    '/helmet,helmet'
			push    
			callk    Said,  2
			bnt      code_0aef
			ldi      1
			sal      local52
			jmp      code_0c44
code_0aef:
			pushi    1
			lofsa    '/neck'
			push    
			callk    Said,  2
			bnt      code_0b01
			ldi      4
			sal      local52
			jmp      code_0c44
code_0b01:
			pushi    1
			lofsa    '/body'
			push    
			callk    Said,  2
			bnt      code_0b13
			ldi      3
			sal      local52
			jmp      code_0c44
code_0b13:
			pushi    1
			lofsa    '/knee<right'
			push    
			callk    Said,  2
			bnt      code_0b25
			ldi      12
			sal      local52
			jmp      code_0c44
code_0b25:
			pushi    1
			lofsa    '/knee<left'
			push    
			callk    Said,  2
			bnt      code_0b37
			ldi      11
			sal      local52
			jmp      code_0c44
code_0b37:
			pushi    1
			lofsa    '/knee'
			push    
			callk    Said,  2
			bnt      code_0b49
			ldi      11
			sal      local52
			jmp      code_0c44
code_0b49:
			pushi    1
			lofsa    '/leg<right'
			push    
			callk    Said,  2
			bnt      code_0b5b
			ldi      7
			sal      local52
			jmp      code_0c44
code_0b5b:
			pushi    1
			lofsa    '/leg<left'
			push    
			callk    Said,  2
			bnt      code_0b6d
			ldi      13
			sal      local52
			jmp      code_0c44
code_0b6d:
			pushi    1
			lofsa    '/leg'
			push    
			callk    Said,  2
			bnt      code_0b7f
			ldi      13
			sal      local52
			jmp      code_0c44
code_0b7f:
			pushi    1
			lofsa    '/deliver<right'
			push    
			callk    Said,  2
			bnt      code_0b91
			ldi      10
			sal      local52
			jmp      code_0c44
code_0b91:
			pushi    1
			lofsa    '/deliver<left'
			push    
			callk    Said,  2
			bt       code_0ba7
			pushi    1
			lofsa    '/ax[<battle]'
			push    
			callk    Said,  2
			bnt      code_0bae
code_0ba7:
			ldi      6
			sal      local52
			jmp      code_0c44
code_0bae:
			pushi    1
			lofsa    '/deliver'
			push    
			callk    Said,  2
			bnt      code_0bc0
			ldi      6
			sal      local52
			jmp      code_0c44
code_0bc0:
			pushi    1
			lofsa    '/elbow<right'
			push    
			callk    Said,  2
			bnt      code_0bd2
			ldi      9
			sal      local52
			jmp      code_0c44
code_0bd2:
			pushi    1
			lofsa    '/elbow<left'
			push    
			callk    Said,  2
			bnt      code_0be4
			ldi      5
			sal      local52
			jmp      code_0c44
code_0be4:
			pushi    1
			lofsa    '/elbow'
			push    
			callk    Said,  2
			bnt      code_0bf6
			ldi      5
			sal      local52
			jmp      code_0c44
code_0bf6:
			pushi    1
			lofsa    '/arm<right'
			push    
			callk    Said,  2
			bnt      code_0c08
			ldi      8
			sal      local52
			jmp      code_0c44
code_0c08:
			pushi    1
			lofsa    '/arm<left'
			push    
			callk    Said,  2
			bnt      code_0c1a
			ldi      2
			sal      local52
			jmp      code_0c44
code_0c1a:
			pushi    1
			lofsa    '/arm'
			push    
			callk    Said,  2
			bnt      code_0c2c
			ldi      2
			sal      local52
			jmp      code_0c44
code_0c2c:
			pushi    1
			lofsa    '/armor,cloth'
			push    
			callk    Said,  2
			bnt      code_0c44
			pushi    2
			pushi    37
			pushi    18
			calle    Print,  4
			ldi      0
			sal      local52
code_0c44:
			lal      local52
			bnt      code_0d30
			lsg      global142
			pushi    1
			shl     
			or      
			sag      global142
			pushi    #setScript
			pushi    1
			lofsa    oiling
			push    
			self     6
			jmp      code_0d30
code_0c5c:
			pushi    0
			callb    NotClose,  0
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0d30
code_0c6b:
			pushi    1
			lofsa    '[<*][/*]'
			push    
			callk    Said,  2
			bnt      code_0d30
			pushi    2
			pushi    37
			pushi    19
			calle    Print,  4
			jmp      code_0d30
code_0c82:
			pushi    1
			lofsa    'force>'
			push    
			callk    Said,  2
			bnt      code_0cc6
			pushi    1
			lofsa    '/ax'
			push    
			callk    Said,  2
			bnt      code_0ca4
			pushi    2
			pushi    37
			pushi    6
			calle    Print,  4
			jmp      code_0d30
code_0ca4:
			pushi    1
			lofsa    '/arm,leg,helmet,helmet'
			push    
			callk    Said,  2
			bt       code_0cba
			pushi    1
			lofsa    '/armor,cloth'
			push    
			callk    Said,  2
			bnt      code_0d30
code_0cba:
			pushi    2
			pushi    37
			pushi    7
			calle    Print,  4
			jmp      code_0d30
code_0cc6:
			pushi    1
			lofsa    'wear,attach<on>'
			push    
			callk    Said,  2
			bnt      code_0cf3
			pushi    1
			lofsa    '/arm,leg,helmet,helmet'
			push    
			callk    Said,  2
			bt       code_0ce7
			pushi    1
			lofsa    '/armor,cloth'
			push    
			callk    Said,  2
			bnt      code_0d30
code_0ce7:
			pushi    2
			pushi    37
			pushi    20
			calle    Print,  4
			jmp      code_0d30
code_0cf3:
			pushi    1
			lofsa    'climb[<up]/stair'
			push    
			callk    Said,  2
			bnt      code_0d0a
			pushi    2
			pushi    37
			pushi    21
			calle    Print,  4
			jmp      code_0d30
code_0d0a:
			pushi    1
			lofsa    'clock,time'
			push    
			callk    Said,  2
			bnt      code_0d1c
			pushi    0
			callb    NotClose,  0
			jmp      code_0d30
code_0d1c:
			pushi    1
			lofsa    'hear/clock'
			push    
			callk    Said,  2
			bnt      code_0d30
			pushi    2
			pushi    37
			pushi    22
			calle    Print,  4
code_0d30:
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Room,  6
			jmp      code_0f17
code_0d3b:
			dup     
			ldi      1
			bt       code_0d43
			ldi      2
code_0d43:
			eq?     
			bnt      code_0e55
			lal      local58
			bt       code_0d52
			lal      local54
			not     
			bnt      code_0d53
code_0d52:
			ret     
code_0d53:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      16384
			and     
			bnt      code_0f17
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      160
			lt?     
			bnt      code_0ddf
			pushi    211
			pushi    3
			class    MoveTo
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0d93
			ldi      52
			jmp      code_0da8
code_0d93:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_0da8
			ldi      30
code_0da8:
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_0dc2
			ldi      148
			jmp      code_0dd7
code_0dc2:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0dd7
			ldi      33
code_0dd7:
			push    
			lag      ego
			send     10
			jmp      code_0e4a
code_0ddf:
			pushi    211
			pushi    3
			class    MoveTo
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0e00
			ldi      261
			jmp      code_0e16
code_0e00:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_0e16
			ldi      290
code_0e16:
			push    
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			lt?     
			bnt      code_0e2f
			ldi      17
			jmp      code_0e45
code_0e2f:
			pushi    #y
			pushi    0
			lap      event
			send     4
			push    
			pushi    #y
			pushi    0
			lag      ego
			send     4
			gt?     
			bnt      code_0e45
			ldi      148
code_0e45:
			push    
			lag      ego
			send     10
code_0e4a:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0f17
code_0e55:
			dup     
			ldi      64
			eq?     
			bnt      code_0f17
			lal      local54
			not     
			bt       code_0e67
			lal      local58
			bnt      code_0e68
code_0e67:
			ret     
code_0e68:
			pushi    #onControl
			pushi    1
			pushi    1
			lag      ego
			send     6
			push    
			ldi      16384
			and     
			bnt      code_0f17
			pushi    #message
			pushi    0
			lap      event
			send     4
			push    
			dup     
			ldi      1
			eq?     
			bnt      code_0ec6
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      160
			lt?     
			bnt      code_0eaa
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    52
			pushi    33
			lag      ego
			send     10
			jmp      code_0ebb
code_0eaa:
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    264
			pushi    17
			lag      ego
			send     10
code_0ebb:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0f16
code_0ec6:
			dup     
			ldi      5
			eq?     
			bnt      code_0f0d
			pushi    #x
			pushi    0
			lag      ego
			send     4
			push    
			ldi      160
			lt?     
			bnt      code_0ef0
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    30
			pushi    148
			lag      ego
			send     10
			jmp      code_0f02
code_0ef0:
			pushi    #setMotion
			pushi    3
			class    MoveTo
			push    
			pushi    290
			pushi    148
			lag      ego
			send     10
code_0f02:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			jmp      code_0f16
code_0f0d:
			pushi    #claimed
			pushi    1
			pushi    1
			lap      event
			send     6
			ret     
code_0f16:
			toss    
code_0f17:
			toss    
			ret     
		)
	)
	
	(method (newRoom n)
		(if (and (== theCSound cSound) (!= n 33))
			(theCSound stop:)
		)
		(super newRoom: n)
	)
)

(instance crush of Script
	
	(method (doit)
		(super doit:)
		(if
			(and
				(< (ego distanceTo: chand) 80)
				(== local51 0)
				(== state 0)
			)
			(= local51 1)
			(ego view: 10 loop: 0 cel: 0 setCycle: EndLoop)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(chand setMotion: MoveTo 158 102 self)
				(theCSound stop:)
				(Fall priority: 2 play:)
			)
			(1
				(Crash number: 36 play:)
				(chand hide:)
				(ego loop: 1 cel: 0 setCycle: EndLoop self)
			)
			(2
				(Fall stop:)
				(ShakeScreen 5 5)
				(= seconds 3)
			)
			(3
				(myIcon view: 10 loop: 3 cycleSpeed: 8)
				(= cIcon myIcon)
				(= deathLoop 3)
				(= deathCel 0)
				(= cyclingIcon 1)
				(EgoDead 37 23)
			)
		)
	)
)

(instance openVisor of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(if (ego inRect: 106 150 108 152)
					(= cycles 1)
				else
					(ego
						setAvoider: (Avoider new:)
						setMotion: MoveTo 107 152 self
					)
				)
			)
			(1
				(ego loop: 1)
				(= cycles 3)
			)
			(2
				(localproc_002d)
				(= cycles 7)
			)
			(3
				(Visor cycleSpeed: 3 setCycle: EndLoop self)
			)
			(4
				(if (== ((inventory at: iValveHandle) owner?) 37)
					(Print 37 25 #at 60 60)
				)
				(= cycles 1)
			)
			(5
				(if (== ((inventory at: iValveHandle) owner?) 37)
					(Print 37 26 #at 60 60)
					(valve dispose:)
				)
				(= cycles 1)
			)
			(6
				(if (== ((inventory at: iValveHandle) owner?) 37)
					(Print 37 27 #at 60 60)
				else
					(Print 37 11 #at 60 60)
				)
				(= cycles 1)
			)
			(7
				(User canInput: FALSE)
				(if (== ((inventory at: iValveHandle) owner?) 37)
					(Print 37 28 #at 60 90)
					(= gotItem TRUE)
					(ego get: iValveHandle)
				else
					(Print 37 29 #at 70 90)
				)
				(= cycles 1)
			)
			(8
				(Print 37 30)
				(= cycles 1)
			)
			(9 (Visor setCycle: BegLoop self))
			(10
				(localproc_00ab)
				(ego setAvoider: 0)
				(= cycles 1)
			)
			(11
				(HandsOn)
				(client setScript: 0)
			)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said '(examine,read,get)/letter')
						(if (== state 3)
							(= cycles 1)
						else
							(Print 37 24)
						)
					)
					((Said 'close/helmet')
						(= state 6)
						(= cycles 1)
					)
				)
			else
				FALSE
			)
		)
	)
)

(instance oiling of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(HandsOff)
				(= local57 1)
				(if (ego inRect: 104 148 111 154)
					(= cycles 1)
				else
					(= seconds (= cycles 0))
					(ego
						setAvoider: (Avoider new:)
						setMotion: MoveTo 107 152 self
					)
				)
			)
			(1 (ego loop: 1) (= cycles 3))
			(2
				(localproc_002d)
				(Can posn: 225 100 show:)
				(if local52
					(Can
						setMotion:
							MoveTo
							[local1 (<< (- local52 1) $0001)]
							[local2 (<< (- local52 1) $0001)]
							self
					)
					(RoomPrint 37 31)
				else
					(= state 5)
					(= cycles 1)
				)
			)
			(3
				(cls)
				(if local52 (Can setLoop: 2 setCycle: Forward))
				(= cycles 1)
			)
			(4
				(Crash number: 73 loop: -1 play:)
				(= seconds 3)
			)
			(5
				(Crash stop:)
				(if local52
					(Can setLoop: 1 setMotion: MoveTo 225 100 self)
					(if (or (== local52 2) (== local52 5) (== local52 6))
						(= state 6)
					else
						(= local52 0)
					)
				else
					(= cycles 1)
				)
			)
			(6
				(User canInput: TRUE)
				(client setScript: 0)
			)
			(7
				(localproc_00ab)
				(HandsOff)
				(= cycles 7)
			)
			(8
				(ego setPri: 12)
				(knight setCycle: EndLoop self)
				(Crash number: 83 loop: 1 play:)
			)
			(9
				(ego
					view: 12
					loop: 2
					cel: 0
					setPri: -1
					illegalBits: 0
					posn: 104 153
					cycleSpeed: 3
					setCycle: EndLoop self
				)
			)
			(10
				(client setScript: 0)
				(myIcon view: 653)
				(= cIcon myIcon)
				(= deathLoop 0)
				(= deathCel 0)
				(= cyclingIcon 1)
				(EgoDead 37 32)
			)
		)
	)
)

(instance postR of PicView
	(properties
		y 142
		x 219
		view 137
		cel 1
		priority 13
	)
)

(instance postL of PicView
	(properties
		y 145
		x 103
		view 137
		priority 13
	)
)

(instance phone of RPicView
	(properties
		y 75
		x 219
		view 137
		loop 1
		cel 1
		priority 3
	)
	
	(method (handleEvent event)
		(if (and (not local53) (MousedOn self event shiftDown))
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance lamp of RPicView
	(properties
		y 75
		x 101
		view 137
		loop 1
		priority 3
	)
	
	(method (handleEvent event)
		(if (and (not local53) (MousedOn self event shiftDown))
			(event claimed: TRUE)
			(ParseName {table})
		)
	)
)

(instance clock of RPicView
	(properties
		y 67
		x 108
		view 137
		loop 2
		priority 3
	)
	
	(method (handleEvent event &tmp [str 250])
		(if
			(and
				(not local53)
				(or (MousedOn self event shiftDown) (Said 'examine[<at]/clock'))
			)
			(event claimed: TRUE)
			(Print (Format @str 37 33 37 34))
		)
	)
)

(instance mirror of RPicView
	(properties
		y 72
		x 220
		view 137
		loop 2
		cel 1
		priority 3
	)
	
	(method (handleEvent event &tmp [str 35])
		(cond 
			(
				(or
					(Said 'rotate,move/mirror,clock')
					(Said '(drag,press)[<open,on]/clock')
					(Said '(press,drag)[<open,on]/mirror')
				)
				(NotClose)
			)
			((Said 'rotate,rotate/mirror')
				(NotClose)
			)
			(
				(and
					(not local53)
					(or
						(MousedOn self event shiftDown)
						(Said 'examine[<at]/mirror')
					)
				)
				(event claimed: TRUE)
				(Print (Format @str 37 33 37 35))
			)
		)
	)
)

(instance knight of Prop
	(properties
		y 153
		x 81
		view 12
		loop 3
		priority 11
	)
	
	(method (handleEvent event)
		(cond 
			(
				(Said
					'enter,wear,((get,conceal,go,climb)<in),(attach<on)/armor,cloth'
				)
				(Print 37 36)
			)
			((Said 'get/letter')
				(if (== ((inventory at: iValveHandle) owner?) 37)
					(Print 37 37)
				else
					(Print 37 38)
				)
			)
			((Said 'get/valve,handle')
				(if (== ((inventory at: iValveHandle) owner?) 37)
					(Print 37 37)
				else
					(AlreadyTook)
				)
			)
			(
				(or
					(MousedOn self event shiftDown)
					(and
						(Said 'examine/armor,cloth>')
						(not (Said 'examine<in>'))
					)
				)
				(event claimed: TRUE)
				(if (not local53) (localproc_002d))
				(Print 37 39 #at 110 110)
				(if (not local57) (localproc_00ab))
			)
		)
	)
)

(instance lampL of Prop
	(properties
		y 60
		x 89
		view 237
		loop 1
	)
	
	(method (handleEvent event)
		(if (and (not local53) (MousedOn self event shiftDown))
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance lampR of Prop
	(properties
		y 60
		x 234
		view 237
		loop 2
	)
	
	(method (handleEvent event)
		(if (and (not local53) (MousedOn self event shiftDown))
			(event claimed: TRUE)
			(ParseName {lamp})
		)
	)
)

(instance valve of Prop
	(properties
		y 46
		x 150
		view 137
		loop 3
	)
)

(instance note of Prop
	(properties
		y 42
		x 151
		view 137
		loop 3
		cel 1
	)
)

(instance Visor of Prop
	(properties
		y 35
		x 134
		view 149
	)
)

(instance chand of Actor
	(properties
		y -1
		x 158
		view 10
	)
)

(instance Can of Actor
	(properties
		y 100
		x 225
		view 149
		loop 1
	)
)

(instance Armor of RFeature
	(properties
		nsTop 24
		nsLeft 101
		nsBottom 189
		nsRight 162
	)
	
	(method (handleEvent event)
		(if (and local53 (MousedOn self event shiftDown))
			(event claimed: TRUE)
			(Print 37 39 #at 110 110)
		)
	)
)

(instance Axe of RFeature
	(properties
		nsTop 25
		nsLeft 167
		nsBottom 116
		nsRight 215
	)
	
	(method (handleEvent event)
		(if
			(or
				(and local53 (MousedOn self event shiftDown))
				(Said 'examine/ax[<battle]')
			)
			(Print 37 13)
			(event claimed: TRUE)
		)
	)
)

(instance Carpet of RFeature
	(properties
		nsTop 34
		nsLeft 131
		nsBottom 148
		nsRight 189
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {carpet})
		)
	)
)

(instance Couch of RFeature
	(properties
		nsTop 95
		nsLeft 227
		nsBottom 117
		nsRight 261
	)
	
	(method (handleEvent event)
		(if (MousedOn self event shiftDown)
			(event claimed: TRUE)
			(ParseName {sofa})
		)
	)
)

(instance leftBotPath of Path

	(method (at n)
		(return [leftBotPts n])
	)
)

(instance rightBotPath of Path

	(method (at n)
		(return [rightBotPts n])
	)
)

(instance leftTopPath of Path

	(method (at n)
		(return [leftTopPts n])
	)
)

(instance rightTopPath of Path

	(method (at n)
		(return [rightTopPts n])
	)
)

(instance Crash of Sound
	(properties
		number 36
		priority 5
	)
)

(instance Fall of Sound
	(properties
		number 9
		priority 5
	)
)

(instance myIcon of DCIcon)
