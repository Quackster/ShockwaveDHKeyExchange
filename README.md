# ShockwaveDHKeyExchange
Implementation of the version 28+ Habbo Hotel encryption for the Shockwave client, it uses the Diffie-Hellman key exchange algorithm, pads each incoming packet by a few bytes based on the hash sent to the client via packet [@hh_entry_init/handleCryptoParameters](https://github.com/Quackster/habbo_src/blob/master/r28_20081120_1552_10301_6c80a6dd09d60c84a1396e0ceb63e445/hh_entry_init/Cast%20External%20ParentScript%208%20-%20Login%20Handler%20Class.ls#L322) and then enciphers the incoming data client->server.

Ported by Quackster from the Adobe Lingo produced by the decompiled Habbo Shockwave client using [ProjectorRays/ProjectorRays](https://github.com/ProjectorRays/ProjectorRays).

For a full implementation see: [@Havana/encryption](https://github.com/Quackster/Havana/tree/encryption).
